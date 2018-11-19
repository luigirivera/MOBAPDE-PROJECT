package com.example.luigi.journally;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class JournalAdapter extends RecyclerView.Adapter<JournalHolder> {
    private ArrayList<JournalModel> journal;
    private JournalActivity journalActivity;
    public JournalAdapter(JournalActivity journalActivity)
    {
        this.journalActivity = journalActivity;
        journal = DatabaseHelper.getInstance(journalActivity).getJournal();
    }
    @Override
    public JournalHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.journal_row, viewGroup, false);

        final JournalHolder holder = new JournalHolder(view);

        holder.getLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(journalActivity.getApplicationContext(), SoloJournalActivity.class);

                intent.putExtra("ID", holder.getId());
                intent.putExtra("TITLE", holder.getTitle().getText());
                intent.putExtra("TIMESTAMP", holder.getTime().getText());
                intent.putExtra("NAME", holder.getName());
                intent.putExtra("COORDS", holder.getLocation().getText());
                intent.putExtra("DESC", holder.getDescription());

                journalActivity.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(JournalHolder journalHolder, int i) {
        journalHolder.setId(journal.get(i).getId());
        journalHolder.getTitle().setText(journal.get(i).getTitle());
        journalHolder.setDescription(journal.get(i).getDescription());
        journalHolder.getLocation().setText(journal.get(i).getLocationCoordinates());
        journalHolder.setName(journal.get(i).getLocationName());
        journalHolder.getTime().setText(journal.get(i).getTimestamp());
    }

    @Override
    public int getItemCount() { return journal.size(); }
}
