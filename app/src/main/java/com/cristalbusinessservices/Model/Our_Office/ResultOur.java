package com.cristalbusinessservices.Model.Our_Office;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultOur {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("directions")
    @Expose
    private String directions;
    @SerializedName("officeManager")
    @Expose
    private String officeManager;
    @SerializedName("facebookUrl")
    @Expose
    private String facebookUrl;
    @SerializedName("instagramUrl")
    @Expose
    private String instagramUrl;
    @SerializedName("twitterUrl")
    @Expose
    private String twitterUrl;
    @SerializedName("linkedInUrl")
    @Expose
    private String linkedInUrl;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("youtubeUrl")
    @Expose
    private String youtubeUrl;

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

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public String getOfficeManager() {
        return officeManager;
    }

    public void setOfficeManager(String officeManager) {
        this.officeManager = officeManager;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getInstagramUrl() {
        return instagramUrl;
    }

    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getLinkedInUrl() {
        return linkedInUrl;
    }

    public void setLinkedInUrl(String linkedInUrl) {
        this.linkedInUrl = linkedInUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

}