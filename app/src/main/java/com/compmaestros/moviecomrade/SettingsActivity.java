package com.compmaestros.moviecomrade;

import android.preference.Preference;
import android.preference.PreferenceActivity;


public class SettingsActivity extends PreferenceActivity
        implements Preference.OnPreferenceChangeListener {

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }
}
