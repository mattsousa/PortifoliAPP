package br.com.bluedogs.econoapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class PrincipalActivity extends AppCompatActivity {
    private TextView txwState,txwValue;
    private Button btnAdd,btnRemove;
    private RecyclerView rcvwHistory;

    private final String TAG = "PRINCIPAL_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txwState = (TextView)findViewById(R.id.main_txw_state);
        txwValue = (TextView)findViewById(R.id.main_txw_value);
        btnAdd = (Button)findViewById(R.id.main_btn_add);
        btnRemove = (Button)findViewById(R.id.main_btn_remove);
        rcvwHistory = (RecyclerView)findViewById(R.id.main_rcvw_history);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 11/01/2017 Create a custom alert to get user's value
                Log.i(TAG,"btnAdd Click Called!");
            }
        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 11/01/2017 Create a custom alert to get user's value
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

}
