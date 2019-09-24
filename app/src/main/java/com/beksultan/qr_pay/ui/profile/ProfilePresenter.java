package com.beksultan.qr_pay.ui.profile;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.db.UserPreferences;
import com.beksultan.qr_pay.model.user.User;
import com.beksultan.qr_pay.model.user.param.UpdateUserParam;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class ProfilePresenter extends MvpPresenter<ProfileView> {

    public void onInit(){
        User user = UserPreferences.get();
        getViewState().onInit(user);
    }

    public void onUpdateUser(final UpdateUserParam updateUserParam){

       Single<Boolean> single = App.getInstance().getApiService().updateUser(updateUserParam)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((v) -> getViewState().onLoadingIndicator(true))
                .doAfterTerminate(()-> getViewState().onLoadingIndicator(false))
                .doOnSuccess((v) -> {
                    if(updateUserParam != null){
                        User user = updateUserParam.toUser();
                        UserPreferences.set(user);
                        getViewState().onSuccessUpdateUser();
                    }
                })
                .doOnError((v) ->  getViewState().onFailedApiService(v.getMessage()));

       single.subscribe();
    }

    public void onLogOut(){
        UserPreferences.clear();
        getViewState().onLogOut();
    }

}
