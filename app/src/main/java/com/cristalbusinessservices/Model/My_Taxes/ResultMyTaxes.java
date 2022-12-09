package com.cristalbusinessservices.Model.My_Taxes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultMyTaxes {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("contactId")
    @Expose
    private Integer contactId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("taxType")
    @Expose
    private Integer taxType;
    @SerializedName("filingStatus")
    @Expose
    private Integer filingStatus;
    @SerializedName("refundAmount")
    @Expose
    private Integer refundAmount;
    @SerializedName("federalDueAmount")
    @Expose
    private Integer federalDueAmount;
    @SerializedName("stateTaxDueAmount")
    @Expose
    private Integer stateTaxDueAmount;
    @SerializedName("totalTaxPreparationFees")
    @Expose
    private Integer totalTaxPreparationFees;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("createdDate")
    @Expose
    private String createdDate;
    @SerializedName("completedDate")
    @Expose
    private Object completedDate;
    @SerializedName("taxTypeName")
    @Expose
    private String taxTypeName;
    @SerializedName("filingStatusName")
    @Expose
    private String filingStatusName;
    @SerializedName("completeFormLink")
    @Expose
    private String completeFormLink;

    public String getCompleteFormLink() {
        return completeFormLink;
    }

    public void setCompleteFormLink(String completeFormLink) {
        this.completeFormLink = completeFormLink;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTaxType() {
        return taxType;
    }

    public void setTaxType(Integer taxType) {
        this.taxType = taxType;
    }

    public Integer getFilingStatus() {
        return filingStatus;
    }

    public void setFilingStatus(Integer filingStatus) {
        this.filingStatus = filingStatus;
    }

    public Integer getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Integer refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Integer getFederalDueAmount() {
        return federalDueAmount;
    }

    public void setFederalDueAmount(Integer federalDueAmount) {
        this.federalDueAmount = federalDueAmount;
    }

    public Integer getStateTaxDueAmount() {
        return stateTaxDueAmount;
    }

    public void setStateTaxDueAmount(Integer stateTaxDueAmount) {
        this.stateTaxDueAmount = stateTaxDueAmount;
    }

    public Integer getTotalTaxPreparationFees() {
        return totalTaxPreparationFees;
    }

    public void setTotalTaxPreparationFees(Integer totalTaxPreparationFees) {
        this.totalTaxPreparationFees = totalTaxPreparationFees;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Object getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Object completedDate) {
        this.completedDate = completedDate;
    }

    public String getTaxTypeName() {
        return taxTypeName;
    }

    public void setTaxTypeName(String taxTypeName) {
        this.taxTypeName = taxTypeName;
    }

    public String getFilingStatusName() {
        return filingStatusName;
    }

    public void setFilingStatusName(String filingStatusName) {
        this.filingStatusName = filingStatusName;
    }
}
