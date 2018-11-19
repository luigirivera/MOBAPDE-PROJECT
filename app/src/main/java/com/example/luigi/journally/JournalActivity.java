package com.example.luigi.journally;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class JournalActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private JournalAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        recyclerView = findViewById(R.id.recyclerViewJournal);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new JournalAdapter(this);
        recyclerView.setAdapter(adapter);

        Log.d("JOURNAL.LY", "Journal Displayed");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.journalmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.addJournalEntry:
                wipeJournal();
                return true;
            case R.id.clearJournalItem:
                wipeJournal();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void wipeJournal()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.wipe_journal_title);
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                wipe();
            }
        });

        builder.setNegativeButton(R.string.no, null);

        builder.create().show();
    }

    private void wipe()
    {
        DatabaseHelper.getInstance(this).wipeJournal();
        Log.d("JOURNAL.LY", "Journal Wiped");
    }
}
