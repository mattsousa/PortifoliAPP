package br.com.bluedogs.econoapp.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import br.com.bluedogs.econoapp.model.operation.SimpleAddingOperation;
import br.com.bluedogs.econoapp.model.operation.SimpleRemovingOperation;

/**
 * Created by Sarah Francis on 11/01/2017.
 */

public class User {
    private int id;
    private String name;
    private double funds;
    private List<Operation> history;

    public static final String DATE_FORMAT = "yyyy.MM.dd ; HH:mm:ss";

    public User() {
        id = 0;
        name = "";
        funds = 0.0;
        history = new LinkedList<>();
    }
    /**
     * This method adds or remove funds.
     * Then, it register this operation on history
     * It calls private methods whatever the add parameter is
     * */
    public void makeOperation(double value,boolean add) {
        Operation operation = new Operation();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        operation.setDateAndTime(format.format(new Date()));
        operation.setValue(value);
        if (add) {
            addFunds(value);
            operation.setType(new SimpleAddingOperation());
        } else {
            removeFunds(value);
            operation.setType(new SimpleRemovingOperation());
        }
        history.add(operation);
    }

    public Operation getLastOperation(){
        Operation operation = history.get(history.size()-1);
        return operation;
    }

    private void addFunds(double value){
        funds += value;
    }
    private void removeFunds(double value){
        funds -= value;
    }

    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }
}