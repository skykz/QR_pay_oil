package com.beksultan.qr_pay.ui.business.head;

import com.arellomobile.mvp.MvpView;
import com.beksultan.qr_pay.model.business.head.DepartmentHead;

public interface HeadView extends MvpView {

    void onSetUpHead(DepartmentHead departmentHead);

    void onHideProgressBar();

    void onShowProgressBar();

    void onError(String message);
}
