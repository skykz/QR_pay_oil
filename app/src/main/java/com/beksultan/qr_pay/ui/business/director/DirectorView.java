package com.beksultan.qr_pay.ui.business.director;

import com.arellomobile.mvp.MvpView;
import com.beksultan.qr_pay.model.business.director.Company;
import com.beksultan.qr_pay.model.business.director.Department;
import java.util.List;

public interface DirectorView extends MvpView {

    void onSetUpRecyclerView(List<Department> department, Company company);

    void onDeleteDepartment(Department department);

    void onHideProgressBar();

    void onShowProgressBar();

    void onError(String message);
}
