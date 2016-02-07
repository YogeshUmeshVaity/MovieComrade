package com.compmaestros.moviecomrade;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;


public class SettingsFragment extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener {

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String stringValue = newValue.toString();

        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list (since they have separate labels/values).
            ListPreference listPreference = (ListPreference) preference;
            int preferenceIndex = listPreference.findIndexOfValue(stringValue);
            if (preferenceIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[preferenceIndex]);
            }
        } else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
        }

        // Don't forget to return true, when you add this method through
        // template it's false by default. If false, the preferences won't be saved.
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from the XML
        addPreferencesFromResource(R.xml.pref_general);

        // For all preferences, attach an OnPreferenceChangeListener so the UI summary can be
        // updated when the preference changes.
        bindPreferenceSummeryToValue(findPreference(getString(R.string.pref_sort_by_key)));
    }

    /**
     * Attaches a listener so the summary is always updated with the preference value.
     * Also fires the listener once, to initialize the summary (so it shows up before the value
     * is changed.)
     */
    private void bindPreferenceSummeryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(this);

        //You can make this one sentence for better performance
        // Retrieve the string value of the preference
        Context preferenceContext = preference.getContext();
        SharedPreferences settings =
                PreferenceManager.getDefaultSharedPreferences(preferenceContext);
        String preferenceKey = preference.getKey();
        String preferenceValue = settings.getString(preferenceKey, "");

        // Trigger the listener immediately with the preference's current value above.
        onPreferenceChange(preference, preferenceValue);
    }

}
