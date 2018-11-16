package com.example.luigi.journally;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

public class JournalAdapter extends RecyclerView.Adapter<JournalHolder> {
    private ArrayList<JournalModel> journal;

    public JournalAdapter()
    {
        journal = new ArrayList<JournalModel>();
    }
    @Override
    public JournalHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(JournalHolder journalHolder, int i) {

    }

    @Override
    public int getItemCount() { return journal.size();  }
}
