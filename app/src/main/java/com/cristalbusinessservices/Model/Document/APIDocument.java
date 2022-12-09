package com.cristalbusinessservices.Model.Document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIDocument {
    @SerializedName("result")
    @Expose
    private List<ResultDocument> result = null;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("notification")
    @Expose
    private Object notification;

    public List<ResultDocument> getResult() {
        return result;
    }

    public void setResult(List<ResultDocument> result) {
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
