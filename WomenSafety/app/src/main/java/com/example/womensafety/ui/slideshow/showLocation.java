package com.example.womensafety.ui.slideshow;

public class showLocation {
    String id,lat,log,date,cid;

    public showLocation(String id,String lat, String log, String date, String cid) {
        this.id=id;
        this.lat = lat;
        this.log = log;
        this.date = date;
        this.cid = cid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
