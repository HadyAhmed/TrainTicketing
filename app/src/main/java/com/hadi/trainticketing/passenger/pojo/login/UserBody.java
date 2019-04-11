package com.hadi.trainticketing.passenger.pojo.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserBody {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;

    // todo check for the sign up object changes
    public UserBody(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}

