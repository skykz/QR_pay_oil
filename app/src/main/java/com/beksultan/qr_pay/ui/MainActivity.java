package com.beksultan.qr_pay.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.db.BalancePreferences;
import com.beksultan.qr_pay.db.UserPreferences;
import com.beksultan.qr_pay.model.balance.Balance;
import com.beksultan.qr_pay.model.balance.BalanceParam;
import com.beksultan.qr_pay.ui.base.BaseCategoryActivity;
import com.beksultan.qr_pay.ui.business.director.DirectorActivity;
import com.beksultan.qr_pay.ui.business.head.HeadActivity;
import com.beksultan.qr_pay.ui.transaction.TransactionActivity;
import com.beksultan.qr_pay.ui.payment.PaymentActivity;
import com.beksultan.qr_pay.ui.profile.ProfileActivity;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.beksultan.qr_pay.Constant.NAME;
import static com.beksultan.qr_pay.Constant.SURNAME;

public class MainActivity extends BaseCategoryActivity implements MainView {

    @InjectPresenter
    MainPresenter mvpPresenter;

    String name;

    String surname;

    String role;

    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.tv_price)
    TextView tv_price;

    @BindView(R.id.cardView_history)
    CardView cardView_history;

    @BindView(R.id.cardView_profile)
    CardView cardView_profile;

    @BindView(R.id.cardView_payment)
    CardView cardView_payment;

    @BindView(R.id.cardView_business)
    CardView cardView_business;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setCurrentNavigationButton() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        onSetParam();
        onSetName();
    }

    private void onSetParam() {
        BalanceParam param = new BalanceParam(UserPreferences.get().id);
        mvpPresenter.getBalance(param);
    }

    private void onSetName() {
        name = App.getInstance().getPreferences().getString(NAME, "");
        surname = App.getInstance().getPreferences().getString(SURNAME, "");
        tv_name.setText(name + " " + surname);
    }

    @Override
    public void onShowBalanceAndRole(Balance balance) {
        tv_price.setText(balance.personalBalance + " KZT");
        BalancePreferences.set(balance);
    }

    @OnClick(R.id.cardView_history)
    public void openHistory() {
        startActivity(new Intent(MainActivity.this, TransactionActivity.class));
        finish();
    }

    @OnClick(R.id.cardView_profile)
    public void openProfile() {
        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
        finish();
    }

    @OnClick(R.id.cardView_payment)
    public void openPayment() {
        startActivity(new Intent(MainActivity.this, PaymentActivity.class));
        finish();
    }

    @OnClick(R.id.cardView_business)
    public void openBusiness() {

        role = BalancePreferences.get().role.trim();
        String[] array = role.split(",");
        ArrayList<String> list = new ArrayList<>(Arrays.asList(array));

        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i).replaceAll(" ", ""));
        }

        if (list.contains("ROLE_DIRECTOR")) {
            startActivity(new Intent(MainActivity.this, DirectorActivity.class));
            finish();

        } else if (list.contains("ROLE_HEAD")) {
            startActivity(new Intent(MainActivity.this, HeadActivity.class));
            finish();

        }
    }


}
