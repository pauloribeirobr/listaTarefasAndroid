package ra20459229.tarefas;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import ra20459229.tarefas.db.Tarefa;
import ra20459229.tarefas.db.TarefaDBHelper;

public class MainActivity extends ListActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    //Variáveis de apoio
    private TarefaDBHelper helper;
    private ListAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        atualizaLista();

    }

    public void adicionarTarefa(View view) {
        View v = (View) view.getParent();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Adicionar");
        builder.setMessage("Qual a tarefa?");
        final EditText entrada = new EditText(this);
        builder.setView(entrada);
        builder.setPositiveButton("Adicionar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                /**
                 * Insere a Tarefa
                 */
                String task = entrada.getText().toString();

                helper = new TarefaDBHelper(MainActivity.this);
                SQLiteDatabase db = helper.getWritableDatabase();
                ContentValues values = new ContentValues();

                values.clear();
                values.put(Tarefa.Columns.TAREFA, task);

                db.insertWithOnConflict(Tarefa.TABLE, null, values,
                        SQLiteDatabase.CONFLICT_IGNORE);

                atualizaLista();
            }
        });

        builder.setNegativeButton("Cancelar", null);
        builder.create().show();

    }



    private void atualizaLista() {
        helper = new TarefaDBHelper(MainActivity.this);
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor cursor = sqlDB.query(Tarefa.TABLE,
                new String[]{Tarefa.Columns._ID, Tarefa.Columns.TAREFA},
                null,null,null,null,null);

        listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.task_view,
                cursor,
                new String[] { Tarefa.Columns.TAREFA},
                new int[] { R.id.taskTextView},
                0
        );
        this.setListAdapter(listAdapter);
    }



    public void apagarTarefa(View view) {

        View v = (View) view.getParent();

        TextView taskTextView = (TextView) v.findViewById(R.id.taskTextView);
        String task = taskTextView.getText().toString();

        String sql = String.format("DELETE FROM %s WHERE %s = '%s'",
                Tarefa.TABLE,
                Tarefa.Columns.TAREFA,
                task);

        helper = new TarefaDBHelper(MainActivity.this);
        SQLiteDatabase sqlDB = helper.getWritableDatabase();
        sqlDB.execSQL(sql);
        atualizaLista();


    }

}
