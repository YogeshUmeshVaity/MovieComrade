package com.compmaestros.moviecomrade;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private static final String LOG_TAG = DetailsFragment.class.getSimpleName();


    private MovieInfo movieInfo;

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments().containsKey(MainFragment.MOVIE_PARCEL)) {
            // TODO: 12/2/16 See if you can use Loader here.
            movieInfo = getArguments().getParcelable(MainFragment.MOVIE_PARCEL);
            Log.d(LOG_TAG, movieInfo.toString());
        }

        Activity activity = this.getActivity();
        CollapsingToolbarLayout appBarLayout =
                (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if(appBarLayout != null) {
            appBarLayout.setTitle(movieInfo.getOriginalTitle());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_details, container, false);
        TextView movieOverview = (TextView) rootView.findViewById(R.id.movie_overview_text_view);
        movieOverview.setText(movieInfo.getOverview());
        return rootView;
    }

}
