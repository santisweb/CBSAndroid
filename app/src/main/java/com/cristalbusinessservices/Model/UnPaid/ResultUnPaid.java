package com.cristalbusinessservices.Model.UnPaid;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultUnPaid {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("contactId")
    @Expose
    private Integer contactId;
    @SerializedName("invoiceNumber")
    @Expose
    private String invoiceNumber;
    @SerializedName("invoiceIdentity")
    @Expose
    private String invoiceIdentity;
    @SerializedName("billToName")
    @Expose
    private String billToName;
    @SerializedName("dateUtc")
    @Expose
    private String dateUtc;
    @SerializedName("dueDateUtc")
    @Expose
    private String dueDateUtc;
    @SerializedName("taxAmount")
    @Expose
    private Double taxAmount;
    @SerializedName("discountAmount")
    @Expose
    private Double discountAmount;
    @SerializedName("subTotalAmount")
    @Expose
    private Double subTotalAmount;
    @SerializedName("totalAmount")
    @Expose
    private Double totalAmount;
    @SerializedName("refundAmount")
    @Expose
    private Double refundAmount;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("paymentStatus")
    @Expose
    private Integer paymentStatus;
    @SerializedName("paymentStatusEnum")
    @Expose
    private Integer paymentStatusEnum;
    @SerializedName("invoiceStatus")
    @Expose
    private Integer invoiceStatus;
    @SerializedName("invoicePaymentUrl")
    @Expose
    private String invoicePaymentUrl;

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

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceIdentity() {
        return invoiceIdentity;
    }

    public void setInvoiceIdentity(String invoiceIdentity) {
        this.invoiceIdentity = invoiceIdentity;
    }

    public String getBillToName() {
        return billToName;
    }

    public void setBillToName(String billToName) {
        this.billToName = billToName;
    }

    public String getDateUtc() {
        return dateUtc;
    }

    public void setDateUtc(String dateUtc) {
        this.dateUtc = dateUtc;
    }

    public String getDueDateUtc() {
        return dueDateUtc;
    }

    public void setDueDateUtc(String dueDateUtc) {
        this.dueDateUtc = dueDateUtc;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Double getSubTotalAmount() {
        return subTotalAmount;
    }

    public void setSubTotalAmount(Double subTotalAmount) {
        this.subTotalAmount = subTotalAmount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getPaymentStatusEnum() {
        return paymentStatusEnum;
    }

    public void setPaymentStatusEnum(Integer paymentStatusEnum) {
        this.paymentStatusEnum = paymentStatusEnum;
    }

    public Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoicePaymentUrl() {
        return invoicePaymentUrl;
    }

    public void setInvoicePaymentUrl(String invoicePaymentUrl) {
        this.invoicePaymentUrl = invoicePaymentUrl;
    }

}
