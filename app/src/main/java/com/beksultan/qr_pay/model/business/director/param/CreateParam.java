package com.beksultan.qr_pay.model.business.director.param;

public class CreateParam {

    public String headId;
    public String companyId;
    public String departmentName;

    public CreateParam(String headId, String companyId, String departmentName) {
        this.headId = headId;
        this.companyId = companyId;
        this.departmentName = departmentName;
    }
}
