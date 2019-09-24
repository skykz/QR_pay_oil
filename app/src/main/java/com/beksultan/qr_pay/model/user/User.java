package com.beksultan.qr_pay.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("Id")
    @Expose
    public String id;

    @SerializedName("Login")
    @Expose
    public String login;

    @SerializedName("Password")
    @Expose
    public String password;

    @SerializedName("Name")
    @Expose
    public String name;

    @SerializedName("Surname")
    @Expose
    public String surname;

    @SerializedName("BirthDate")
    @Expose
    public String birthdate;

    @SerializedName("Email")
    @Expose
    public String email;

    @SerializedName("Phone")
    @Expose
    public String phone;
    
}
