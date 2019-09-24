package com.beksultan.qr_pay.ui.business.head.detail;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.db.BalancePreferences;
import com.beksultan.qr_pay.db.DepartmentBalancePreference;
import com.beksultan.qr_pay.model.balance.Balance;
import com.beksultan.qr_pay.model.balance.DepartmentBalance;
import com.beksultan.qr_pay.model.business.director.Department;
import com.beksultan.qr_pay.model.business.head.DepartmentHead;
import com.beksultan.qr_pay.model.business.head.DepartmentStaff;
import com.beksultan.qr_pay.model.business.head.param.DeleteStaffParam;
import com.beksultan.qr_pay.model.business.head.param.DepartmentHeadParam;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class DepartmentStaffPresenter extends MvpPresenter<DepartmentStaffView> {

    public void getStaff(DepartmentHeadParam param) {
        App.getInstance().getApiService().getHead(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((v) -> getViewState().onShowProgressBar())
                .doAfterTerminate(() -> getViewState().onHideProgressBar())
                .subscribe(new SingleObserver<DepartmentHead>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(DepartmentHead departmentHead) {
                        DepartmentBalancePreference.set(departmentHead);
                        getViewState().onSetUpRecyclerView(departmentHead.departmentStaffList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onError(e.getMessage());
                    }
                });
    }

    public void deleteStaff(DepartmentStaff departmentStaff, String departmentId) {
        App.getInstance().getApiService().deleteStaff(new DeleteStaffParam(departmentStaff.id, departmentId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((v) -> getViewState().onShowProgressBar())
                .doAfterTerminate(() -> getViewState().onHideProgressBar())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        getViewState().onDeleteDepartment(departmentStaff);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onError(e.getMessage());
                    }
                });
    }
}
