package com.hadi.trainticketing.passenger.model.pojo.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("iat")
    @Expose
    private Integer iat;
    @SerializedName("exp")
    @Expose
    private Integer exp;

    public Data getData() {
        return data;
    }

    public Integer getIat() {
        return iat;
    }

    public Integer getExp() {
        return exp;
    }
}
