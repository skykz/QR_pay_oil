package com.beksultan.qr_pay.model.business.director.addDepartment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchHeadResponse {

    @SerializedName("FoundedPersons")
    @Expose
    public List<Head> headList;
}
