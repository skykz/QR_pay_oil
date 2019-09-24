package com.beksultan.qr_pay.ui.business.director.add;

import com.arellomobile.mvp.MvpView;
import com.beksultan.qr_pay.model.business.director.addDepartment.Head;
import java.util.List;

public interface AddDepartmentView extends MvpView {

    void onSetUpRecyclerView(List<Head> headList);

    void onCreatedDepartment(String string);

    void onHideProgressBar();

    void onShowProgressBar();

    void onError(String message);
}
