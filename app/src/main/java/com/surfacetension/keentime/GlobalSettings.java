package com.surfacetension.keentime;

import android.content.SharedPreferences;

/**
 * Created by simonkenny on 14/01/15.
 */
public class GlobalSettings {
    private final static GlobalSettings INSTANCE = new GlobalSettings();

    private static final String SHARED_PREFS_EMPTY_STRING = "[[empty]]";

    //private static final String SHARED_PREFS = "KEEN_TIME_SHARED_PREFS";
    public static final String SHARED_PREFS_CUR_EVENT_COLLECTION = "CUR_EVENT_COLLECTION";
    public static final String SHARED_PREFS_USER = "USER";
    public static final String SHARED_PREFS_MASTER_API = "MASTER_API";
    public static final String SHARED_PREFS_PROJECT = "PROJECT";
    public static final String SHARED_PREFS_READ = "READ";
    public static final String SHARED_PREFS_WRITE = "WRITE";

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

    public void setStringKey(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public String getStringKey(String key) {
        String str = sharedPreferences.getString(key, SHARED_PREFS_EMPTY_STRING);
        if( str.equals(SHARED_PREFS_EMPTY_STRING) ) {
            return null;
        }
        return str;
    }
}
