package com.example.luigi.journally;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper dbHelper;

    private static final String LOG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "JOURNALLY";

    private static final String TABLE_LOCATION = "LOCATION";
    private static final String TABLE_JOURNAL = "JOURNAL";

    // FOR BOTH TABLES
    private static final String KEY_ID = "id";
    private static final String KEY_LAT = "latitude";
    private static final String KEY_LONG = "longitude";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";

    // FOR JOURNAL
    private static final String KEY_TITLE = "title";
    private static final String KEY_DESC = "description";

    private static final String CREATE_LOCATION = "CREATE TABLE " + TABLE_LOCATION +
                                                    "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    KEY_NAME + " TEXT, " + KEY_DATE + " DATETIME, " +
                                                    KEY_LAT + " INTEGER, " + KEY_LONG + " INTEGER)";

    private static final String CREATE_JOURNAL = "CREATE TABLE " + TABLE_JOURNAL +
                                                    "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    KEY_TITLE + " TEXT, " + KEY_DESC + " TEXT, " +
                                                    KEY_NAME + " TEXT, " + KEY_DATE + " DATETIME, " +
                                                    KEY_LAT + " INTEGER, " + KEY_LONG + " INTEGER)";
    private DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (dbHelper == null)
            dbHelper = new DatabaseHelper(context.getApplicationContext());

        return dbHelper;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LOCATION);
        db.execSQL(CREATE_JOURNAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JOURNAL);

        onCreate(db);
    }

    public boolean addJournal(String title, String description, String name, long lat, long longt)
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

    public Cursor getJournal()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + KEY_TITLE + ", " +
                                    KEY_DESC + ", " +
                                    KEY_NAME + ", " +
                                    KEY_LAT + ", " +
                                    KEY_LONG + ", " +
                                    KEY_DATE + ", " + " FROM " + TABLE_JOURNAL;

        Cursor data = db.rawQuery(query, null);

        return data;


    }
}