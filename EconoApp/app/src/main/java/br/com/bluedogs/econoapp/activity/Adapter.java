package br.com.bluedogs.econoapp.activity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import br.com.bluedogs.econoapp.R;
import br.com.bluedogs.econoapp.model.Operation;

/**
 * Created by Sarah Francis on 15/01/2017.
 */

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
        holder.btnListMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 15/01/2017 Create a new dialog showing description and some options for removing or editing
            }
        });
        // TODO: 15/01/2017 Setting a value color according to the operation
        holder.txwListValue.setText(String.valueOf(operations[position].getValue()));
        holder.txwListDate.setText(operations[position].getDateAndTime());
    }


    @Override
    public int getItemCount() {
        return operations.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txwListValue,txwListDate;
        private Button btnListMore;
        public ViewHolder(View itemView) {
            super(itemView);
            txwListDate = (TextView)itemView.findViewById(R.id.recycler_txw_date);
            txwListValue = (TextView)itemView.findViewById(R.id.recycler_txw_value);
            btnListMore = (Button)itemView.findViewById(R.id.recycler_btn_more);
        }
    }
}
