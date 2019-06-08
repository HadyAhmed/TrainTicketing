package com.hadi.trainticketing.validator.home.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScanBody {
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("value")
    @Expose
    private String value;

    public ScanBody(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "ScanBody{" +
                "key='" + key + '\'' + "value='" + value + '\'' +
                '}';
    }

}
