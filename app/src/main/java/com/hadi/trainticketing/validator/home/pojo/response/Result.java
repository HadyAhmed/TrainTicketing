package com.hadi.trainticketing.validator.home.pojo.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("n")
    @Expose
    private Integer n;
    @SerializedName("nModified")
    @Expose
    private Integer nModified;
    @SerializedName("opTime")
    @Expose
    private OpTime opTime;
    @SerializedName("electionId")
    @Expose
    private String electionId;
    @SerializedName("ok")
    @Expose
    private Integer ok;
    @SerializedName("operationTime")
    @Expose
    private String operationTime;
    @SerializedName("$clusterTime")
    @Expose
    private ClusterTime $clusterTime;

    public Integer getN() {
        return n;
    }

    public Integer getnModified() {
        return nModified;
    }

    public OpTime getOpTime() {
        return opTime;
    }

    public String getElectionId() {
        return electionId;
    }

    public Integer getOk() {
        return ok;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public ClusterTime getClusterTime() {
        return $clusterTime;
    }
}
