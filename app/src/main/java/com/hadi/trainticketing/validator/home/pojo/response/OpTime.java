package com.hadi.trainticketing.validator.home.pojo.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpTime {

    @SerializedName("ts")
    @Expose
    private String ts;
    @SerializedName("t")
    @Expose
    private Integer t;

    public String getTs() {
        return ts;
    }

    public Integer getT() {
        return t;
    }
}