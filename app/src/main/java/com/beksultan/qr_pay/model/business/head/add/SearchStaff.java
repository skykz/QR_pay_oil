package com.beksultan.qr_pay.model.business.head.add;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SearchStaff {

    @SerializedName("FoundedPersons")
    @Expose
    public List<HeadStaff> headStaffList;
}
