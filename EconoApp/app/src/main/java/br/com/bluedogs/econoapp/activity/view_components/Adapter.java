package br.com.bluedogs.econoapp.activity.view_components;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.ParseException;

import br.com.bluedogs.econoapp.R;
import br.com.bluedogs.econoapp.model.Operation;
import br.com.bluedogs.econoapp.model.operation.SimpleAddingOperation;
import br.com.bluedogs.econoapp.model.operation.SimpleRemovingOperation;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Operation[] operations;
    public static final int DEFAULT_ITENS_NUMBER = 10;
    public Adapter(Operation[] operations){
        this.operations = operations;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // TODO: 15/01/2017 [OK]Setting a value color according to the operation
        // TODO: 19/01/2017 Create a boolean method that returns if a object remove or add funds
        if(operations[position].getType() != null){
            if(operations[position].getType().getOperationType() == new SimpleAddingOperation().getOperationType()) {
                //Change this textview color to green
                holder.txwListValue.setTextColor(Color.GREEN);
                holder.txwListValue.setText("+"+String.valueOf(operations[position].getValue()));
            }
            else if(operations[position].getType().getOperationType() == new SimpleRemovingOperation().getOperationType()){
                //Change this textview color to red
                holder.txwListValue.setTextColor(Color.RED);
                holder.txwListValue.setText("-"+String.valueOf(operations[position].getValue()));
            }
        }
        try {
            holder.txwListDate.setText("");
            holder.txwListDate.setText(operations[position].getRecyclerViewDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        return operations.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txwListValue,txwListDate;
        public ViewHolder(View itemView) {
            super(itemView);
            txwListDate = (TextView)itemView.findViewById(R.id.recycler_txw_date);
            txwListValue = (TextView)itemView.findViewById(R.id.recycler_txw_value);
        }
    }
}
