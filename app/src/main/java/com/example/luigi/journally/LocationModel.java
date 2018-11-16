package com.example.luigi.journally;

public class LocationModel {
    private String name;
    private long lat;
    private long longt;

    public LocationModel(String name, long lat, long longt)
    {
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
}
