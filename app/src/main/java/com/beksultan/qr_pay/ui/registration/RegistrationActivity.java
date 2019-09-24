package com.beksultan.qr_pay.ui.registration;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.model.user.param.RegistrationParam;
import com.beksultan.qr_pay.ui.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends MvpAppCompatActivity implements RegistrationView {

    @InjectPresenter
    RegistrationPresenter mvpPresenter;

    @BindView(R.id.mainLayout)
    LinearLayout mainLayout;

    @BindView(R.id.tv_back)
    TextView tv_back;

    @BindView(R.id.edt_name)
    EditText edt_name;

    @BindView(R.id.edt_surname)
    EditText edt_surname;

    @BindView(R.id.edt_day)
    EditText edt_day;

    @BindView(R.id.edt_month)
    EditText edt_month;

    @BindView(R.id.edt_year)
    EditText edt_year;

    @BindView(R.id.edt_email)
    EditText edt_email;

    @BindView(R.id.edt_phone)
    EditText edt_phone;

    @BindView(R.id.edt_login)
    EditText edt_login;

    @BindView(R.id.edt_password)
    EditText edt_password;

    @BindView(R.id.btn_register)
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_back)
    public void onBack() {
        finish();
    }

    @OnClick(R.id.btn_register)
    public void onClickRegistration() {
        String birthDate = edt_year.getText().toString().trim() + "-" + edt_day.getText().toString().trim() + "-" + edt_month.getText().toString().trim();
        RegistrationParam param = new RegistrationParam(
                edt_login.getText().toString().trim(),
                edt_password.getText().toString().trim(),
                edt_name.getText().toString().trim(),
                edt_surname.getText().toString().trim(),
                edt_phone.getText().toString().trim(),
                edt_email.getText().toString().trim(),
                birthDate.trim());

        if (param.isValidLogin()
                && param.isValidPassword()
                && param.isValidName()
                && param.isValidSurname()
                && param.isValidPhone()
                && param.isValidEmail()
                && param.isValidBirthDate()) {
            mvpPresenter.registration(param);
        } else {

            if (!param.isValidLogin()) {
                edt_login.setError("Please, enter your name again!");
            }
            if (!param.isValidPassword()) {
                edt_password.setError("Please, enter your name again!");
            }
            if (!param.isValidName()) {
                edt_name.setError("Please, enter your name again!");
            }
            if (!param.isValidSurname()) {
                edt_surname.setError("Please, enter your name again!");
            }
            if (!param.isValidPhone()) {
                edt_phone.setError("Please, enter your name again!");
            }
            if (!param.isValidEmail()) {
                edt_email.setError("Please, enter your name again!");
            }
            if (!param.isValidBirthDate()) {
                edt_day.setError("Please, enter your name again!");
                edt_month.setError("Please, enter your name again!");
                edt_year.setError("Please, enter your name again!");
            }
        }
    }

    @Override
    public void onSuccessRegistered() {
        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFailedApiService(String error) {
        Snackbar.make(mainLayout, error, Snackbar.LENGTH_LONG).show();
    }
}
