package com.beksultan.qr_pay.ui.business.director.detail;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.model.business.director.detail.DepartmentItem;
import com.beksultan.qr_pay.model.business.director.param.DetailDepartmentParam;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class DetailDepartmentPresenter extends MvpPresenter<DetailDepartmentView> {
    public void getDepartmentStaff(DetailDepartmentParam param){
        App.getInstance().getApiService().getDepartmentStaff(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((v) -> getViewState().onShowProgressBar())
                .doAfterTerminate(() -> getViewState().onHideProgressBar())
                .subscribe(new SingleObserver<DepartmentItem>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(DepartmentItem departmentItem) {
                        getViewState().onSetUpRecyclerView(departmentItem.staffList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onError(e.getMessage());
                    }
                });
    }
}
