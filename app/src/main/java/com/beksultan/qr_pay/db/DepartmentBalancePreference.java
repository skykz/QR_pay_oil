package com.beksultan.qr_pay.db;

import android.content.SharedPreferences;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.model.business.head.DepartmentHead;
import static com.beksultan.qr_pay.Constant.DEPARTMENT_BALANCE;

public class DepartmentBalancePreference {

    public static void set(DepartmentHead balance) {
        SharedPreferences preferences = App.getInstance().getPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(DEPARTMENT_BALANCE, balance.departmentBalance);
        editor.apply();
    }

    public static DepartmentHead get() {
        SharedPreferences preferences = App.getInstance().getPreferences();
        DepartmentHead balance = new DepartmentHead();
        balance.departmentBalance = preferences.getString(DEPARTMENT_BALANCE,"");
        return balance;
    }
}
