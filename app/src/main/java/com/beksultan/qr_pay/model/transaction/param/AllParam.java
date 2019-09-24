package com.beksultan.qr_pay.model.transaction.param;

public class AllParam {

    public String personId;
    public String page;
    public String startDate;
    public String endDate;

    public AllParam(String personId, String startDate, String endDate) {
        this.personId = personId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
