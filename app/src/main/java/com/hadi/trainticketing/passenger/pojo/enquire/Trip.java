package com.hadi.trainticketing.passenger.pojo.enquire;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trip {

    @SerializedName("arrived")
    @Expose
    private Boolean arrived;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("startTime")
    @Expose
    private String startTime;
    @SerializedName("arrivelTime")
    @Expose
    private String arrivelTime;
    @SerializedName("train")
    @Expose
    private Train train;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Boolean getArrived() {
        return arrived;
    }

    public String getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getArrivelTime() {
        return arrivelTime;
    }

    public Train getTrain() {
        return train;
    }

    public Integer getV() {
        return v;
    }
}

