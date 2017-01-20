package br.com.bluedogs.econoapp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.*;

import java.util.ArrayList;
import java.util.Stack;

import br.com.bluedogs.econoapp.R;
import br.com.bluedogs.econoapp.db.*;
import br.com.bluedogs.econoapp.model.*;

public class PrincipalActivity extends AppCompatActivity {
    private TextView txwState,txwValue,txwHistoryResult;
    private Button btnAdd,btnRemove;
    private RecyclerView rcvwHistory;

    private User user;
    private double amount;
    private final String TAG = "PRINCIPAL_ACTIVITY";
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG,"Start onCreate Method");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txwState = (TextView)findViewById(R.id.main_txw_state);
        txwValue = (TextView)findViewById(R.id.main_txw_value);
        txwHistoryResult = (TextView)findViewById(R.id.main_txw_history_result);
        btnAdd = (Button)findViewById(R.id.main_btn_add);
        btnRemove = (Button)findViewById(R.id.main_btn_remove);
        rcvwHistory = (RecyclerView)findViewById(R.id.main_rcvw_history);

        user = UserDAO.getUser(getApplicationContext());

        if(user.getName().isEmpty()){
            // TODO: 13/01/2017 Create custom alert to get user's name
            user.setName("Matheus");//teste
            UserDAO.insert(getApplicationContext(),user);
            Log.i(TAG,"Data Object Access Called!");
            Log.i(TAG,"Database Insertion Called!");
        }else{
            txwValue.setText("R$"+String.valueOf(user.getFunds()));
            Toast.makeText(getApplicationContext(),"Name: "+user.getName(),Toast.LENGTH_SHORT).show();
            Log.i(TAG,"Data Object Access Called!");
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 11/01/2017 [OK]Create a custom alert to get user's value
                stdDialog(true);
                Log.i(TAG,"btnAdd Click Called!");
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 11/01/2017 [OK]Create a custom alert to get user's value
                stdDialog(false);
                Log.i(TAG,"btnRemove Click Called!");
            }
        });
        // TODO: 11/01/2017 Get the first 10 last operations from database and create a list
    }

    private void updateList() {
        ArrayList<Operation> operationList = (ArrayList<Operation>) OperationDAO.getOperations(getApplicationContext());
        if(operationList.size() == 0)
            txwHistoryResult.setVisibility(View.VISIBLE);
        else{
            txwHistoryResult.setVisibility(View.GONE);
            Stack<Operation> stack = new Stack<>();
            Operation[]operations = (operationList.size() < br.com.bluedogs.econoapp.activity.view_components.Adapter.DEFAULT_ITENS_NUMBER) ?
                    new Operation[operationList.size()] : new Operation[br.com.bluedogs.econoapp.activity.view_components.Adapter.DEFAULT_ITENS_NUMBER];
            for(Operation it: operationList) stack.push(it);
            for(int i = 0; !stack.empty() && i< br.com.bluedogs.econoapp.activity.view_components.Adapter.DEFAULT_ITENS_NUMBER; i++) operations[i] = stack.pop();
            adapter = new br.com.bluedogs.econoapp.activity.view_components.Adapter(operations);
            rcvwHistory.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onStart() {
        Log.i(TAG,"Start onStart Method");
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"Start onResume Method");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        updateList();
        rcvwHistory.setLayoutManager(layoutManager);
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
                OperationDAO.insert(getApplicationContext(),user.getLastOperation());
                UserDAO.alter(getApplicationContext(),user);
                txwValue.setText("R$"+user.getFunds());
                Log.i(TAG,"User's row update called!");
                amount = 0;
                updateList();
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
