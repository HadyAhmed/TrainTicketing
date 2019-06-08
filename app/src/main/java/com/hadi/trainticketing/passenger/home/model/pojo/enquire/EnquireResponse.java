package com.hadi.trainticketing.passenger.home.model.pojo.enquire;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class EnquireResponse {

    @SerializedName("resultArray")
    @Expose
    private List<ResultArray> resultArray = null;

    public List<ResultArray> getResultArray() {
        return resultArray;
    }
}



