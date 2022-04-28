package com.vasana.smartattendance.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateUserDetails implements Serializable {
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("status")
    private String status;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UpdateUserDetails(String mobile, String status) {
        this.mobile = mobile;
        this.status = status;
    }
}
