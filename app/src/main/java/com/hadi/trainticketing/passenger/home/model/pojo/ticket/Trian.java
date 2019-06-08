package com.hadi.trainticketing.passenger.home.model.pojo.ticket;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trian {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("days")
    @Expose
    private List<String> days = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("number")
    @Expose
    private Integer number;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Boolean getStatus() {
        return status;
    }

    public List<String> getDays() {
        return days;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getV() {
        return v;
    }
}
