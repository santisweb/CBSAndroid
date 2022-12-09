package com.cristalbusinessservices.Model.Enabled_Businesses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultEnableBusinesses {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("businessType")
    @Expose
    private String businessType;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("address2")
    @Expose
    private String address2;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("maplink")
    @Expose
    private String maplink;
    @SerializedName("officeManager")
    @Expose
    private String officeManager;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("nearByLandmarks")
    @Expose
    private Object nearByLandmarks;
    @SerializedName("searchLocationLatitude")
    @Expose
    private Integer searchLocationLatitude;
    @SerializedName("searchLocationLongitude")
    @Expose
    private Integer searchLocationLongitude;
    @SerializedName("websiteUrl")
    @Expose
    private Object websiteUrl;
    @SerializedName("distanceFromAddress")
    @Expose
    private Double distanceFromAddress;
    @SerializedName("isMobileAppEnable")
    @Expose
    private Boolean isMobileAppEnable;

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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getMaplink() {
        return maplink;
    }

    public void setMaplink(String maplink) {
        this.maplink = maplink;
    }

    public String getOfficeManager() {
        return officeManager;
    }

    public void setOfficeManager(String officeManager) {
        this.officeManager = officeManager;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Object getNearByLandmarks() {
        return nearByLandmarks;
    }

    public void setNearByLandmarks(Object nearByLandmarks) {
        this.nearByLandmarks = nearByLandmarks;
    }

    public Integer getSearchLocationLatitude() {
        return searchLocationLatitude;
    }

    public void setSearchLocationLatitude(Integer searchLocationLatitude) {
        this.searchLocationLatitude = searchLocationLatitude;
    }

    public Integer getSearchLocationLongitude() {
        return searchLocationLongitude;
    }

    public void setSearchLocationLongitude(Integer searchLocationLongitude) {
        this.searchLocationLongitude = searchLocationLongitude;
    }

    public Object getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(Object websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Double getDistanceFromAddress() {
        return distanceFromAddress;
    }

    public void setDistanceFromAddress(Double distanceFromAddress) {
        this.distanceFromAddress = distanceFromAddress;
    }

    public Boolean getIsMobileAppEnable() {
        return isMobileAppEnable;
    }

    public void setIsMobileAppEnable(Boolean isMobileAppEnable) {
        this.isMobileAppEnable = isMobileAppEnable;
    }
}
