package com.example.luigi.journally;


public class JournalModel {

    private int id;
    private String title;
    private String description;
    private String locationCoordinates;
    private String locationName;
    private String timestamp;

    public JournalModel(int id, String title, String description, double lat, double longt, String locationName, String timestamp)
    {
        this.setId(id);
        this.setTimestamp(timestamp);
        this.setTitle(title);
        this.setDescription(description);
        this.setLocationCoordinates("(" + lat + ", " + longt + ")");
        this.setLocationName(locationName);

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocationCoordinates() {
        return locationCoordinates;
    }

    public void setLocationCoordinates(String locationCoordinates) {
        this.locationCoordinates = locationCoordinates;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
