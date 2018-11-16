package com.example.luigi.journally;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationHolder>{

    public ArrayList<LocationModel> locations;

    public LocationAdapter()
    {
        locations = new ArrayList<LocationModel>();
    }

    @Override
    public LocationHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(LocationHolder locationHolder, int i) {

    }

    @Override
    public int getItemCount() { return locations.size(); }
}
