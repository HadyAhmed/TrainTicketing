package com.hadi.trainticketing.passenger.model.pojo.ticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Trip {

    @SerializedName("finished")
    @Expose
    private Boolean finished;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("train")
    @Expose
    private String train;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Boolean getFinished() {
        return finished;
    }

    public String getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getTrain() {
        return train;
    }

    public Integer getV() {
        return v;
    }
}
