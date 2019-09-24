package com.beksultan.qr_pay.model.transaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transaction {

    @SerializedName("Id")
    @Expose
    public String id;

    @SerializedName("Date")
    @Expose
    public String date;

    @SerializedName("Price")
    @Expose
    public String price;

    @SerializedName("Balance")
    @Expose
    public String balance;

    @SerializedName("Gas")
    @Expose
    public String gas;

    @SerializedName("DepartmentName")
    @Expose
    public String departmentName;

}
