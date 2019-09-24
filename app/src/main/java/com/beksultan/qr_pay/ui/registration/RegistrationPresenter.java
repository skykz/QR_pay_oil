package com.beksultan.qr_pay.ui.registration;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.db.UserPreferences;
import com.beksultan.qr_pay.model.user.User;
import com.beksultan.qr_pay.model.user.param.RegistrationParam;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class RegistrationPresenter extends MvpPresenter<RegistrationView> {

    public void registration(RegistrationParam param) {
        App.getInstance().getApiService().registration(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(User user) {
                        if (user != null) {
                            UserPreferences.set(user);
                            getViewState().onSuccessRegistered();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onFailedApiService(e.getMessage());
                    }
                });
    }
}
