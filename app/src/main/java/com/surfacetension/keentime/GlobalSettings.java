package com.surfacetension.keentime;

import android.content.SharedPreferences;

/**
 * Created by simonkenny on 14/01/15.
 */
public class GlobalSettings {
    private final static GlobalSettings INSTANCE = new GlobalSettings();

    private static final String SHARED_PREFS_EMPTY_STRING = "[[empty]]";

    private static final String SHARED_PREFS = "KEEN_TIME_SHARED_PREFS";
    private static final String SHARED_PREFS_CUR_EVENT_COLLECTION = "CUR_EVENT_COLLECTION";

    SharedPreferences sharedPreferences;

    // thwart instantiation
    protected GlobalSettings() {
        sharedPreferences = null;
    }

    public static GlobalSettings getInstance() {
        return INSTANCE;
    }

    public void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    // --- access

    public void setCurEventCollection(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SHARED_PREFS_CUR_EVENT_COLLECTION, name);
        editor.commit();
    }

    public String getCurEventCollection() {
        String str = sharedPreferences.getString(SHARED_PREFS_CUR_EVENT_COLLECTION, SHARED_PREFS_EMPTY_STRING);
        if( str.equals(SHARED_PREFS_EMPTY_STRING) ) {
            return null;
        }
        return str;
    }
}
