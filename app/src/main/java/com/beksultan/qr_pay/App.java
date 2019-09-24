package com.beksultan.qr_pay;

import android.app.Application;
import android.content.SharedPreferences;
import com.beksultan.qr_pay.network.ApiService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import static com.beksultan.qr_pay.Constant.BASE_URL;

public class App extends Application {

    public static App instance;

    private SharedPreferences preferences;

    public static Retrofit retrofit;

    public static ApiService apiService;

    @Override
    public void onCreate() {

        super.onCreate();
        instance = this;
        preferences = getSharedPreferences("app", MODE_PRIVATE);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static App getInstance() {
        return instance;
    }

    public SharedPreferences getPreferences() {
    return preferences;
    }

    public ApiService getApiService(){ return apiService; }
}