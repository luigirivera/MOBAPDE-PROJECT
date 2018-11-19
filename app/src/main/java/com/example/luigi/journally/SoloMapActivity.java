package com.example.luigi.journally;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SoloMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int id;
    private String name;
    private String timestamp;
    private double lat;
    private double longt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_map);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        name = intent.getStringExtra("NAME");
        timestamp = intent.getStringExtra("TIMESTAMP");
        lat = intent.getDoubleExtra("LAT", 0);
        longt = intent.getDoubleExtra("LONGT", 0);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.solo_map_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.deleteMapItem:
                //TODO: ask if they want to delete; do action based on answer
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng marker = new LatLng(lat, longt);
        mMap.addMarker(new MarkerOptions().position(marker).title(name).snippet(timestamp));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));

        Log.d("JOURNAL.LY", "Solo Map Displayed");
    }
}
