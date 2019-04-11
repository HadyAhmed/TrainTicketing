package com.hadi.trainticketing.passenger.pojo.enquire;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticket {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("from")
    @Expose
    private From from;
    @SerializedName("to")
    @Expose
    private To to;
    @SerializedName("ticketType")
    @Expose
    private TicketType ticketType;
    @SerializedName("classType")
    @Expose
    private Integer classType;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public From getFrom() {
        return from;
    }

    public To getTo() {
        return to;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public Integer getClassType() {
        return classType;
    }

    public Integer getV() {
        return v;
    }
}

