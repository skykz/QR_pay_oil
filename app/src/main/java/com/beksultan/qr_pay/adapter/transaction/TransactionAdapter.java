package com.beksultan.qr_pay.adapter.transaction;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.model.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionHolder> {

    private List<Transaction> mTransactionList;

    public TransactionAdapter() {
        mTransactionList = new ArrayList<>();
    }

    public void set(List<Transaction> transactionList){
        mTransactionList.clear();
        mTransactionList.addAll(transactionList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransactionAdapter.TransactionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_transaction,parent,false);

        return new TransactionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.TransactionHolder holder, int position) {
        holder.tv_date.setText(mTransactionList.get(position).date);
        holder.tv_price.setText(mTransactionList.get(position).price);
        holder.tv_department_name.setText(mTransactionList.get(position).departmentName);
    }

    @Override
    public int getItemCount() {
        return mTransactionList.size();
    }

    public class TransactionHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_date)
        TextView tv_date;

        @BindView(R.id.tv_price)
        TextView tv_price;

        @BindView(R.id.tv_department_name)
        TextView tv_department_name;

        private TransactionHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
