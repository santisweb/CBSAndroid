package com.cristalbusinessservices.Model.Appointments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Organization {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shortDescription")
    @Expose
    private Object shortDescription;
    @SerializedName("email")
    @Expose
    private Object email;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("fax")
    @Expose
    private Object fax;
    @SerializedName("website")
    @Expose
    private Object website;
    @SerializedName("cdnPath")
    @Expose
    private Object cdnPath;
    @SerializedName("fileName")
    @Expose
    private Object fileName;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("createdDateUtc")
    @Expose
    private String createdDateUtc;
    @SerializedName("createdByUserId")
    @Expose
    private Integer createdByUserId;
    @SerializedName("createdByUserIp")
    @Expose
    private String createdByUserIp;
    @SerializedName("changedDateUtc")
    @Expose
    private String changedDateUtc;
    @SerializedName("changedByUserId")
    @Expose
    private Object changedByUserId;
    @SerializedName("changedByUserIp")
    @Expose
    private String changedByUserIp;
    @SerializedName("calendarEvents")
    @Expose
    private List<Object> calendarEvents = null;
    @SerializedName("contacts")
    @Expose
    private List<Object> contacts = null;
    @SerializedName("opportunities")
    @Expose
    private List<Object> opportunities = null;
    @SerializedName("user")
    @Expose
    private Object user;
    @SerializedName("user1")
    @Expose
    private Object user1;
    @SerializedName("organizationAddressId")
    @Expose
    private Integer organizationAddressId;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("organizationStatus")
    @Expose
    private Integer organizationStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(Object shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getFax() {
        return fax;
    }

    public void setFax(Object fax) {
        this.fax = fax;
    }

    public Object getWebsite() {
        return website;
    }

    public void setWebsite(Object website) {
        this.website = website;
    }

    public Object getCdnPath() {
        return cdnPath;
    }

    public void setCdnPath(Object cdnPath) {
        this.cdnPath = cdnPath;
    }

    public Object getFileName() {
        return fileName;
    }

    public void setFileName(Object fileName) {
        this.fileName = fileName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedDateUtc() {
        return createdDateUtc;
    }

    public void setCreatedDateUtc(String createdDateUtc) {
        this.createdDateUtc = createdDateUtc;
    }

    public Integer getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Integer createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getCreatedByUserIp() {
        return createdByUserIp;
    }

    public void setCreatedByUserIp(String createdByUserIp) {
        this.createdByUserIp = createdByUserIp;
    }

    public String getChangedDateUtc() {
        return changedDateUtc;
    }

    public void setChangedDateUtc(String changedDateUtc) {
        this.changedDateUtc = changedDateUtc;
    }

    public Object getChangedByUserId() {
        return changedByUserId;
    }

    public void setChangedByUserId(Object changedByUserId) {
        this.changedByUserId = changedByUserId;
    }

    public String getChangedByUserIp() {
        return changedByUserIp;
    }

    public void setChangedByUserIp(String changedByUserIp) {
        this.changedByUserIp = changedByUserIp;
    }

    public List<Object> getCalendarEvents() {
        return calendarEvents;
    }

    public void setCalendarEvents(List<Object> calendarEvents) {
        this.calendarEvents = calendarEvents;
    }

    public List<Object> getContacts() {
        return contacts;
    }

    public void setContacts(List<Object> contacts) {
        this.contacts = contacts;
    }

    public List<Object> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<Object> opportunities) {
        this.opportunities = opportunities;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Object getUser1() {
        return user1;
    }

    public void setUser1(Object user1) {
        this.user1 = user1;
    }

    public Integer getOrganizationAddressId() {
        return organizationAddressId;
    }

    public void setOrganizationAddressId(Integer organizationAddressId) {
        this.organizationAddressId = organizationAddressId;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Integer getOrganizationStatus() {
        return organizationStatus;
    }

    public void setOrganizationStatus(Integer organizationStatus) {
        this.organizationStatus = organizationStatus;
    }

}
