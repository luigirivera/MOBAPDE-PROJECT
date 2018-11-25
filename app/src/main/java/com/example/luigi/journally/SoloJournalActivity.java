package com.example.luigi.journally;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class SoloJournalActivity extends AppCompatActivity {

    private long id;
    private TextView titleTxt, nameTxt, timestampTxt, descriptionTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo_journal);

        Intent intent = getIntent();

        titleTxt = findViewById(R.id.titleTxt);
        timestampTxt = findViewById(R.id.timestampTxt);
        nameTxt = findViewById(R.id.nameTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);


        id = intent.getLongExtra("ID", 0);

        titleTxt.setText(intent.getStringExtra("TITLE"));
        timestampTxt.setText(intent.getStringExtra("TIMESTAMP"));
        nameTxt.setText(intent.getStringExtra("NAME"));
        descriptionTxt.setText(intent.getStringExtra("DESC"));

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
                deleteEntry();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deleteEntry()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.delete_journal_title);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete();
            }
        });

        builder.setNegativeButton(R.string.no, null);

        builder.create().show();
    }

    private void delete()
    {
        DatabaseHelper.getInstance(this).deleteJournalEntry(id);
        Log.d("JOURNAL.LY", id + " Journal Entry Deleted");
        finish();
    }
}
