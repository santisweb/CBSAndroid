package com.cristalbusinessservices.Model;

public class Meeting {
    String title, mess, url;

    public Meeting(String title, String mess, String url) {
        this.title = title;
        this.mess = mess;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getMess() {
        return mess;
    }
}
