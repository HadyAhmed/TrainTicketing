package com.hadi.trainticketing.passenger.model.pojo.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PassengerLoginResponse {

    @SerializedName("success")
    @Expose
    private boolean success;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user")
    @Expose
    private UserBody user;

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public UserBody getUser() {
        return user;
    }

}

