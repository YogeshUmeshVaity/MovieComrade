package com.compmaestros.moviecomrade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/** Activity for displaying the details a movie selected by the user */
public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add((R.id.fragment_container_details_activity), new DetailsFragment()).commit();
        }
    }
}
