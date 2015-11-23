package ra20459229.tarefas.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by paulo on 22/11/15.
 */
public class TarefaDBHelper extends SQLiteOpenHelper {

    public TarefaDBHelper(Context context) {
        super(context, Tarefa.DB_NAME, null, Tarefa.DB_VERSION);
        Log.d("TarefaDBHelper", "Construtor");
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        Log.d("TarefaDBHelper", "onCreate");
        String sqlQuery =
                String.format("CREATE TABLE %s (_id INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT)", Tarefa.TABLE,
                        Tarefa.Columns.TAREFA);

        Log.d("TarefaDBHelper", "Query to form table: " + sqlQuery);
        sqlDB.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int i, int i2) {
        sqlDB.execSQL("DROP TABLE IF EXISTS " + Tarefa.TABLE);
        onCreate(sqlDB);

    }
}
