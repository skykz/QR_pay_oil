package com.beksultan.qr_pay.ui.transaction.fragments.company;

import com.arellomobile.mvp.MvpView;
import com.beksultan.qr_pay.model.transaction.Transaction;
import java.util.List;

public interface CompanyView extends MvpView {

    void onCompanyTransactions(List<Transaction> transactionList);

    void onShowProgressBar();

    void onHideProgressBar();

    void onError(String error);
}
