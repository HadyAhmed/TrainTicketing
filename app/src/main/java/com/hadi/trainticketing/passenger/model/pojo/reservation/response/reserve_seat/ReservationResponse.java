package com.hadi.trainticketing.passenger.model.pojo.reservation.response.reserve_seat;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hadi.trainticketing.passenger.model.pojo.reservation.response.seats.AvailableSeat;
import com.hadi.trainticketing.passenger.model.pojo.reservation.response.seats.Seat;

import java.util.List;

public class ReservationResponse {

    @SerializedName("seats")
    @Expose
    private List<Seat> seats = null;
    @SerializedName("reservedSeats")
    @Expose
    private List<Object> reservedSeats = null;
    @SerializedName("availableSeats")
    @Expose
    private List<AvailableSeat> availableSeats = null;

    public List<AvailableSeat> getAvailableSeats() {
        return availableSeats;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public List<Object> getReservedSeats() {
        return reservedSeats;
    }
}


