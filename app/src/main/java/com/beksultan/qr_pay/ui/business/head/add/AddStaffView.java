package com.beksultan.qr_pay.ui.business.head.add;

import com.arellomobile.mvp.MvpView;
import com.beksultan.qr_pay.model.business.head.add.HeadStaff;

import java.util.List;

public interface AddStaffView extends MvpView {

    void onSetUpRecyclerView(List<HeadStaff> headStaffList);

    void onSuccessCreated(String s);

    void onShowProgressBar();

    void onHideProgressBar();

    void onError(String string);
}
