package com.beksultan.qr_pay.ui.business.head.add;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.model.business.head.add.SearchStaff;
import com.beksultan.qr_pay.model.business.head.param.AddStaffParam;
import com.beksultan.qr_pay.model.business.head.param.SearchStaffParam;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class AddStaffPresenter extends MvpPresenter<AddStaffView> {

    public void searchStaff(SearchStaffParam param) {
        App.getInstance().getApiService().searchStaff(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((v) -> getViewState().onShowProgressBar())
                .doAfterTerminate(() -> getViewState().onHideProgressBar())
                .subscribe(new SingleObserver<SearchStaff>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(SearchStaff searchStaff) {
                        getViewState().onSetUpRecyclerView(searchStaff.headStaffList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onError(e.toString());
                    }
                });
    }

    public void addStaff(AddStaffParam param) {
        App.getInstance().getApiService().addStaff(param)
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
                        getViewState().onSuccessCreated(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onError(e.getMessage());
                    }
                });
    }
}
