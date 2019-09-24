package com.beksultan.qr_pay.model.transaction.param;

public class UserAndCompanyParam {

    public String personId;
    public String startDate;
    public String endDate;

    public UserAndCompanyParam(String personId, String startDate, String endDate) {
        this.personId = personId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
