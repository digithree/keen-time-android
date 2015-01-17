package com.surfacetension.keentime;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by simonkenny on 17/01/15.
 */
public class DatabaseAccess extends SQLiteOpenHelper {
    // --- values for events table
    public static final String TABLE_EVENTS = "events";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CUSTOMER = "customer";
    public static final String COLUMN_PROJECT = "project";
    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_START = "start";
    public static final String COLUMN_END = "end";
    public static final String COLUMN_USER = "user";
    public static final String COLUMN_COLLECTION = "collection";

    public static final String TABLE_COLLECTIONS = "collections";
    public static final String COLUMN_NAME = "name";

    private static final String DATABASE_NAME = "keentime.db";

    // Database creation sql statement
    private static final String DATABASE_CREATE_EVENTS_TABLE = "create table "
            + TABLE_EVENTS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_CUSTOMER + " char(512),"
            + COLUMN_PROJECT + " char(512),"
            + COLUMN_TASK + " char(512),"
            + COLUMN_START + " char(512),"
            + COLUMN_END + " char(512),"
            + COLUMN_USER + " char(512),"
            + COLUMN_COLLECTION + " char(512)"
            + ");";

    private static final String DATABASE_CREATE_COLLECTIONS_TABLE = "create table "
            + TABLE_COLLECTIONS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " char(512)"
            + ");";

    public DatabaseAccess(Context context) {
        super(context, DATABASE_NAME, null, Utils.getAppVersionCode(context));
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.d(DatabaseAccess.class.getName(), "Creating database");
        database.execSQL(DATABASE_CREATE_EVENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(DatabaseAccess.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        // TODO : migrate, don't just drop the data
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }
}
