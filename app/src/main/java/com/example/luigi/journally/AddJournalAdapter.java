package com.example.luigi.journally;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AddJournalAdapter extends RecyclerView.Adapter<LocationHolder> {

    private LocationsJournalActivity locationsJournalActivity;
    private ArrayList<LocationModel> locations;
    private String name;
    private double lat;
    private double longt;
    public AddJournalAdapter(LocationsJournalActivity locationsJournalActivity)
    {
        this.locationsJournalActivity = locationsJournalActivity;

        locations = DatabaseHelper.getInstance(locationsJournalActivity).getLocations();
    }
    @Override
    public LocationHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.vault_row, viewGroup, false);

        final LocationHolder holder = new LocationHolder(view);

        holder.getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = holder.getName().getText().toString();
                lat = holder.getLat();
                longt = holder.getLongt();
                locationsJournalActivity.finish();
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(LocationHolder locationHolder, int i) {
        locationHolder.setId(locations.get(i).getId());
        locationHolder.getName().setText(locations.get(i).getName());
        locationHolder.getTimestamp().setText(locations.get(i).getTimestamp());
        locationHolder.setLat(locations.get(i).getLat());
        locationHolder.setLongt(locations.get(i).getLongt());
    }

    @Override
    public int getItemCount() { return locations.size(); }


    public String getName() {
        return name;
    }


    public double getLat() {
        return lat;
    }

    public double getLongt() {
        return longt;
    }
}
