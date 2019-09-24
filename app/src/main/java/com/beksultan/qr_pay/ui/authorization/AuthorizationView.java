package com.beksultan.qr_pay.ui.authorization;

import com.arellomobile.mvp.MvpView;

interface AuthorizationView extends MvpView {

    void onSuccessSignIn();

    void onFailedApiService(String error);
}

