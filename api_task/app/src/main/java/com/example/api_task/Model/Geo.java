package com.example.api_task.Model;

public class Geo {
    public String lat;
    public String lng;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Geo(String lat, String lng) {
        this.lat = lat;
        this.lng = lng;
    }
}