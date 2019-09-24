package com.beksultan.qr_pay.ui.authorization;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.ui.MainActivity;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.model.user.param.AuthParam;
import com.beksultan.qr_pay.ui.registration.RegistrationActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import static com.beksultan.qr_pay.Constant.USER_IS_LOGGED;

public class AuthorizationActivity extends MvpAppCompatActivity implements AuthorizationView {

    @InjectPresenter
    AuthorizationPresenter mvpPresenter;

    @BindView(R.id.mainLayout)
    LinearLayout mainLayout;

    @BindView(R.id.edt_login)
    EditText edt_login;

    @BindView(R.id.edt_password)
    EditText edt_password;

    @BindView(R.id.txt_registration)
    TextView txt_registration;

    @BindView(R.id.txt_forgot_password)
    TextView txt_forgot_password;

    @BindView(R.id.btn_sign)
    Button btn_sign;

    public SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = App.getInstance().getPreferences();
        if (preferences.getBoolean(USER_IS_LOGGED, false)) {
            onSuccessSignIn();
        }
        setContentView(R.layout.activity_authorization);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.txt_registration)
    public void onRegistration() {
        new AlertDialog.Builder(AuthorizationActivity.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Хотите пройти регистрацию?")
                .setPositiveButton("Да", (dialog, which) -> startActivity(new Intent(AuthorizationActivity.this, RegistrationActivity.class)))
                .setNegativeButton("Нет", (dialog, which) -> {
                }).show();
    }

    @OnClick(R.id.txt_forgot_password)
    public void onForgotPassword() {
        new AlertDialog.Builder(AuthorizationActivity.this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Забыли пароль?")
                .setPositiveButton("Да", (dialog, which) -> {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:+77023461050"));
                    startActivity(callIntent);
                })
                .setNegativeButton("Нет", (dialog, which) -> {
                }).show();
    }

    @OnClick(R.id.btn_sign)
    public void onClickSignIn() {
        AuthParam authParam = new AuthParam(edt_login.getText().toString().trim(), edt_password.getText().toString().trim());

        if (authParam.isValidLogin() && authParam.isValidPassword()) {
            mvpPresenter.signIn(authParam);
        } else {
            if (!authParam.isValidLogin()) {
                edt_login.setError("Please, enter your name again!");
            }
            if (!authParam.isValidPassword()) {
                edt_password.setError("Please, enter your name again!");
            }
        }
    }

    @Override
    public void onSuccessSignIn() {
        startActivity(new Intent(AuthorizationActivity.this, MainActivity.class));
        finish();
    }

    //TODO It's should be in Basic Activity
    @Override
    public void onFailedApiService(String error) {
        Snackbar.make(mainLayout, error, Snackbar.LENGTH_LONG).show();
    }
}
