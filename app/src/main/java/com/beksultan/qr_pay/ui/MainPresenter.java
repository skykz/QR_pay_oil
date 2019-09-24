package com.beksultan.qr_pay.ui;

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
public class MainPresenter extends MvpPresenter<MainView> {

    public void getBalance(BalanceParam param){

        App.getInstance().getApiService().getBalance(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Balance>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Balance balance) {
                        if (balance.getCoperateBalance() == null){
                            balance.setCoperateBalance("-1");
                            getViewState().onShowBalanceAndRole(balance);
                        }else{
                            getViewState().onShowBalanceAndRole(balance);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

}
