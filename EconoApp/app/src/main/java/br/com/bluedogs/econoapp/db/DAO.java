package br.com.bluedogs.econoapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sarah Francis on 11/01/2017.
 */

public class DAO extends SQLiteOpenHelper{

    private static DAO instance;

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "EconoApp";

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

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
