package com.beksultan.qr_pay.db;

import android.content.SharedPreferences;

import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.model.balance.Balance;

import static com.beksultan.qr_pay.Constant.COPERATE_BALANCE;
import static com.beksultan.qr_pay.Constant.PERSONAL_BALANCE;
import static com.beksultan.qr_pay.Constant.ROLE;
import static com.beksultan.qr_pay.utils.StringUtils.isOk;

public class BalancePreferences {

    public static void set(Balance balance) {
        SharedPreferences preferences = App.getInstance().getPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        if (isOk(balance.coperateBalance))
            editor.putString(COPERATE_BALANCE, balance.coperateBalance);
        if (isOk(balance.personalBalance))
            editor.putString(PERSONAL_BALANCE, balance.personalBalance);
        if (isOk(balance.role)) editor.putString(ROLE, balance.role);
        editor.apply();
    }

    public static Balance get() {
        SharedPreferences preferences = App.getInstance().getPreferences();
        Balance balance = new Balance();
        balance.coperateBalance = preferences.getString(COPERATE_BALANCE, "");
        balance.personalBalance = preferences.getString(PERSONAL_BALANCE, "");
        balance.role = preferences.getString(ROLE, "");

        return balance;
    }
}
