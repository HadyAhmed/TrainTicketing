package com.hadi.trainticketing.passenger.home.model.pojo.enquire;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultArray {

    @SerializedName("tickets")
    @Expose
    private List<Ticket> tickets = null;
    @SerializedName("tripNumber")
    @Expose
    private Integer tripNumber;
    @SerializedName("arrivalTime")
    @Expose
    private String arrivalTime;
    @SerializedName("endTime")
    @Expose
    private String endTime;
    @SerializedName("arrayResult")
    @Expose
    private List<ArrayResult> arrayResult = null;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Integer getTripNumber() {
        return tripNumber;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public List<ArrayResult> getArrayResult() {
        return arrayResult;
    }
}

