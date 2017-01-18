package br.com.bluedogs.econoapp.activity.view_components.dialogs;

import android.app.Dialog;
import android.content.Context;

/**
 * Created by CTIC on 18/01/2017.
 */

public interface ActivityDialog{
    Dialog createDialog(Context context);
    void showDialog();
}