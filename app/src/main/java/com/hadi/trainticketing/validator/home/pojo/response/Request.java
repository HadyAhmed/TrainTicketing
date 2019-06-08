package com.hadi.trainticketing.validator.home.pojo.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

    @SerializedName("result")
    @Expose
    private Result result;

    public Result getResult() {
        return result;
    }
}
