package br.com.bluedogs.econoapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.bluedogs.econoapp.R;
import br.com.bluedogs.econoapp.model.User;

public class PrincipalActivity extends AppCompatActivity {
    private TextView txwState,txwValue,txwHistoryResult;
    private Button btnAdd,btnRemove;
    private RecyclerView rcvwHistory;

    private User user;
    private double amount;
    private final String TAG = "PRINCIPAL_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = new User();

        txwState = (TextView)findViewById(R.id.main_txw_state);
        txwValue = (TextView)findViewById(R.id.main_txw_value);
        txwHistoryResult = (TextView)findViewById(R.id.main_txw_history_result);
        btnAdd = (Button)findViewById(R.id.main_btn_add);
        btnRemove = (Button)findViewById(R.id.main_btn_remove);
        rcvwHistory = (RecyclerView)findViewById(R.id.main_rcvw_history);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 11/01/2017 Create a custom alert to get user's value
                stdDialog(true);
                Log.i(TAG,"btnAdd Click Called!");
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 11/01/2017 Create a custom alert to get user's value
                stdDialog(false);
                Log.i(TAG,"btnRemove Click Called!");
            }
        });
        // TODO: 11/01/2017 Get the first 10 last operations from database and create a list
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Log.i(TAG,"Settings Click Called!");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected AlertDialog stdDialog(final boolean add){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View v = inflater.inflate(R.layout.dialog_principal_operation,null);
        final TextView txwAmount = (TextView)v.findViewById(R.id.main_txw_amount);
        final EditText edtCustom = ((EditText)v.findViewById(R.id.main_edt_custom));
        final Button btnCustomOk = (Button)v.findViewById(R.id.main_btn_custom_ok);
        builder.setView(v);

        builder.setTitle(R.string.main_dialog_title);

        builder.setPositiveButton(R.string.main_dialog_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i(TAG,"Dialog Operation OK clicked!");
                user.makeOperation(amount,add);
                txwValue.setText("R$"+user.getFunds());
                amount = 0;
            }
        }).setNegativeButton(R.string.main_dialog_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.i(TAG,"Dialog Operation Cancel clicked!");
                amount = 0;
                txwAmount.setText(R.string.main_amount);
            }
        });
        ((Button)v.findViewById(R.id.main_btn_m1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += 1;
                txwAmount.setText("R$"+String.valueOf(amount));
                Log.i(TAG,"Dialog Operation +1 Button clicked!");
            }
        });
        ((Button)v.findViewById(R.id.main_btn_m5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += 5;
                txwAmount.setText("R$"+String.valueOf(amount));
                Log.i(TAG,"Dialog Operation +5 Button clicked!");
            }
        });
        ((Button)v.findViewById(R.id.main_btn_m10)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += 10;
                txwAmount.setText("R$"+String.valueOf(amount));
                Log.i(TAG,"Dialog Operation +10 Button clicked!");
            }
        });
        ((Button)v.findViewById(R.id.main_btn_m100)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += 100;
                txwAmount.setText("R$"+String.valueOf(amount));
                Log.i(TAG,"Dialog Operation +100 Button clicked!");
            }
        });
        ((Button)v.findViewById(R.id.main_btn_mcustom)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtCustom.setVisibility(View.VISIBLE);
                btnCustomOk.setVisibility(View.VISIBLE);
                Log.i(TAG,"Dialog Operation Custom Button clicked!");
            }
        });
        ((Button)v.findViewById(R.id.main_btn_custom_ok)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount += Double.parseDouble(edtCustom.getText().toString());
                txwAmount.setText("R$"+String.valueOf(amount));
                Log.i(TAG,"Dialog Operation Custom OK Button clicked!");
            }
        });

        return builder.show();
    }
}
