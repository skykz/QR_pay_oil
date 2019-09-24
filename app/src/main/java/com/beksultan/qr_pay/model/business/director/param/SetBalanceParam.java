package com.beksultan.qr_pay.model.business.director.param;

public class SetBalanceParam {

    public String headId;

    public String cooperatorId;

    public String price;

    public SetBalanceParam(String headId, String cooperatorId, String price) {
        this.headId = headId;
        this.cooperatorId = cooperatorId;
        this.price = price;
    }
}
