package com.example.luigi.journally;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class JournalHolder extends RecyclerView.ViewHolder  {
    private int id;
    private TextView title;
    private String description;
    private TextView location;
    private String name;
    private TextView time;

    private ConstraintLayout layout;

    public JournalHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.titleTxt);
        location = itemView.findViewById(R.id.locationTxt);
        time = itemView.findViewById(R.id.timeTxt);

        layout = itemView.findViewById(R.id.journalRowLayout);
    }


    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public TextView getLocation() {
        return location;
    }

    public void setLocation(TextView location) {
        this.location = location;
    }

    public TextView getTime() {
        return time;
    }

    public void setTime(TextView time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConstraintLayout getLayout() {
        return layout;
    }

    public void setLayout(ConstraintLayout layout) {
        this.layout = layout;
    }
}
