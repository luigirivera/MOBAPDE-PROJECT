package com.example.luigi.journally;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class VaultActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private LocationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault);

        recyclerView = findViewById(R.id.recyclerViewVault);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new LocationAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.vaultmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.passItem:
                changePass();
                return true;
            case R.id.clearVaultItem:
                //TODO: ask if they want to clear; do action based on answer
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void changePass()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText oldpass = new EditText(this);
        oldpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setTitle(R.string.passphrase_title);
        builder.setView(oldpass);
            builder.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    oldPass(oldpass);
                }
            });
        builder.create().show();
    }

    private void oldPass(EditText oldpass)
    {
        if(oldpass.getText().toString().equals(DatabaseHelper.getInstance(this).getPassword().getString(0))){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final EditText newpass = new EditText(this);
            newpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setTitle(R.string.change_passphrase_title);
            builder.setView(newpass);
            builder.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    newPass(newpass);
                }
            });

            builder.create().show();
        }
        else
            Toast.makeText(this.getApplicationContext(), R.string.incorrect_passphrase, Toast.LENGTH_LONG).show();
    }

    private void newPass(EditText newPass)
    {
        DatabaseHelper.getInstance(this).updatePassword(newPass.getText().toString());
    }
}
