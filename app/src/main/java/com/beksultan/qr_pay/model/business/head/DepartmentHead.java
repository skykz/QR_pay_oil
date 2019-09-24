package com.beksultan.qr_pay.model.business.head;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DepartmentHead {

    @SerializedName("CompanyId")
    @Expose
    public String companyId;

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
    public String departmentHeadid;

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
    public List<DepartmentStaff> departmentStaffList;
}
