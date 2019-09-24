package com.beksultan.qr_pay.ui.business.director.pay;

import com.arellomobile.mvp.MvpView;
import com.beksultan.qr_pay.model.business.director.Department;
import java.util.List;

public interface PayView extends MvpView {

    void onSetUpRecyclerView(List<Department> departmentList);

    void onHideProgressBar();

    void onShowProgressBar();

    void onError(String message);

    void onSuccessBalance();
}
