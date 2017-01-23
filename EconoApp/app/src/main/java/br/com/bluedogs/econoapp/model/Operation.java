package br.com.bluedogs.econoapp.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import br.com.bluedogs.econoapp.R;
import br.com.bluedogs.econoapp.activity.PrincipalActivity;
import br.com.bluedogs.econoapp.model.operation.OperationType;

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
        SimpleDateFormat newFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        Date dateDatabase = format.parse(getDateAndTime()),
            dateNow = new Date();
        long miliseconds = dateNow.getTime() - dateDatabase.getTime();
        String dateFormat = "";
        //If the operation was created before a day the item is gonna be like
        // Example: "12 seconds ago." or "12 minutes ago."
        //Else it will be like this
        // Example: "Created at 12/10/2016"
        if(TimeUnit.MILLISECONDS.toSeconds(miliseconds) < 60){
            dateFormat = TimeUnit.MILLISECONDS.toSeconds(miliseconds)+ PrincipalActivity.resources.getString(R.string.main_seconds);
        }else if(TimeUnit.MILLISECONDS.toMinutes(miliseconds) < 60){
            dateFormat = TimeUnit.MILLISECONDS.toMinutes(miliseconds)+PrincipalActivity.resources.getString(R.string.main_minutes);
        }else if(TimeUnit.MILLISECONDS.toHours(miliseconds) < 24){
            dateFormat = TimeUnit.MILLISECONDS.toHours(miliseconds)+PrincipalActivity.resources.getString(R.string.main_hours);
        }else{
            dateFormat = PrincipalActivity.resources.getString(R.string.main_days)+" "+newFormat.format(dateDatabase);
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
