package com.hadi.trainticketing.validator.login.pojo;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class EmployeeLoginResponse {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("emp")
    @Expose
    private Emp emp;

    public Boolean getSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public Emp getEmp() {
        return emp;
    }
}