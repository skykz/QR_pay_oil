package com.beksultan.qr_pay.ui.payment.fragments;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.model.balance.Balance;
import com.beksultan.qr_pay.model.balance.BalanceParam;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class PaymentPresenter extends MvpPresenter<BalanceView> {

    public void onShowBalance(BalanceParam param) {
        App.getInstance().getApiService().getBalance(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((v) -> getViewState().onShowProgressBar(true))
                .doAfterTerminate(() -> getViewState().onShowProgressBar(false))
                .subscribe(new SingleObserver<Balance>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Balance balance) {
                        getViewState().onSetBalance(balance);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onError(e.getMessage());
                    }
                });

    }
}
