package com.hadi.trainticketing.passenger.model.pojo.ticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hadi.trainticketing.passenger.model.pojo.enquire.From;
import com.hadi.trainticketing.passenger.model.pojo.enquire.To;

public class JustTrip {

    @SerializedName("arrived")
    @Expose
    private Boolean arrived;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("trip")
    @Expose
    private Trip trip;
    @SerializedName("from")
    @Expose
    private From from;
    @SerializedName("to")
    @Expose
    private To to;
    @SerializedName("arrivalTime")
    @Expose
    private String arrivalTime;
    @SerializedName("sort")
    @Expose
    private Integer sort;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Boolean getArrived() {
        return arrived;
    }

    public String getId() {
        return id;
    }

    public Trip getTrip() {
        return trip;
    }

    public From getFrom() {
        return from;
    }

    public To getTo() {
        return to;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public Integer getSort() {
        return sort;
    }

    public Integer getV() {
        return v;
    }
}
