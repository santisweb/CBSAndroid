package com.cristalbusinessservices.Model.Document_Type;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APITypeDocument {
    @SerializedName("result")
    @Expose
    private List<ResultTypeDocument> result = null;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("notification")
    @Expose
    private Object notification;

    public List<ResultTypeDocument> getResult() {
        return result;
    }

    public void setResult(List<ResultTypeDocument> result) {
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
