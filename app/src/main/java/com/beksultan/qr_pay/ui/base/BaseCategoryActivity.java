package com.beksultan.qr_pay.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.db.BalancePreferences;
import com.beksultan.qr_pay.ui.MainActivity;
import com.beksultan.qr_pay.ui.business.director.DirectorActivity;
import com.beksultan.qr_pay.ui.business.head.HeadActivity;
import com.beksultan.qr_pay.ui.transaction.TransactionActivity;
import com.beksultan.qr_pay.ui.payment.PaymentActivity;
import com.beksultan.qr_pay.ui.profile.ProfileActivity;
import com.beksultan.qr_pay.utils.NavButtonUtils;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseCategoryActivity extends MvpAppCompatActivity implements BaseCategoryView {

    String role;

    @BindView(R.id.btn_navigation)
    protected BottomNavigationView btn_navigation;

    @BindView(R.id.container)
    protected RelativeLayout container;

    @BindView(R.id.progressBar)
    @Nullable
    protected ProgressBar progressBar;

    protected abstract int getLayoutResourceId();

    protected abstract void setCurrentNavigationButton();

    @Override
    public void onLoadingIndicator(boolean isShow) {
        if (isShow) {
            container.setVisibility(View.GONE);
            if (progressBar != null) progressBar.setVisibility(View.VISIBLE);
        } else {
            container.setVisibility(View.VISIBLE);
            if (progressBar != null) progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailedApiService(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);

        setCurrentNavigationButton();
        NavButtonUtils.disableShiftMode(btn_navigation);

        btn_navigation.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.nav_profile:
                    startActivity(new Intent(BaseCategoryActivity.this, ProfileActivity.class));
                    finish();
                    break;
                case R.id.nav_history:
                    startActivity(new Intent(BaseCategoryActivity.this, TransactionActivity.class));
                    finish();
                    break;
                case R.id.nav_business:

                    role = BalancePreferences.get().role.trim();
                    ArrayList<String> list = new ArrayList<>(Arrays.asList(role.split(",")));

                    for (int i = 0; i < list.size(); i++) {
                        list.set(i, list.get(i).replaceAll(" ", ""));
                    }

                    if (list.contains("ROLE_DIRECTOR")) {
                        startActivity(new Intent(BaseCategoryActivity.this, DirectorActivity.class));
                        finish();

                    } else if (list.contains("ROLE_HEAD")) {
                        startActivity(new Intent(BaseCategoryActivity.this, HeadActivity.class));
                        finish();
                    }

                    break;
                case R.id.nav_payment:
                    startActivity(new Intent(BaseCategoryActivity.this, PaymentActivity.class));
                    finish();
                    break;

                default:
                    break;
            }
            return false;
        });
    }

}
