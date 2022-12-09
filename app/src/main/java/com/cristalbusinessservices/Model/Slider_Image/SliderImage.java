package com.cristalbusinessservices.Model.Slider_Image;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SliderImage {
    @SerializedName("imageName")
    @Expose
    private String imageName;
    @SerializedName("linkUrl")
    @Expose
    private Object linkUrl;
    @SerializedName("imageFullUrl")
    @Expose
    private String imageFullUrl;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Object getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(Object linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getImageFullUrl() {
        return imageFullUrl;
    }

    public void setImageFullUrl(String imageFullUrl) {
        this.imageFullUrl = imageFullUrl;
    }
}
