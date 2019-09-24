package com.beksultan.qr_pay.model.business.director.param;

public class UpdateParam {

    public String headId;
    public String departmentId;
    public String departmentName;

    public UpdateParam(String headId, String departmentId, String departmentName) {
        this.headId = headId;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }
}
