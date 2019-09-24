package com.beksultan.qr_pay.ui.profile;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.qr_pay.R;
import com.beksultan.qr_pay.db.UserPreferences;
import com.beksultan.qr_pay.model.user.User;
import com.beksultan.qr_pay.model.user.param.UpdateUserParam;
import com.beksultan.qr_pay.ui.authorization.AuthorizationActivity;
import com.beksultan.qr_pay.ui.base.BaseCategoryActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends BaseCategoryActivity implements ProfileView {

    @InjectPresenter
    ProfilePresenter mvpPresenter;

    private String user_id;

    private String user_password;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.img_user)
    ImageView img_user;

    @BindView(R.id.ll_edt_photo)
    LinearLayout ll_edt_photo;

    @BindView(R.id.edt_name)
    EditText edt_name;

    @BindView(R.id.ll_edt_name)
    LinearLayout ll_edt_name;

    @BindView(R.id.edt_surname)
    EditText edt_surname;

    @BindView(R.id.ll_edt_surname)
    LinearLayout ll_edt_surname;

    @BindView(R.id.edt_date_birth)
    EditText edt_date_birth;

    @BindView(R.id.edt_email)
    EditText edt_email;

    @BindView(R.id.ll_edt_email)
    LinearLayout ll_edt_email;

    @BindView(R.id.edt_login)
    EditText edt_login;

    @BindView(R.id.edt_password)
    EditText edt_password;

    @BindView(R.id.ll_edt_password)
    LinearLayout ll_edt_password;

    @BindView(R.id.edt_phone)
    EditText edt_phone;

    @BindView(R.id.ll_edt_phone)
    LinearLayout ll_edt_phone;

    @BindView(R.id.ll_about_company)
    LinearLayout ll_about_company;

    @BindView(R.id.img_log_out)
    ImageView img_log_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        onOpen();
    }

    @Override
    protected void setCurrentNavigationButton() {
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_home);
        MenuItem menuItem = btn_navigation.getMenu().findItem(R.id.nav_profile);
        menuItem.setIcon(drawable);
        menuItem.setEnabled(false);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_profile;
    }

    public void onOpen(){
        User user = UserPreferences.get();
        onInit(user);
    }

    @Override
    public void onInit(User user) {
        user_id = user.id;
        user_password = user.password;
        edt_name.setText(user.name);
        edt_surname.setText(user.surname);
        edt_date_birth.setText(user.birthdate);
        edt_email.setText(user.email);
        edt_login.setText(user.login);
        edt_password.setText(user.password);
        edt_phone.setText(user.phone);
    }

    @OnClick(R.id.ll_edt_password)
    public void onClickUpdatePassword() {
        View view = LayoutInflater.from(this).inflate(R.layout.alert_dialog, null);
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setCancelable(false);

        EditText edt_currentPassword = view.findViewById(R.id.edt_currentPassword);

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", ((dialog, which) -> {
            if (edt_currentPassword.getText().toString().equals(user_password)) {
                onUpdateUser();
            }
        }));
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", ((dialog, which) -> alertDialog.dismiss() ));
        alertDialog.setView(view);
        alertDialog.show();
    }

    @OnClick({R.id.ll_edt_name, R.id.ll_edt_surname, R.id.ll_edt_email, R.id.ll_edt_phone})
    public void onClickUpdateUser() {
        onUpdateUser();
    }

    public void onUpdateUser() {
        UpdateUserParam updateUserParam = new
                UpdateUserParam(user_id,
                edt_name.getText().toString().trim(),
                edt_surname.getText().toString().trim(),
                edt_password.getText().toString().trim(),
                edt_email.getText().toString().trim(),
                edt_phone.getText().toString().trim());

        if (updateUserParam.isValidName() &&
                updateUserParam.isValidSurname() &&
                updateUserParam.isValidEmail() &&
                updateUserParam.isValidPassword() &&
                updateUserParam.isValidPhone()) {
            mvpPresenter.onUpdateUser(updateUserParam);

        } else {
            if (!updateUserParam.isValidName()) {
                edt_name.setError("Please, enter your name again!");
            }
            if (!updateUserParam.isValidSurname()) {
                edt_surname.setError("Please, enter your name again!");
            }
            if (!updateUserParam.isValidEmail()) {
                edt_email.setError("Please, enter your name again!");
            }
            if (!updateUserParam.isValidPassword()) {
                edt_password.setError("Please, enter your name again!");
            }
            if (!updateUserParam.isValidPhone()) {
                edt_phone.setError("Please, enter your name again!");
            }
        }
    }

    @Override
    public void onSuccessUpdateUser() {
        mvpPresenter.onInit();
    }

    @OnClick(R.id.img_log_out)
    public void onClick() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Действительно хотите выйти из QR_pay?")
                .setPositiveButton("Да", ((dialog, which) -> {
                    onLogOut();
                    mvpPresenter.onLogOut();
                }))
                .setNegativeButton("Нет", ((dialog, which) -> {
                }))
                .show();
    }

    @Override
    public void onLogOut() {
        startActivity(new Intent(ProfileActivity.this, AuthorizationActivity.class));
        finish();
    }
}
