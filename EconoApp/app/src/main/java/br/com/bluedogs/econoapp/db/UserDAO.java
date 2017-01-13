package br.com.bluedogs.econoapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


import br.com.bluedogs.econoapp.model.User;

/**
 * Created by Sarah Francis on 13/01/2017.
 */

public class UserDAO {
    private static final String coluns[] = {"_id","nome","quantia"};

    public static void insert(Context context, User user){
        DAO dao = DAO.getInstance(context);
        ContentValues values = new ContentValues();
        int i = 1;
        values.put(coluns[i],user.getName());
        i+=1;
        values.put(coluns[i],user.getFunds());
        dao.getWritableDatabase().insert(DAO.TABELAS[0],null,values);
    }

    public static User getUser(Context context){
        User user = new User();
        DAO dao = DAO.getInstance(context);
        String dql = "SELECT "+coluns[0]+", "+coluns[1]+", "+coluns[2]+
                    " FROM "+DAO.TABELAS[0];
        Cursor cursor = dao.getReadableDatabase().rawQuery(dql,null);
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setFunds(cursor.getDouble(2));
            }
        }
        return user;
    }

    // TODO: 13/01/2017 Make a method called "alter" that receives Context, int and User params
}