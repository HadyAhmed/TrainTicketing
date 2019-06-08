package com.hadi.trainticketing.passenger.home.model.pojo.reservation.response.reservation;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReservationResult {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("reservation")
    @Expose
    private Reservation reservation;

    public String getMessage() {
        return message;
    }

    public Reservation getReservation() {
        return reservation;
    }
}

