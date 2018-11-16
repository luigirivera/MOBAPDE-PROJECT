package com.example.luigi.journally;

public class JournalModel {

    private String title;
    private String description;
    private String locationCoordinates;
    private String locationName;

    public JournalModel(String title, String description, long lat, long longt, String locationName)
    {
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
}
