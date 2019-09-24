package com.beksultan.qr_pay.model.business.head;

import android.support.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DepartmentStaff implements Comparable{

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

    public boolean isSelected;

    @Override
    public int compareTo(@NonNull Object o) {
        DepartmentStaff obj = (DepartmentStaff)o;
        return this.id.compareTo(obj.id);
    }
}
