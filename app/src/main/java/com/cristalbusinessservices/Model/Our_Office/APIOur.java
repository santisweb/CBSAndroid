package com.cristalbusinessservices.Model.Our_Office;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIOur {
    @SerializedName("result")
    @Expose
    private ResultOur result;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("notification")
    @Expose
    private Object notification;

    public ResultOur getResult() {
        return result;
    }

    public void setResult(ResultOur result) {
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
