package com.beksultan.qr_pay.ui.qrScanner;

import com.arellomobile.mvp.MvpView;

public interface QrView  extends MvpView {

    void onSuccessQrData();

    void onHideProgressBar();

    void onShowProgressBar();
    
    void onError(String message);
}
