package com.example.luigi.journally;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper dbHelper;

    private static final String LOG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "JOURNALLY";

    private static final String TABLE_USER = "USER";
    private static final String TABLE_LOCATION = "LOCATION";
    private static final String TABLE_JOURNAL = "JOURNAL";

    // FOR ALL TABLES
    private static final String KEY_ID = "id";

    // FOR USER
    private static final String KEY_PASS = "passphrase";

    // FOR LOCATION AND JOURNAL
    private static final String KEY_LAT = "latitude";
    private static final String KEY_LONG = "longitude";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";

    // FOR JOURNAL
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESC = "description";

    private static final String CREATE_USER = "CREATE TABLE " + TABLE_USER +
                                                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                KEY_PASS + " TEXT)";

    private static final String CREATE_LOCATION = "CREATE TABLE " + TABLE_LOCATION +
                                                    "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    KEY_NAME + " TEXT, " + KEY_DATE + " DATETIME, " +
                                                    KEY_LAT + " REAL, " + KEY_LONG + " REAL)";

    private static final String CREATE_JOURNAL = "CREATE TABLE " + TABLE_JOURNAL +
                                                    "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    KEY_TITLE + " TEXT, " + KEY_DESC + " TEXT, " +
                                                    KEY_NAME + " TEXT, " + KEY_DATE + " DATETIME, " +
                                                    KEY_LAT + " REAL, " + KEY_LONG + " REAL)";
    private DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {

        if (dbHelper == null)
            dbHelper = new DatabaseHelper(context.getApplicationContext());

        return dbHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_LOCATION);
        db.execSQL(CREATE_JOURNAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOURNAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    public boolean addLocation(String name, double lat, double longt)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_NAME, name);
        values.put(KEY_LAT, lat);
        values.put(KEY_LONG, longt);
        values.put(KEY_DATE, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        long result = db.insert(TABLE_LOCATION, null, values);

        if( result == -1) return false;
        else return true;
    }



    public boolean addJournal(String title, String description, String name, double lat, double longt)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_TITLE, title);
        values.put(KEY_DESC, description);
        values.put(KEY_NAME, name);
        values.put(KEY_LAT, lat);
        values.put(KEY_LONG, longt);
        values.put(KEY_DATE, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        long result = db.insert(TABLE_JOURNAL, null, values);

        if( result == -1) return false;
        else return true;
    }

    public void createPassword()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL(CREATE_USER);

        ContentValues values = new ContentValues();

        values.put(KEY_PASS, "password");

        db.insert(TABLE_USER, null, values);

    }

    public void updatePassword(String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_PASS, pass);
        db.update(TABLE_USER, values, KEY_ID + " = ?", new String[]{String.valueOf(1)});
    }


    public Cursor getPassword()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + KEY_PASS +" FROM " + TABLE_USER;
        try{
            Cursor data = db.rawQuery(query, null);
            data.moveToFirst();
            return data;
        }catch(SQLiteException exception){
            return null;
        }
    }

    public void resetPassword()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_USER +
                "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KEY_PASS + " TEXT)";

        db.execSQL(query);

        ContentValues values = new ContentValues();

        values.put(KEY_PASS, "password");

        db.insert(TABLE_USER, null, values);
    }

    public Cursor getLocations()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_LOCATION;

        try{
            Cursor data = db.rawQuery(query, null);
            data.moveToFirst();
            return data;
        }catch(SQLiteException exception){
            return null;
        }
    }

    public Cursor getJournal()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_JOURNAL;

        try{
            Cursor data = db.rawQuery(query, null);
            data.moveToFirst();
            return data;
        }catch(SQLiteException exception){
            return null;
        }
    }

    public void wipeLocations()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
        db.execSQL(CREATE_LOCATION);
    }

    public void wipeJournal()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOURNAL);
        db.execSQL(CREATE_JOURNAL);
    }
}
