package com.example.luigi.journally;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class SoloJournalActivity extends AppCompatActivity {

    private int id;
    private String title;
    private String coords;
    private String time;
    private String name;
    private TextView titleTxt, coordsTxt, nameTxt, timestampTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_journal);

        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);
        title = intent.getStringExtra("TITLE");
        coords = intent.getStringExtra("COORDS");
        time = intent.getStringExtra("TIMESTAMP");
        name = intent.getStringExtra("NAME");



        Log.d("JOURNAL.LY", "Solo Journal Displayed");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.solo_journal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.deleteJournalItem:
                //TODO: ask if they want to delete; do action based on answer
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
