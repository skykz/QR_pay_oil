package com.beksultan.qr_pay.ui.transaction.fragments.user;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.model.transaction.TransactionResponse;
import com.beksultan.qr_pay.model.transaction.param.UserAndCompanyParam;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class UserPresenter extends MvpPresenter<UserView> {

    public void getUserTransactionList(UserAndCompanyParam param) {

        App.getInstance().getApiService().getUserTransactionList(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((v) -> getViewState().onShowProgressBar())
                .doAfterTerminate(() -> getViewState().onHideProgressBar())
                .subscribe(new SingleObserver<TransactionResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(TransactionResponse transactionResponse) {
                        if (transactionResponse != null) {
                            getViewState().onSetUserTransactions(transactionResponse.transactionList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onError(e.getMessage());
                    }
                });
    }

}
