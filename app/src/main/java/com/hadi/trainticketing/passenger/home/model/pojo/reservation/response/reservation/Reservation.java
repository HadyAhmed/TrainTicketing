package com.hadi.trainticketing.passenger.home.model.pojo.reservation.response.reservation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Reservation {

    @SerializedName("justTrip")
    @Expose
    private List<String> justTrip = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("ticket")
    @Expose
    private String ticket;
    @SerializedName("seat")
    @Expose
    private String seat;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public List<String> getJustTrip() {
        return justTrip;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getTicket() {
        return ticket;
    }

    public String getSeat() {
        return seat;
    }

    public Integer getV() {
        return v;
    }
}

