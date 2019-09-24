package com.beksultan.qr_pay.ui.business.head.detail;

import com.arellomobile.mvp.MvpView;
import com.beksultan.qr_pay.model.business.head.DepartmentStaff;

import java.util.List;

public interface DepartmentStaffView extends MvpView{

    void onSetUpRecyclerView(List<DepartmentStaff> departmentStaffList);

    void onDeleteDepartment(DepartmentStaff departmentStaff);

    void onHideProgressBar();

    void onShowProgressBar();

    void onError(String message);

}
