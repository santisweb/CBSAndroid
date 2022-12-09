package com.cristalbusinessservices.Model.Document;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultDocument {
    @SerializedName("taxDocumentId")
    @Expose
    private Integer taxDocumentId;
    @SerializedName("taxId")
    @Expose
    private Integer taxId;
    @SerializedName("mediaContentId")
    @Expose
    private Integer mediaContentId;
    @SerializedName("taxDocumentTypeId")
    @Expose
    private Integer taxDocumentTypeId;
    @SerializedName("taxpayerId")
    @Expose
    private Integer taxpayerId;
    @SerializedName("contactId")
    @Expose
    private Integer contactId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("fileName")
    @Expose
    private String fileName;
    @SerializedName("altText")
    @Expose
    private Object altText;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("cdnPartialPath")
    @Expose
    private String cdnPartialPath;
    @SerializedName("createdDateUtc")
    @Expose
    private String createdDateUtc;

    public Integer getTaxDocumentId() {
        return taxDocumentId;
    }

    public void setTaxDocumentId(Integer taxDocumentId) {
        this.taxDocumentId = taxDocumentId;
    }

    public Integer getTaxId() {
        return taxId;
    }

    public void setTaxId(Integer taxId) {
        this.taxId = taxId;
    }

    public Integer getMediaContentId() {
        return mediaContentId;
    }

    public void setMediaContentId(Integer mediaContentId) {
        this.mediaContentId = mediaContentId;
    }

    public Integer getTaxDocumentTypeId() {
        return taxDocumentTypeId;
    }

    public void setTaxDocumentTypeId(Integer taxDocumentTypeId) {
        this.taxDocumentTypeId = taxDocumentTypeId;
    }

    public Integer getTaxpayerId() {
        return taxpayerId;
    }

    public void setTaxpayerId(Integer taxpayerId) {
        this.taxpayerId = taxpayerId;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Object getAltText() {
        return altText;
    }

    public void setAltText(Object altText) {
        this.altText = altText;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCdnPartialPath() {
        return cdnPartialPath;
    }

    public void setCdnPartialPath(String cdnPartialPath) {
        this.cdnPartialPath = cdnPartialPath;
    }

    public String getCreatedDateUtc() {
        return createdDateUtc;
    }

    public void setCreatedDateUtc(String createdDateUtc) {
        this.createdDateUtc = createdDateUtc;
    }
}
