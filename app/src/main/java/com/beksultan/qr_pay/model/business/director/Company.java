package com.beksultan.qr_pay.model.business.director;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Company {

    @SerializedName("CompanyId")
    @Expose
    public String companyId;

    @SerializedName("CompanyName")
    @Expose
    public String companyName;

    @SerializedName("CompanyBalance")
    @Expose
    public String companyBalance;

    @SerializedName("Departments")
    @Expose
    public List<Department> departmentList;

}
