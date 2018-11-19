package com.example.luigi.journally;

public class LocationModel {

    private long id;
    private String name;
    private double lat;
    private double longt;
    private String timestamp;

    public LocationModel(long id, String name, double lat, double longt, String timestamp)
    {
        this.setTimestamp(timestamp);
        this.setId(id);
        this.setName(name);
        this.setLat(lat);
        this.setLongt(longt);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongt() {
        return longt;
    }

    public void setLongt(double longt) {
        this.longt = longt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
