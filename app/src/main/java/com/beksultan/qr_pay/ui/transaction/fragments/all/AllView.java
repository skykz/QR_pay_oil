package com.beksultan.qr_pay.ui.transaction.fragments.all;

import com.arellomobile.mvp.MvpView;
import com.beksultan.qr_pay.model.transaction.Transaction;

import java.util.List;

public interface AllView extends MvpView {

    void onShowProgressBar(boolean isShow);

    void onShowProgressBarLoadMore(boolean isShow);

    void onSetTransactionList(List<Transaction> transactionList);

    void addTransactionList(List<Transaction> transactionList);

    void onShowError(String error);

}
