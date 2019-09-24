package com.beksultan.qr_pay.db;

import android.content.SharedPreferences;
import com.beksultan.qr_pay.App;
import com.beksultan.qr_pay.model.business.director.Company;
import static com.beksultan.qr_pay.Constant.COMPANY_BALANCE;
import static com.beksultan.qr_pay.Constant.COMPANY_ID;
import static com.beksultan.qr_pay.Constant.COMPANY_NAME;
import static com.beksultan.qr_pay.utils.StringUtils.isOk;

public class CompanyPreferences {

    public static void set(Company company) {
        SharedPreferences preferences = App.getInstance().getPreferences();
        SharedPreferences.Editor editor = preferences.edit();
        if (isOk(company.companyId)) editor.putString(COMPANY_ID, company.companyId);
        if (isOk(company.companyName)) editor.putString(COMPANY_NAME, company.companyName);
        if (isOk(company.companyBalance)) editor.putString(COMPANY_BALANCE, company.companyBalance);
        editor.apply();
    }

    public static Company get(){
        SharedPreferences preferences = App.getInstance().getPreferences();
        Company company = new Company();
        company.companyId = preferences.getString(COMPANY_ID,"");
        company.companyName = preferences.getString(COMPANY_NAME,"");
        company.companyBalance = preferences.getString(COMPANY_BALANCE,"");
        return company;
    }
}
