package com.beksultan.qr_pay.ui.authorization;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.db.UserPreferences;
import com.beksultan.qr_pay.model.user.User;
import com.beksultan.qr_pay.model.user.param.AuthParam;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class AuthorizationPresenter extends MvpPresenter<AuthorizationView> {

    public void signIn(final AuthParam param) {
        App.getInstance().getApiService().authorization(param)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User user = response.body();
                        if (user != null) {
                            UserPreferences.set(user);
                            getViewState().onSuccessSignIn();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        getViewState().onFailedApiService(t.getMessage());
                    }
                });
    }


}
