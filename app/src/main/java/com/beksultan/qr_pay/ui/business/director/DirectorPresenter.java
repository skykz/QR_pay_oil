package com.beksultan.qr_pay.ui.business.director;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.db.CompanyPreferences;
import com.beksultan.qr_pay.model.business.director.Department;
import com.beksultan.qr_pay.model.business.director.param.DepartmentParam;
import com.beksultan.qr_pay.model.business.director.Company;
import com.beksultan.qr_pay.model.business.director.param.DeleteParam;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class DirectorPresenter extends MvpPresenter<DirectorView> {

    public void getDepartmentList(DepartmentParam param) {
        App.getInstance().getApiService().getDepartmentList(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((v) -> getViewState().onShowProgressBar())
                .doAfterTerminate(() -> getViewState().onHideProgressBar())
                .subscribe(new SingleObserver<Company>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(Company company) {
                        CompanyPreferences.set(company);
                        getViewState().onSetUpRecyclerView(company.departmentList, company);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onError(e.getMessage());
                    }
                });
    }

    public void deleteDepartment(Department department) {
        App.getInstance().getApiService().deleteDepartment(new DeleteParam(department.id))
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
                        getViewState().onDeleteDepartment(department);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onError(e.getMessage());
                    }
                });
    }
}
