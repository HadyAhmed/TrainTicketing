package com.hadi.trainticketing.validator.home.pojo.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Signature {

    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("keyId")
    @Expose
    private String keyId;

    public String getHash() {
        return hash;
    }

    public String getKeyId() {
        return keyId;
    }
}