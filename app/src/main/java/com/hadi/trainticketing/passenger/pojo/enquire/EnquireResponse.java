package com.hadi.trainticketing.passenger.pojo.enquire;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EnquireResponse {

    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public List<Result> getResult() {
        return result;
    }
}
