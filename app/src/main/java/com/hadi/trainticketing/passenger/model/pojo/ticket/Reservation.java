package com.hadi.trainticketing.passenger.model.pojo.ticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hadi.trainticketing.passenger.model.pojo.signup.User;

import java.util.List;

public class Reservation {

    @SerializedName("arrivalTime")
    @Expose
    private String arrivalTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("justTrip")
    @Expose
    private List<JustTrip> justTrip = null;
    @SerializedName("valid")
    @Expose
    private Boolean valid;
    @SerializedName("ended")
    @Expose
    private Boolean ended;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("ticket")
    @Expose
    private Ticket ticket;
    @SerializedName("seat")
    @Expose
    private Seat seat;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public List<JustTrip> getJustTrip() {
        return justTrip;
    }

    public Boolean getValid() {
        return valid;
    }

    public Boolean getEnded() {
        return ended;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Seat getSeat() {
        return seat;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Integer getV() {
        return v;
    }
}
