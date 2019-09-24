package com.beksultan.qr_pay.ui.payment.fragments;

import com.arellomobile.mvp.MvpView;
import com.beksultan.qr_pay.model.balance.Balance;

public interface BalanceView extends MvpView {

    void onSetBalance(Balance balance);

    void onShowProgressBar(boolean isShow);

    void onError(String s);
}
