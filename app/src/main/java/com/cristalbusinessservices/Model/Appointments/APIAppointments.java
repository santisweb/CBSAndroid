package com.cristalbusinessservices.Model.Appointments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIAppointments {
    @SerializedName("result")
    @Expose
    private List<ResultAppointments> result = null;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("notification")
    @Expose
    private Object notification;

    public List<ResultAppointments> getResult() {
        return result;
    }

    public void setResult(List<ResultAppointments> result) {
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
