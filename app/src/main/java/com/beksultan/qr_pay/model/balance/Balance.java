package com.beksultan.qr_pay.model.balance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Balance {

    @SerializedName("PersonalBalance")
    @Expose
    public String personalBalance;

    @SerializedName("CoperateBalance")
    @Expose
    public String coperateBalance;

    @SerializedName("Role")
    @Expose
    public String role;

    public String getPersonalBalance() {
        return personalBalance;
    }

    public String getCoperateBalance() {
        return coperateBalance;
    }

    public String getRole() {
        return role;
    }

    public void setPersonalBalance(String personalBalance) {
        this.personalBalance = personalBalance;
    }

    public void setCoperateBalance(String coperateBalance) {
        this.coperateBalance = coperateBalance;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
