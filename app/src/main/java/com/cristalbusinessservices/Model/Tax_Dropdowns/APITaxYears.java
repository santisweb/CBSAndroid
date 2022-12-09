package com.cristalbusinessservices.Model.Tax_Dropdowns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APITaxYears {
    @SerializedName("yearsDropdown")
    @Expose
    private List<YearsDropdown> yearsDropdown = null;
    @SerializedName("taxTypes")
    @Expose
    private List<TaxType> taxTypes = null;

    public List<YearsDropdown> getYearsDropdown() {
        return yearsDropdown;
    }

    public void setYearsDropdown(List<YearsDropdown> yearsDropdown) {
        this.yearsDropdown = yearsDropdown;
    }

    public List<TaxType> getTaxTypes() {
        return taxTypes;
    }

    public void setTaxTypes(List<TaxType> taxTypes) {
        this.taxTypes = taxTypes;
    }
}
