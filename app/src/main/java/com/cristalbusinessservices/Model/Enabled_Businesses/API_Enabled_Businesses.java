package com.cristalbusinessservices.Model.Enabled_Businesses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class API_Enabled_Businesses {
    @SerializedName("result")
    @Expose
    private List<ResultEnableBusinesses> result = null;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("notification")
    @Expose
    private Object notification;

    public List<ResultEnableBusinesses> getResult() {
        return result;
    }

    public void setResult(List<ResultEnableBusinesses> result) {
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
