package com.beksultan.qr_pay.ui;

import com.arellomobile.mvp.MvpView;
import com.beksultan.qr_pay.model.balance.Balance;

public interface MainView extends MvpView {

    void onShowBalanceAndRole(Balance balance);
}
