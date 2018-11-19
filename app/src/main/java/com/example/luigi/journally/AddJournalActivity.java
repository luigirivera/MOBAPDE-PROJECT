package com.example.luigi.journally;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddJournalActivity extends AppCompatActivity {
    private Button setLocation, save;
    private EditText title, entry;
    private String name;
    private Double lat, longt;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_journal);

        prefs = getSharedPreferences("LOCATION", Context.MODE_PRIVATE);
        title = findViewById(R.id.newTitleTxt);
        entry = findViewById(R.id.entryTxt);

        setLocation = findViewById(R.id.setLocationBtn);
        save = findViewById(R.id.saveBtn);

        SharedPreferences.Editor editor = prefs.edit();
        editor.clear().commit();
    }

    public void save(View v)
    {
        if(!title.getText().toString().isEmpty() && !entry.getText().toString().isEmpty() && lat != null  && longt != null && name != null)
            DatabaseHelper.getInstance(this).addJournal(title.getText().toString(), entry.getText().toString(), name, lat, longt);

        finish();
    }

    public void setLocation(View v)
    {
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
            startActivity(new Intent(getApplicationContext(), LocationsJournalActivity.class));

        else
            Toast.makeText(this.getApplicationContext(), R.string.incorrect_passphrase, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        name = prefs.getString("NAME", null);
        if(name != null)
            setLocation.setText(name);

        try{
            lat = Double.parseDouble(prefs.getString("LAT", null));
            longt = Double.parseDouble(prefs.getString("LONG", null));
        }catch(NullPointerException e)
        {
            lat = null;
            longt = null;
        }


        Log.d("JOURNAL.LY", "Got Location for Journal: " + name + "  (" + lat + ", " + longt + ")");
    }
}
