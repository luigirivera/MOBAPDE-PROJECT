package com.example.luigi.journally;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationHolder>{

    private ArrayList<LocationModel> locations;
    private VaultActivity vaultActivity;
    public LocationAdapter(VaultActivity vaultActivity)
    {
        this.vaultActivity = vaultActivity;
        locations = DatabaseHelper.getInstance(vaultActivity).getLocations();
    }

    @Override
    public LocationHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.vault_row, viewGroup, false);

        final LocationHolder holder = new LocationHolder(view);

        holder.getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vaultActivity.getApplicationContext(), SoloMapActivity.class);

                intent.putExtra("ID", holder.getId());
                intent.putExtra("NAME", holder.getName().getText());
                intent.putExtra("LAT", holder.getLat());
                intent.putExtra("LONGT", holder.getLongt());

                vaultActivity.startActivity(intent);
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

    public void updateList()
    {
        locations = DatabaseHelper.getInstance(vaultActivity).getLocations();
        notifyDataSetChanged();
        Log.d("JOURNAL.LY", "Vault Data Updated");
    }
}
