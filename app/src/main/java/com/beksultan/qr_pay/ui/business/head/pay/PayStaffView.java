package com.beksultan.qr_pay.ui.business.head.pay;

import com.arellomobile.mvp.MvpView;
import com.beksultan.qr_pay.model.business.head.DepartmentStaff;

import java.util.List;

public interface PayStaffView extends MvpView {

    void onShowProgressBar();

    void onSetUpRecyclerView(List<DepartmentStaff> departmentStaffList);

    void onHideProgressBar();

    void onSuccessBalance();

    void onError(String s);
}
