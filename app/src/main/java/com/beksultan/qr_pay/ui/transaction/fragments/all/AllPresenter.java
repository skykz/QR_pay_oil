package com.beksultan.qr_pay.ui.transaction.fragments.all;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.model.transaction.TransactionResponse;
import com.beksultan.qr_pay.model.transaction.param.AllParam;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class AllPresenter extends MvpPresenter<AllView>{

    public int page = 1;

    public void getTransactions(AllParam param, boolean isLoadMore){

        if(isLoadMore){
            page++;
        }
        else {
            page = 1;
        }
        param.page = String.valueOf(page);

        App.getInstance().getApiService().getAllTransactionList(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((v) -> showLoadingIndicator(true, isLoadMore))
                .doAfterTerminate(() -> showLoadingIndicator(false, isLoadMore))
                .subscribe(new SingleObserver<TransactionResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(TransactionResponse transactionResponse) {
                        if(isLoadMore){
                            getViewState().addTransactionList(transactionResponse.transactionList);
                        }
                        else{
                            getViewState().onSetTransactionList(transactionResponse.transactionList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onShowError(e.getMessage());
                    }
                });
    }

    private void showLoadingIndicator(boolean isShow, boolean isLoadMore){
        if(isLoadMore){
            getViewState().onShowProgressBarLoadMore(isShow);
        }
        else{
            getViewState().onShowProgressBar(isShow);
        }
    }
}
