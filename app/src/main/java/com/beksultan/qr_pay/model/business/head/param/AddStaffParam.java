package com.beksultan.qr_pay.model.business.head.param;

public class AddStaffParam {

    public String personId;
    public String departmentId;

    public AddStaffParam(String personId, String departmentId) {
        this.personId = personId;
        this.departmentId = departmentId;
    }
}
