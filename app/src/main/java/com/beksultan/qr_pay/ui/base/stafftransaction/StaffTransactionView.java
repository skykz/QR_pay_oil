package com.beksultan.qr_pay.ui.base.stafftransaction;

import com.arellomobile.mvp.MvpView;
import com.beksultan.qr_pay.model.transaction.Transaction;
import java.util.List;

public interface StaffTransactionView extends MvpView {

    void onSetUpRecyclerView(List<Transaction> transactionList);

    void onShowProgressBar();

    void onHideProgressBar();

    void onError(String error);
}
