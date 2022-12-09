package com.cristalbusinessservices.Model.Tax_Dropdowns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaxType {
    @SerializedName("display")
    @Expose
    private String display;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("isSelected")
    @Expose
    private Boolean isSelected;

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }
}
