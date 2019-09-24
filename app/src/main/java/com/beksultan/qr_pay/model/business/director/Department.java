package com.beksultan.qr_pay.model.business.director;

import android.support.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Department implements Comparable{

    @SerializedName("Id")
    @Expose
    public String id;

    @SerializedName("Name")
    @Expose
    public String name;

    @SerializedName("HeadId")
    @Expose
    public String headId;

    @SerializedName("HeadName")
    @Expose
    public String headName;

    @SerializedName("HeadSurname")
    @Expose
    public String headSurname;

    @SerializedName("HeadLogin")
    @Expose
    public String headLogin;

    @SerializedName("DepartmentBalance")
    @Expose
    public String departmentBalance;

    public boolean isSelected;

    @Override
    public int compareTo(@NonNull Object o) {
        Department obj = (Department)o;

        return this.id.compareTo(obj.id);
    }
}
