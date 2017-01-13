package br.com.bluedogs.econoapp.model;

/**
 * Created by Sarah Francis on 12/01/2017.
 */

public class Operation {
    private long id;
    private String dateAndTime;
    private OperationType type;
    private double value;


    public Operation() {
        id = 0;
        dateAndTime = "";
        type = null;
        value = 0.0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
