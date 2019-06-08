package com.hadi.trainticketing.passenger.home.model.pojo.profile;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserResponse {

    @SerializedName("result")
    @Expose
    private Result result;

    public Result getResult() {
        return result;
    }
}