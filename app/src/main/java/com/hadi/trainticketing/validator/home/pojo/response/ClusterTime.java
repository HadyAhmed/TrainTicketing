package com.hadi.trainticketing.validator.home.pojo.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClusterTime {

    @SerializedName("clusterTime")
    @Expose
    private String clusterTime;
    @SerializedName("signature")
    @Expose
    private Signature signature;

    public String getClusterTime() {
        return clusterTime;
    }

    public Signature getSignature() {
        return signature;
    }
}