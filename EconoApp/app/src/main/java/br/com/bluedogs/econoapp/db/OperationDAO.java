package br.com.bluedogs.econoapp.db;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bluedogs.econoapp.model.Operation;

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
        values.put(coluns[i], (byte) operation.getType().getOperationType());
        i+=1;
        values.put(coluns[i], operation.getValue());
        dao.getWritableDatabase().insert(DAO.TABELAS[1],null,values);
    }

    public static Operation getOperationByID(int id){
        Operation operation = new Operation();
        return operation;
    }

    public static List<Operation> getOperationsByDate(Date date){
        ArrayList<Operation> operations = new ArrayList<>();
        return operations;
    }

    public static List<Operation> getOperationsByValue(double value){
        ArrayList<Operation> operations = new ArrayList<>();
        return operations;
    }
}
