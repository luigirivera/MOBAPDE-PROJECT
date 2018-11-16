package com.example.luigi.journally;

public class LocationModel {

    private long id;
    private String name;
    private long lat;
    private long longt;
    private String timestamp;

    public LocationModel(long id, String name, long lat, long longt, String timestamp)
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

    public long getLat() {
        return lat;
    }

    public void setLat(long lat) {
        this.lat = lat;
    }

    public long getLongt() {
        return longt;
    }

    public void setLongt(long longt) {
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
