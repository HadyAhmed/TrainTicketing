package com.hadi.trainticketing.passenger.pojo.signup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("validation")
    @Expose
    private boolean validation;
    @SerializedName("balance")
    @Expose
    private int balance;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("nationalID")
    @Expose
    private String nationalID;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("dateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("userType")
    @Expose
    private String userType;
    @SerializedName("joinedOn")
    @Expose
    private String joinedOn;
    @SerializedName("__v")
    @Expose
    private int v;

    public boolean isValidation() {
        return validation;
    }

    public int getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    public String getNationalID() {
        return nationalID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserType() {
        return userType;
    }

    public String getJoinedOn() {
        return joinedOn;
    }

    public int getV() {
        return v;
    }
}

