package com.compmaestros.moviecomrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_settings_activity, new SettingsFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
