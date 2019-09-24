package com.beksultan.qr_pay.model.business.director.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Staff {

    @SerializedName("Id")
    @Expose
    public String id;

    @SerializedName("Name")
    @Expose
    public String name;

    @SerializedName("Surname")
    @Expose
    public String surname;

    @SerializedName("Balance")
    @Expose
    public String balance;

    @SerializedName("Phone")
    @Expose
    public String phone;
}
