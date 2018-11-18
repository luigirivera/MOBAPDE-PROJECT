package com.example.luigi.journally;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseHelper dbHelper;
    private Button save;
    private Boolean locationPerms = false;
    private final int LOCATION_PERMISSION_REQUEST_CODE = 1698;
    private FusedLocationProviderClient fusedLocationProviderClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        getLocationPermission();



        save = findViewById(R.id.saveBtn);

        if(DatabaseHelper.getInstance(this).getPassword() == null)
            DatabaseHelper.getInstance(this).createPassword();
    }

    private void getCurrentLocation()
    {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try{
            if(locationPerms)
            {
                fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if(task.isSuccessful())
                        {
                            Location location = (Location) task.getResult();
                            LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15f));
                        }
                    }
                });
            }

        }catch(SecurityException e){}
    }

    private void startMap()
    {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void getLocationPermission()
    {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                locationPerms = true;
                startMap();
            }
            else
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
        else
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
    }

    public void onClick(View view) {
        //TODO: Save location

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.journalItem:
                startActivity(new Intent(getApplicationContext(), JournalActivity.class));
                return true;
            case R.id.vaultItem:
                openVaultDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void openVaultDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setTitle(R.string.passphrase_title);
        builder.setView(input);
        builder.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                passOnClick(input);
            }
        });

        builder.create().show();
    }

    private void passOnClick(EditText input)
    {
        Cursor data = DatabaseHelper.getInstance(this).getPassword();

        if(input.getText().toString().equals(data.getString(0)))
            startActivity(new Intent(getApplicationContext(), VaultActivity.class));

        else
            Toast.makeText(this.getApplicationContext(), R.string.incorrect_passphrase, Toast.LENGTH_LONG).show();
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        mMap.setMyLocationEnabled(true);
        if(locationPerms)
        {
            getCurrentLocation();
            mMap.setMyLocationEnabled(true);
        }

//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
