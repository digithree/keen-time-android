package com.surfacetension.keentime;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by simonkenny on 17/01/15.
 */
public class EventsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private DatabaseAccess dbHelper;
    private String[] allColumns = {
            DatabaseAccess.COLUMN_ID,
            DatabaseAccess.COLUMN_CUSTOMER,
            DatabaseAccess.COLUMN_PROJECT,
            DatabaseAccess.COLUMN_TASK,
            DatabaseAccess.COLUMN_START,
            DatabaseAccess.COLUMN_END,
            DatabaseAccess.COLUMN_USER,
            DatabaseAccess.COLUMN_COLLECTION
    };

    public EventsDataSource(Context context) {
        dbHelper = new DatabaseAccess(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // access methods
    public void updateRow(EventData mEventData) {
        ContentValues values = new ContentValues();
        values.put(DatabaseAccess.COLUMN_ID, mEventData.getId());
        values.put(DatabaseAccess.COLUMN_CUSTOMER, mEventData.getCustomer());
        values.put(DatabaseAccess.COLUMN_PROJECT, mEventData.getProject());
        values.put(DatabaseAccess.COLUMN_TASK, mEventData.getTask());
        values.put(DatabaseAccess.COLUMN_START, mEventData.getStart().toString());
        values.put(DatabaseAccess.COLUMN_END, mEventData.getEnd().toString());
        values.put(DatabaseAccess.COLUMN_USER, mEventData.getUser());
        values.put(DatabaseAccess.COLUMN_COLLECTION, mEventData.getCollection());

        database.update(DatabaseAccess.TABLE_EVENTS,
                values, "_id="+mEventData.getId(), null);
    }

    public EventData createRow(EventData mEventData) {
        ContentValues values = new ContentValues();
        values.put(DatabaseAccess.COLUMN_CUSTOMER, mEventData.getCustomer());
        values.put(DatabaseAccess.COLUMN_PROJECT, mEventData.getProject());
        values.put(DatabaseAccess.COLUMN_TASK, mEventData.getTask());
        values.put(DatabaseAccess.COLUMN_START, mEventData.getStart().toString());
        values.put(DatabaseAccess.COLUMN_END, mEventData.getEnd().toString());
        values.put(DatabaseAccess.COLUMN_USER, mEventData.getUser());
        values.put(DatabaseAccess.COLUMN_COLLECTION, mEventData.getCollection());

        long insertId = database.insert(DatabaseAccess.TABLE_EVENTS, null, values);
        Cursor cursor = database.query(DatabaseAccess.TABLE_EVENTS,
                allColumns, DatabaseAccess.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        EventData retEventData = cursorToEventData(cursor);
        cursor.close();
        return retEventData;
    }

    public void deleteRow(EventData mEventData) {
        long id = mEventData.getId();
        System.out.println("Row deleted with id: " + id);
        database.delete(DatabaseAccess.TABLE_EVENTS, DatabaseAccess.COLUMN_ID
                + " = " + id, null);
    }

    public List<EventData> getAllRows() {
        List<EventData> allRowsList = new ArrayList<EventData>();

        Cursor cursor = database.query(DatabaseAccess.TABLE_EVENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            EventData mEventData = cursorToEventData(cursor);
            allRowsList.add(mEventData);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return allRowsList;
    }

    public EventData getRow(int id) {
        Cursor cursor = database.query(DatabaseAccess.TABLE_EVENTS,
                allColumns, DatabaseAccess.COLUMN_ID+"=?",
                new String[]{""+id}, null, null, null);
        if( cursor != null ) {
            if( !cursor.moveToFirst() ) {
                cursor.close();
                return new EventData();
            }
        }
        EventData mEventData = cursorToEventData(cursor);
        cursor.close();
        return mEventData;
    }

    private EventData cursorToEventData(Cursor cursor) {
        EventData mEventData = new EventData();
        int idx = 0;
        mEventData.setId(cursor.getLong(idx++));
        mEventData.setCustomer(cursor.getString(idx++));
        mEventData.setProject(cursor.getString(idx++));
        mEventData.setTask(cursor.getString(idx++));
        SimpleDateFormat sdf = new SimpleDateFormat();
        try {
            mEventData.setStart(sdf.parse(cursor.getString(idx++)));
            mEventData.setEnd(sdf.parse(cursor.getString(idx++)));
        } catch (ParseException e) {
            Log.d("EventsDataSource","cursorToEventData: string to date parse exception");
            mEventData.setStart(new Date());
            mEventData.setEnd(new Date());
        }
        mEventData.setUser(cursor.getString(idx++));
        mEventData.setCollection(cursor.getString(idx++));
        return mEventData;
    }
}
