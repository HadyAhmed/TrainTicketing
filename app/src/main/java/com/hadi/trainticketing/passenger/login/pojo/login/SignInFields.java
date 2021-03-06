package com.hadi.trainticketing.passenger.login.pojo.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignInFields {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;

    public SignInFields() {
    }

    public SignInFields(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
