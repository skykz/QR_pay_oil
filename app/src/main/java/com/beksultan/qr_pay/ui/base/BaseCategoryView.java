package com.beksultan.qr_pay.ui.base;

import com.arellomobile.mvp.MvpView;

public interface BaseCategoryView extends MvpView {

    void onFailedApiService(String error);

    void onLoadingIndicator(boolean isShow);
}
