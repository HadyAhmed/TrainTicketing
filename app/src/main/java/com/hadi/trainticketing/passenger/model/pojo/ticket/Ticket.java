package com.hadi.trainticketing.passenger.model.pojo.ticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticket {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("classType")
    @Expose
    private Integer classType;
    @SerializedName("from")
    @Expose
    private From from;
    @SerializedName("to")
    @Expose
    private To to;
    @SerializedName("trian")
    @Expose
    private Trian trian;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getClassType() {
        return classType;
    }

    public From getFrom() {
        return from;
    }

    public To getTo() {
        return to;
    }

    public Trian getTrian() {
        return trian;
    }

    public Integer getV() {
        return v;
    }
}
