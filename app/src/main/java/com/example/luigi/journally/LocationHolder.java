package com.example.luigi.journally;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class LocationHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView coords;
    private TextView timestamp;
    private double lat;
    private double longt;

    public LocationHolder(View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.nameTxt);
        coords = itemView.findViewById(R.id.coordsTxt);
        timestamp = itemView.findViewById(R.id.timestampTxt);
    }


    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getCoords() {
        return coords;
    }

    public void setCoords(TextView coords) {
        this.coords = coords;
    }

    public TextView getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(TextView timestamp) {
        this.timestamp = timestamp;
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
}
