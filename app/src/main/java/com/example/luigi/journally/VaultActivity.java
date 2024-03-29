package com.example.luigi.journally;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.provider.ContactsContract;
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

        adapter = new LocationAdapter(this);
        recyclerView.setAdapter(adapter);

        Log.d("JOURNAL.LY", "Vault Displayed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateList();
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
                clearVault();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void clearVault()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.wipe_vault_title);
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
        DatabaseHelper.getInstance(this).wipeLocations();

        Log.d("JOURNAL.LY", "Vault Wiped");
        adapter.updateList();
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
        String hash = DatabaseHelper.getInstance(this).hash(oldpass.getText().toString());
        String pass = DatabaseHelper.getInstance(this).getPassword();
        if(hash.equals(pass)){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final EditText newpass = new EditText(this);
            newpass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            builder.setTitle(R.string.change_passphrase_title);
            builder.setView(newpass);
            builder.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    newPass(newpass.getText().toString());
                }
            });

            builder.create().show();
        }
        else
            Toast.makeText(this.getApplicationContext(), R.string.incorrect_passphrase, Toast.LENGTH_LONG).show();
    }

    private void newPass(final String newPass)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText confirmPass = new EditText(this);
        confirmPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setTitle(R.string.confirm_new_pass);
        builder.setView(confirmPass);
        builder.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirmPass(newPass, confirmPass.getText().toString());
            }
        });

        builder.create().show();
    }

    private void confirmPass(String newPass, String confirmPass)
    {
        if(newPass.equals(confirmPass))
        {
            DatabaseHelper.getInstance(this).updatePassword(newPass);
            Toast.makeText(this.getApplicationContext(), R.string.passphrase_change_success, Toast.LENGTH_LONG).show();
        }

        else
            Toast.makeText(this.getApplicationContext(), R.string.unmatch_passphrase, Toast.LENGTH_LONG).show();
    }
}
