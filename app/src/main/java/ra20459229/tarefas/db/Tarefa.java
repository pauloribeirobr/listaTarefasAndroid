package ra20459229.tarefas.db;

/**
 * Created by Paulo Ribeiro on 21/11/15.
 * Cria Banco de Dados SQLITE
 */

import android.provider.BaseColumns;

public class Tarefa {
    public static final String  DB_NAME = "tarefas.db";
    public static final int     DB_VERSION = 1;
    public static final String TABLE = "tarefas";

    //Define as colunas
    public class Columns {
        public static final String TAREFA = "tarefa";
        public static final String _ID = BaseColumns._ID;
    }

}
