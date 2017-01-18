package br.com.bluedogs.econoapp.activity.view_components.dialogs;

/**
 * Created by CTIC on 18/01/2017.
 */

public class DialogFactory {
    private DialogFactory() {
    }

    public static ActivityDialog makeNewDialog(String type){
        if(type.equalsIgnoreCase("DLG-0")){
            return new ActivityValueDialog();
        }else if(type.equalsIgnoreCase("DLG-1")){
            return new ActivityHistoryDialog();
        }else if(type.equalsIgnoreCase("DLG-2")){
            return new ActivityUserDialog();
        }

        return null;
    }
}
