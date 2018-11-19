package com.example.luigi.journally;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

public class LocationsJournalActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AddJournalAdapter adapter;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_journal);

        prefs = getApplicationContext().getSharedPreferences("LOCATION", Context.MODE_PRIVATE);

        recyclerView = findViewById(R.id.recyclerViewAddJournal);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new AddJournalAdapter(this);
        recyclerView.setAdapter(adapter);

        Log.d("JOURNAL.LY", "Vault Displayed");
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = prefs.edit();

        String name;
        double lat, longt;

        name = adapter.getName();
        lat = adapter.getLat();
        longt = adapter.getLongt();
        editor.putString("NAME", name);
        editor.putString("LAT", String.valueOf(lat));
        editor.putString("LONG", String.valueOf(longt));

        editor.commit();
    }
}
