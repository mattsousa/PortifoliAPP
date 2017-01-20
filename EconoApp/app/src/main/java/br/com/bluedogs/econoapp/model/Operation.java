package br.com.bluedogs.econoapp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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

    public String getRecyclerViewDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(User.DATE_FORMAT);
        SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateDatabase = format.parse(getDateAndTime()),
            dateNow = new Date();
        long miliseconds = dateNow.getTime() - dateDatabase.getTime();
        String dateFormat = "";
        if(TimeUnit.MILLISECONDS.toSeconds(miliseconds) < 60){
            dateFormat = TimeUnit.MILLISECONDS.toSeconds(miliseconds)+" Seconds";
        }else if(TimeUnit.MILLISECONDS.toMinutes(miliseconds) < 60){
            dateFormat = TimeUnit.MILLISECONDS.toMinutes(miliseconds)+" Minutes";
        }else if(TimeUnit.MILLISECONDS.toHours(miliseconds) < 24){
            dateFormat = TimeUnit.MILLISECONDS.toHours(miliseconds)+" Hours";
        }else{
            dateFormat = "Created at "+newFormat.format(dateDatabase);
        }
        return dateFormat;
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
