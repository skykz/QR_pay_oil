package com.beksultan.qr_pay.ui.business.head;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.db.DepartmentBalancePreference;
import com.beksultan.qr_pay.model.business.head.DepartmentHead;
import com.beksultan.qr_pay.model.business.head.param.DepartmentHeadParam;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class HeadPresenter extends MvpPresenter<HeadView>{

    public void getHead(DepartmentHeadParam param){
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
                        getViewState().onSetUpHead(departmentHead);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onError(e.getMessage());
                    }
                });

    }
}
