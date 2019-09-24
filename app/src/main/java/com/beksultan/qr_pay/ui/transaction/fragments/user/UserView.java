package com.beksultan.qr_pay.ui.transaction.fragments.user;

import com.arellomobile.mvp.MvpView;
import com.beksultan.qr_pay.model.transaction.Transaction;
import java.util.List;

public interface UserView extends MvpView {

    void onSetUserTransactions(List<Transaction> transactionList);

    void onShowEmpty();

    void onHideEmpty();

    void onShowProgressBar();

    void onHideProgressBar();

    void onError(String error);
}
