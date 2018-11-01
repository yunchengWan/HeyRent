package com.heyrent.app.renter.utils;

import android.content.Context;

import net.grandcentrix.tray.AppPreferences;
import net.grandcentrix.tray.core.ItemNotFoundException;

public class SharedPreferenceManager {

    private AppPreferences mPreferences;

    public SharedPreferenceManager(Context context) {
        mPreferences = new AppPreferences(context);
    }

    public void putString(String key, String value) {
        mPreferences.put(key, value);
    }

    public String getString(String key) {
        try {
            return mPreferences.getString(key);
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

}
