package com.beksultan.qr_pay.model.transaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class TransactionResponse {

    @SerializedName("NumberOfTransactions")
    @Expose
    public String count;

    @SerializedName("Transactions")
    @Expose
    public ArrayList<Transaction> transactionList;

}
