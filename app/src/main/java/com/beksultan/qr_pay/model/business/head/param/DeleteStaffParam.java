package com.beksultan.qr_pay.model.business.head.param;

import com.arellomobile.mvp.MvpView;

public class DeleteStaffParam implements MvpView {

    public String personId;

    public String departmentId;

    public DeleteStaffParam(String personId, String departmentId) {
        this.personId = personId;
        this.departmentId = departmentId;
    }
}
