package com.hadi.trainticketing.passenger.home.model.pojo.ticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class To {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getV() {
        return v;
    }
}
