package com.hadi.trainticketing.passenger.pojo.enquire;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("trip")
    @Expose
    private Trip trip;
    @SerializedName("ticket")
    @Expose
    private Ticket ticket;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public Trip getTrip() {
        return trip;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Integer getV() {
        return v;
    }
}
