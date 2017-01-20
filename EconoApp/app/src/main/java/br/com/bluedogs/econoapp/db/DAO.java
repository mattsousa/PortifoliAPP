package br.com.bluedogs.econoapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.bluedogs.econoapp.model.User;

/**
 * Created by Sarah Francis on 11/01/2017.
 */

public class DAO extends SQLiteOpenHelper{

    private static DAO instance;

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "EconoApp";

    public static final String TABELAS[] = {"tbUsuario","tbOperacoes"};

    private DAO(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static DAO getInstance(Context context){
        if(instance == null){
            instance = new DAO(context,DB_NAME,null,DB_VERSION);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder ddl = new StringBuilder();

        ddl.append("CREATE TABLE "+TABELAS[0]+" (");
        ddl.append("_id         INTEGER         PRIMARY KEY     AUTOINCREMENT   ,");
        ddl.append("nome        VARCHAR(50)     NOT NULL                        ,");
        ddl.append("quantia     REAL            NOT NULL                         ");
        ddl.append(");");

        sqLiteDatabase.execSQL(ddl.toString());
        ddl.delete(0,ddl.length());

        ddl.append("CREATE TABLE "+TABELAS[1]+" (");
        ddl.append("_id         INTEGER         PRIMARY KEY     AUTOINCREMENT        ,");
        ddl.append("dataEHora   TEXT("+ User.DATE_FORMAT.length()+")     NOT NULL    ,");
        ddl.append("tipo        VARCHAR(3)      NOT NULL                             ,");
        ddl.append("valor       REAL            NOT NULL                             ");
        ddl.append(");");
        sqLiteDatabase.execSQL(ddl.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String ddl = "DROP DATABASE "+DB_NAME+" IF EXIST";
        sqLiteDatabase.execSQL(ddl);
    }
}
