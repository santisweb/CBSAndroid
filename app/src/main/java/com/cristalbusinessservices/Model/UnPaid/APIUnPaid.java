package com.cristalbusinessservices.Model.UnPaid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIUnPaid {
    @SerializedName("result")
    @Expose
    private List<ResultUnPaid> result = null;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("notification")
    @Expose
    private Object notification;

    public List<ResultUnPaid> getResult() {
        return result;
    }

    public void setResult(List<ResultUnPaid> result) {
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
