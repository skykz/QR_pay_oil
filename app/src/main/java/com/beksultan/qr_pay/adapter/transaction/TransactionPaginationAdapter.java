package com.beksultan.qr_pay.adapter.transaction;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.adapter.base.BaseRViewAdapter;
import com.beksultan.qr_pay.model.transaction.Transaction;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TransactionPaginationAdapter extends BaseRViewAdapter {

    private static final int LAYOUT_ID = R.layout.row_transaction;
    private MenuItemsClickCallback mCallback;

    public TransactionPaginationAdapter() {
    }

    @Override
    public void setLoading(boolean loading) {
        super.setLoading(loading);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case LAYOUT_ID:
                return new ItemViewHolder(inflate(parent, LAYOUT_ID));
            case PROGRESS_BAR_LAYOUT_ID:
                return new SimpleViewHolder(inflate(parent, PROGRESS_BAR_LAYOUT_ID));
            default:
                throw incorrectOnCreateViewHolder();

        }
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case LAYOUT_ID:
                ((ItemViewHolder) holder).bind((Transaction) data.get(position));
                break;
        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.row_transaction;
    }

    protected class ItemViewHolder extends MainViewHolder {
        @BindView(R.id.tv_date)
        TextView tv_date;
        @BindView(R.id.tv_price)
        TextView tv_price;
        @BindView(R.id.tv_department_name)
        TextView tv_department_name;
        private Transaction binded;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Transaction transaction) {
            binded = transaction;
            tv_date.setText(transaction.date);
            tv_price.setText(transaction.price);
            tv_department_name.setText(transaction.departmentName);
        }
    }

    private interface MenuItemsClickCallback {
        void onItemClick();
    }

}
