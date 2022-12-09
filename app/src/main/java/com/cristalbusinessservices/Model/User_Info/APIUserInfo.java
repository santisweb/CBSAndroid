package com.cristalbusinessservices.Model.User_Info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class APIUserInfo implements Serializable {
    @SerializedName("result")
    @Expose
    private ResultUserInfo result;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("notification")
    @Expose
    private Object notification;

    public ResultUserInfo getResult() {
        return result;
    }

    public void setResult(ResultUserInfo result) {
        this.result = result;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public Object getNotification() {
        return notification;
    }

    public void setNotification(Object notification) {
        this.notification = notification;
    }
}
