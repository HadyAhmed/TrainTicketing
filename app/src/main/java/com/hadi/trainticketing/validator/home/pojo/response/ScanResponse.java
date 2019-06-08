package com.hadi.trainticketing.validator.home.pojo.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScanResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("request")
    @Expose
    private Request request;

    public String getMessage() {
        return message;
    }

    public Request getRequest() {
        return request;
    }
}

