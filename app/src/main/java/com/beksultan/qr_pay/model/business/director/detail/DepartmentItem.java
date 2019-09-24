package com.beksultan.qr_pay.model.business.director.detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DepartmentItem {

    @SerializedName("CompanyId")
    @Expose
    public String companyid;

    @SerializedName("CompanyName")
    @Expose
    public String companyName;

    @SerializedName("DepartmentId")
    @Expose
    public String departmentId;

    @SerializedName("DepartmentName")
    @Expose
    public String departmentName;

    @SerializedName("DepartmentBalance")
    @Expose
    public String departmentBalance;

    @SerializedName("DepartmentHeadId")
    @Expose
    public String departmentHeadId;

    @SerializedName("DepartmentHeadName")
    @Expose
    public String departmentHeadName;

    @SerializedName("DepartmentHeadSurname")
    @Expose
    public String departmentHeadSurname;

    @SerializedName("DepartmentNumberOfStaff")
    @Expose
    public String departmentNumberOfStaff;

    @SerializedName("DepartmentStaff")
    @Expose
    public List<Staff> staffList;

}
