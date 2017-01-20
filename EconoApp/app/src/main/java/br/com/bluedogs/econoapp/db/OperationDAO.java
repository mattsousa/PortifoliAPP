package br.com.bluedogs.econoapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bluedogs.econoapp.model.Operation;
import br.com.bluedogs.econoapp.model.SimpleAddingOperation;
import br.com.bluedogs.econoapp.model.SimpleRemovingOperation;
import br.com.bluedogs.econoapp.model.User;

/**
 * Created by Sarah Francis on 13/01/2017.
 */

public class OperationDAO{

    private static final String coluns[] = {"_id","dataEHora","tipo","valor"};

    public static void insert(Context context,Operation operation){
        DAO dao = DAO.getInstance(context);
        ContentValues values = new ContentValues();
        int i = 1;
        values.put(coluns[i], operation.getDateAndTime());
        i+=1;
        if(operation.getType().getOperationType() == new SimpleAddingOperation().getOperationType()){
            values.put(coluns[i],""+new SimpleAddingOperation().getOperationType());
        }else if(operation.getType().getOperationType() == new SimpleRemovingOperation().getOperationType()){
            values.put(coluns[i],""+new SimpleRemovingOperation().getOperationType());
        }
        i+=1;
        values.put(coluns[i], operation.getValue());
        dao.getWritableDatabase().insert(DAO.TABELAS[1],null,values);
    }

    public static Operation getOperationByID(Context context,int id){
        DAO dao = DAO.getInstance(context);
        Operation operation = new Operation();
        String dql = "SELECT "+
                coluns[0]+" , "+
                coluns[1]+" , "+
                coluns[2]+" , "+
                coluns[3]+" "+
                " FROM "+DAO.TABELAS[1]+
                "WHERE "+coluns[0]+" = 0";
        Cursor cursor = dao.getReadableDatabase().rawQuery(dql,null);
        if(cursor.moveToFirst()){
            while(cursor.moveToNext()){
                String tipo = cursor.getString(cursor.getColumnIndex(coluns[2]));
                operation.setId(cursor.getInt(cursor.getColumnIndex(coluns[0])));
                operation.setDateAndTime(cursor.getString(cursor.getColumnIndex(coluns[1])));
                // TODO: 19/01/2017 Create a factory to Operation supertype
                if (tipo.equalsIgnoreCase(new SimpleAddingOperation().getOperationType()+"")){
                    operation.setType(new SimpleAddingOperation());
                }else if(tipo.equalsIgnoreCase(new SimpleRemovingOperation().getOperationType()+"")){
                    operation.setType(new SimpleAddingOperation());
                }
                operation.setValue(cursor.getDouble(cursor.getColumnIndex(coluns[3])));
            }
        }
        return operation;
    }

    public static List<Operation> getOperations(Context context){
        DAO dao = DAO.getInstance(context);
        ArrayList<Operation> operations = new ArrayList<>();
        String dql = "SELECT "+
                coluns[0]+" , "+
                coluns[1]+" , "+
                coluns[2]+" , "+
                coluns[3]+" "+
                " FROM "+DAO.TABELAS[1];
        Cursor cursor = dao.getReadableDatabase().rawQuery(dql,null);
        if(cursor.moveToFirst()){
            do{
                Operation operation = new Operation();
                String tipo = cursor.getString(cursor.getColumnIndex(coluns[2]));
                operation.setId(cursor.getInt(cursor.getColumnIndex(coluns[0])));
                operation.setDateAndTime(cursor.getString(cursor.getColumnIndex(coluns[1])));
                // TODO: 19/01/2017 Create a factory to Operation supertype
                if (tipo.equalsIgnoreCase(new SimpleAddingOperation().getOperationType()+"")){
                    operation.setType(new SimpleAddingOperation());
                }else if(tipo.equalsIgnoreCase(new SimpleRemovingOperation().getOperationType()+"")){
                    operation.setType(new SimpleRemovingOperation());
                }
                operation.setValue(cursor.getDouble(cursor.getColumnIndex(coluns[3])));
                operations.add(operation);
            }while(cursor.moveToNext());
        }
        return operations;
    }

    public static List<Operation> getOperationsByDate(Context context,Date date){
        DAO dao = DAO.getInstance(context);
        ArrayList<Operation> operations = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat(User.DATE_FORMAT);
        String dql = "SELECT "+
                coluns[0]+" , "+
                coluns[1]+" , "+
                coluns[2]+" , "+
                coluns[3]+" "+
                " FROM "+DAO.TABELAS[1]+
                "WHERE "+coluns[1]+" = "+format.format(date);
        Cursor cursor = dao.getReadableDatabase().rawQuery(dql,null);
        if(cursor.moveToFirst()){
            do{
                Operation operation = new Operation();
                operation.setId(cursor.getInt(cursor.getColumnIndex(coluns[0])));
                operation.setDateAndTime(cursor.getString(cursor.getColumnIndex(coluns[1])));
                switch (cursor.getExtras().getByte(coluns[2])){
                    case 'a':
                        operation.setType(new SimpleAddingOperation());
                        break;
                    case 'r':
                        operation.setType(new SimpleRemovingOperation());
                }
                operation.setValue(cursor.getDouble(cursor.getColumnIndex(coluns[3])));
                operations.add(operation);
            }while(cursor.moveToNext());
        }
        return operations;
    }

    public static List<Operation> getOperationsByValue(Context context,double value){
        DAO dao = DAO.getInstance(context);
        ArrayList<Operation> operations = new ArrayList<>();
        String dql = "SELECT "+
                coluns[0]+" , "+
                coluns[1]+" , "+
                coluns[2]+" , "+
                coluns[3]+" "+
                " FROM "+DAO.TABELAS[1]+
                " WHERE "+coluns[3]+" = "+value;
        Cursor cursor = dao.getReadableDatabase().rawQuery(dql,null);
        ContentValues values = new ContentValues();
        if(cursor.moveToFirst()){
            do{
                Operation operation = new Operation();
                operation.setId(cursor.getInt(cursor.getColumnIndex(coluns[0])));
                operation.setDateAndTime(cursor.getString(cursor.getColumnIndex(coluns[1])));
                switch (cursor.getExtras().getByte(coluns[2])){
                    case 'a':
                        operation.setType(new SimpleAddingOperation());
                        break;
                    case 'r':
                        operation.setType(new SimpleRemovingOperation());
                }
                operation.setValue(cursor.getDouble(cursor.getColumnIndex(coluns[3])));
                operations.add(operation);
            }while(cursor.moveToNext());
        }
        return operations;
    }
}
