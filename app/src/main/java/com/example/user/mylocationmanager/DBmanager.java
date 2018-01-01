package com.example.user.mylocationmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by user on 31/12/2017.
 */

public class DBmanager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "movies.db";

    private final String TABLE_NAME = "locationManager";
    private final String ID = "_id"; //col0
    private final String LOCATION = "location"; //col1
    private final String LAT = "latitude"; // col2
    private final String LON = "longitude"; // col3

    //Constructor to create database
    public DBmanager(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    //create table
    @Override
    public void onCreate(SQLiteDatabase db) {

        String queryTable = "CREATE TABLE " + TABLE_NAME +
                " ( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LOCATION + " TEXT NOT NULL, " +
                LAT + " TEXT NOT NULL, " +
                LON + " TEXT NOT NULL " +
                " ) ";
        db.execSQL(queryTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // insert new movie to DB
    public boolean insertNewLocation(String locationName, String latitude, String longitude) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(LOCATION, locationName);
        contentValues.put(LAT, latitude);
        contentValues.put(LON, longitude);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {

            return false;
        }
        Log.i("SQL", "data insert");
        return true;
    }

    // get Cursor to present DB and get out the info for the App
    public Cursor getCursor() {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    // delete all the info from DB
    public boolean deleteAll() {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME;
        db.execSQL(query);

        Log.i("SQL", "all data was deleted");
        return true;
    }

    // delete item from DB
    public void deleteItem(String name) {

        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereArgs = new String[]{name};
        db.delete(TABLE_NAME, LOCATION + " = ? ", whereArgs);
        db.close();
    }

    // get data id from DB --> by param name --> return long Id
    public long getId(String name) {

        String query = "SELECT " + ID + " FROM " + TABLE_NAME +
                " WHERE " + LOCATION + " = ?;";

        SQLiteDatabase db = this.getReadableDatabase();
        return DatabaseUtils.longForQuery(db, query, new String[]{name});

    }

    // get the row data of the movie by id
    public Cursor getRowDataById(long id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " = " + id;

        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public void updateById(long id, Loc m) {

        SQLiteDatabase db = this.getWritableDatabase();

        Loc location = m;
        String name = location.getPlace();
        String lat = String.valueOf(location.getLat());
        String lon = String.valueOf(location.getLon());

        ContentValues contentV = new ContentValues();
        contentV.put(LOCATION, name); //These Fields should be your String values of actual column names
        contentV.put(LAT, lat);
        contentV.put(LON, lon);

        db.update(TABLE_NAME, contentV, ID + " = " + id, null);
    }
}
