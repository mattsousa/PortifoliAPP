package br.com.bluedogs.econoapp.model;


/**
 * Created by Sarah Francis on 11/01/2017.
 */

public class User {
    private int id;
    private String name;
    private double funds;

    public static final String DATE_FORMAT = "";

    public User() {
        id = 0;
        name = "";
        funds = 0.0;
    }
    /**
     * This method adds or remove funds.
     * Then, it register this operation on history
     * It calls private methods whatever the add parameter is
     * */
    public void makeOperation(double value,boolean add){
    }

    private void addFunds(double value){}
    private void removeFunds(double value){}

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