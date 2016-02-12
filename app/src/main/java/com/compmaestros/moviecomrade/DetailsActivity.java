package com.compmaestros.moviecomrade;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/** Activity for displaying the details a movie selected by the user */
public class DetailsActivity extends AppCompatActivity {

    private static final String LOG_TAG = "DetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if(savedInstanceState == null) {
            Bundle movieData = new Bundle();
            movieData.putParcelable(MainFragment.MOVIE_PARCEL,
                    getIntent().getParcelableExtra(MainFragment.MOVIE_PARCEL));
            DetailsFragment detailsFragment = new DetailsFragment();
            detailsFragment.setArguments(movieData);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.movie_detail_container, detailsFragment).commit();
        }
    }
}
