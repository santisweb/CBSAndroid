package com.cristalbusinessservices.Model.Appointments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultAppointments {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("organizationId")
    @Expose
    private Integer organizationId;
    @SerializedName("businessId")
    @Expose
    private Integer businessId;
    @SerializedName("calendarEventId")
    @Expose
    private Object calendarEventId;
    @SerializedName("assignedToContactId")
    @Expose
    private Integer assignedToContactId;
    @SerializedName("customerContactId")
    @Expose
    private Integer customerContactId;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("createdByUserId")
    @Expose
    private Integer createdByUserId;
    @SerializedName("createdDateUtc")
    @Expose
    private String createdDateUtc;
    @SerializedName("changedDateUtc")
    @Expose
    private Object changedDateUtc;
    @SerializedName("changedByUserId")
    @Expose
    private Object changedByUserId;
    @SerializedName("meetingId")
    @Expose
    private String meetingId;
    @SerializedName("meetingUrl")
    @Expose
    private Object meetingUrl;
    @SerializedName("company")
    @Expose
    private Object company;
    @SerializedName("user")
    @Expose
    private Object user;
    @SerializedName("contact")
    @Expose
    private Contact contact;
    @SerializedName("assignedToContact")
    @Expose
    private Object assignedToContact;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("organization")
    @Expose
    private Organization organization;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Object getCalendarEventId() {
        return calendarEventId;
    }

    public void setCalendarEventId(Object calendarEventId) {
        this.calendarEventId = calendarEventId;
    }

    public Integer getAssignedToContactId() {
        return assignedToContactId;
    }

    public void setAssignedToContactId(Integer assignedToContactId) {
        this.assignedToContactId = assignedToContactId;
    }

    public Integer getCustomerContactId() {
        return customerContactId;
    }

    public void setCustomerContactId(Integer customerContactId) {
        this.customerContactId = customerContactId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Integer createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getCreatedDateUtc() {
        return createdDateUtc;
    }

    public void setCreatedDateUtc(String createdDateUtc) {
        this.createdDateUtc = createdDateUtc;
    }

    public Object getChangedDateUtc() {
        return changedDateUtc;
    }

    public void setChangedDateUtc(Object changedDateUtc) {
        this.changedDateUtc = changedDateUtc;
    }

    public Object getChangedByUserId() {
        return changedByUserId;
    }

    public void setChangedByUserId(Object changedByUserId) {
        this.changedByUserId = changedByUserId;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public Object getMeetingUrl() {
        return meetingUrl;
    }

    public void setMeetingUrl(Object meetingUrl) {
        this.meetingUrl = meetingUrl;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Object getAssignedToContact() {
        return assignedToContact;
    }

    public void setAssignedToContact(Object assignedToContact) {
        this.assignedToContact = assignedToContact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

}
