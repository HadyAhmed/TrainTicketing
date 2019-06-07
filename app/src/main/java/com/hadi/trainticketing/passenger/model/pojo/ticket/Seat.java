package com.hadi.trainticketing.passenger.model.pojo.ticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Seat {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("classType")
    @Expose
    private Integer classType;
    @SerializedName("train")
    @Expose
    private Trian train;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Boolean getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getClassType() {
        return classType;
    }

    public Trian getTrain() {
        return train;
    }

    public Integer getV() {
        return v;
    }
}