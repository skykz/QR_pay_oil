package com.beksultan.qr_pay.ui.base.stafftransaction;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.model.transaction.Transaction;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StaffTransactionAdapter extends RecyclerView.Adapter<StaffTransactionAdapter.StaffHolder> {

    public List<Transaction> transactionList;

    public StaffTransactionAdapter(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public StaffHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_transaction, parent, false);

        return new StaffHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffHolder holder, int position) {
        holder.tv_date.setText(transactionList.get(position).date);
        holder.tv_price.setText(transactionList.get(position).price);
        holder.tv_department_name.setText(transactionList.get(position).departmentName);
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class StaffHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_date)
        TextView tv_date;
        @BindView(R.id.tv_price)
        TextView tv_price;
        @BindView(R.id.tv_department_name)
        TextView tv_department_name;

        public StaffHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
