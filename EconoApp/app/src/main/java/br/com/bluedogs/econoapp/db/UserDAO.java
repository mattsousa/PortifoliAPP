package br.com.bluedogs.econoapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;


import br.com.bluedogs.econoapp.model.User;


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
            do{
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setFunds(cursor.getDouble(2));
                Log.i("PRINCIPAL_ACTIVITY","");
                Log.i("PRINCIPAL_ACTIVITY","USER DATABASE");
                Log.i("PRINCIPAL_ACTIVITY","");
                Log.i("PRINCIPAL_ACTIVITY","ID:\t\t"+user.getId());
                Log.i("PRINCIPAL_ACTIVITY","NAME:\t\t"+user.getName());
                Log.i("PRINCIPAL_ACTIVITY","FUNDS:\t\t"+user.getFunds());
                Log.i("PRINCIPAL_ACTIVITY","");

            }while(cursor.moveToNext());
        }
        cursor.close();
        return user;
    }

    // TODO: 13/01/2017 Make a method called "alter" that receives a Context and User params
    public static void alter(Context context,User user){
        DAO dao = DAO.getInstance(context);
        ContentValues values = new ContentValues();
        values.put(coluns[1],user.getName());
        values.put(coluns[2],user.getFunds());
        dao.getWritableDatabase().update(DAO.TABELAS[0],values,null,null);
    }
}
