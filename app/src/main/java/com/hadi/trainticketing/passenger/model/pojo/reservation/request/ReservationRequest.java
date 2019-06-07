package com.hadi.trainticketing.passenger.model.pojo.reservation.request;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservationRequest {

    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("ticket")
    @Expose
    private String ticket;
    @SerializedName("justTrip")
    @Expose
    private String[] justTrip = null;
    @SerializedName("seat")
    @Expose
    private String seat;

    public ReservationRequest(String user, String ticket, String[] justTrip, String seat) {
        this.user = user;
        this.ticket = ticket;
        this.justTrip = justTrip;
        this.seat = seat;
    }

    public String getUser() {
        return user;
    }

    public String getTicket() {
        return ticket;
    }

    public String[] getJustTrip() {
        return justTrip;
    }

    public String getSeat() {
        return seat;
    }
}
