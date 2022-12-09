package com.cristalbusinessservices.Model.User_Info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultUserInfo implements Serializable {
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("contactId")
    @Expose
    private Integer contactId;
    @SerializedName("organizationId")
    @Expose
    private Integer organizationId;
    @SerializedName("businessId")
    @Expose
    private Integer businessId;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("userImagePartialPath")
    @Expose
    private String userImagePartialPath;
    @SerializedName("userImageFullPath")
    @Expose
    private String userImageFullPath;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserImagePartialPath() {
        return userImagePartialPath;
    }

    public void setUserImagePartialPath(String userImagePartialPath) {
        this.userImagePartialPath = userImagePartialPath;
    }

    public String getUserImageFullPath() {
        return userImageFullPath;
    }

    public void setUserImageFullPath(String userImageFullPath) {
        this.userImageFullPath = userImageFullPath;
    }
}
