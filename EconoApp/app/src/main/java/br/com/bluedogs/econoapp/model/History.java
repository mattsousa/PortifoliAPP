package br.com.bluedogs.econoapp.model;

/**
 * Created by Sarah Francis on 11/01/2017.
 */

public class History {

    private int id;
    private int userId;
    private boolean operation;
    private double value;
    private String description;
    private String operationDate;


    public History() {
        id = 0;
        userId = 0;
        operation = true;
        value = 0.0;
        description = "";
        operationDate = "";
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isOperation() {
        return operation;
    }

    public void setOperation(boolean operation) {
        this.operation = operation;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }
}
