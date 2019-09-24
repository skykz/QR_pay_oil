package com.beksultan.qr_pay.db;

import android.content.SharedPreferences;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.model.balance.Balance;
import com.beksultan.qr_pay.model.user.User;
import static com.beksultan.qr_pay.Constant.BIRTH_DATE;
import static com.beksultan.qr_pay.Constant.COPERATE_BALANCE;
import static com.beksultan.qr_pay.Constant.EMAIL;
import static com.beksultan.qr_pay.Constant.ID;
import static com.beksultan.qr_pay.Constant.LOGIN;
import static com.beksultan.qr_pay.Constant.NAME;
import static com.beksultan.qr_pay.Constant.PASSWORD;
import static com.beksultan.qr_pay.Constant.PERSONAL_BALANCE;
import static com.beksultan.qr_pay.Constant.PHONE;
import static com.beksultan.qr_pay.Constant.ROLE;
import static com.beksultan.qr_pay.Constant.SURNAME;
import static com.beksultan.qr_pay.Constant.USER_IS_LOGGED;
import static com.beksultan.qr_pay.utils.StringUtils.isOk;

public class UserPreferences {

    public static void set(User user) {
        SharedPreferences preferences = App.getInstance().getPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        if (isOk(user.id)) editor.putString(ID, user.id);
        if (isOk(user.name)) editor.putString(NAME, user.name);
        if (isOk(user.surname)) editor.putString(SURNAME, user.surname);
        if (isOk(user.birthdate)) editor.putString(BIRTH_DATE, user.birthdate);
        if (isOk(user.email)) editor.putString(EMAIL, user.email);
        if (isOk(user.login)) editor.putString(LOGIN, user.login);
        if (isOk(user.password)) editor.putString(PASSWORD, user.password);
        if (isOk(user.phone)) editor.putString(PHONE, user.phone);
        editor.putBoolean(USER_IS_LOGGED, true);
        editor.apply();
    }

    public static User get() {
        SharedPreferences preferences = App.getInstance().getPreferences();
        User user = new User();
        user.id = preferences.getString(ID, "");
        user.name = preferences.getString(NAME, "");
        user.surname = preferences.getString(SURNAME, "");
        user.birthdate = preferences.getString(BIRTH_DATE, "");
        user.email = preferences.getString(EMAIL, "");
        user.login = preferences.getString(LOGIN, "");
        user.password = preferences.getString(PASSWORD, "");
        user.phone = preferences.getString(PHONE, "");
        return user;
    }

    public static void clear() {
        SharedPreferences preferences = App.getInstance().getPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(ID, "");
        editor.putString(NAME, "");
        editor.putString(SURNAME, "");
        editor.putString(BIRTH_DATE, "");
        editor.putString(EMAIL, "");
        editor.putString(LOGIN, "");
        editor.putString(PASSWORD, "");
        editor.putString(PHONE, "");
        editor.putBoolean(USER_IS_LOGGED, false);
        editor.apply();
    }
}
