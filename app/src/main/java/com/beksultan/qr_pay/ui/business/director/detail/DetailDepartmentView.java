package com.beksultan.qr_pay.ui.business.director.detail;

import com.arellomobile.mvp.MvpView;
import com.beksultan.qr_pay.model.business.director.detail.Staff;
import java.util.List;

public interface DetailDepartmentView extends MvpView {

    void onSetUpRecyclerView(List<Staff> staffList);

    void onHideProgressBar();

    void onShowProgressBar();

    void onError(String message);
}
