package com.beksultan.qr_pay.ui.registration;

import com.arellomobile.mvp.MvpView;

public interface RegistrationView extends MvpView {

    void onSuccessRegistered();

    void onFailedApiService(String error);
}
