package com.cristalbusinessservices.Model.Document_Type;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultTypeDocument {
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("selected")
    @Expose
    private Boolean selected;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }
}
