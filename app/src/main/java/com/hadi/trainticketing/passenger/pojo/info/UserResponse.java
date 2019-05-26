package com.hadi.trainticketing.passenger.pojo.info;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class Result {

    @SerializedName("validation")
    @Expose
    private Boolean validation;
    @SerializedName("balance")
    @Expose
    private Integer balance;
    @SerializedName("userType")
    @Expose
    private String userType;
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
    @SerializedName("joinedOn")
    @Expose
    private String joinedOn;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public Boolean getValidation() {
        return validation;
    }

    public Integer getBalance() {
        return balance;
    }

    public String getUserType() {
        return userType;
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

    public String getJoinedOn() {
        return joinedOn;
    }

    public Integer getV() {
        return v;
    }
}

public class UserResponse {

    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public List<Result> getResult() {
        return result;
    }
}
