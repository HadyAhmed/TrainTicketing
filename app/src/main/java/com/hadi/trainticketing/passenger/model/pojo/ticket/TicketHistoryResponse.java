package com.hadi.trainticketing.passenger.model.pojo.ticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TicketHistoryResponse {

    @SerializedName("reservation")
    @Expose
    private List<Reservation> reservation = null;

    public List<Reservation> getReservation() {
        return reservation;
    }
}


