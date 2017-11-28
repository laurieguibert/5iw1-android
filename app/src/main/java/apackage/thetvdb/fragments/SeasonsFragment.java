package apackage.thetvdb.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.sburba.tvdbapi.TvdbApi;
import com.sburba.tvdbapi.TvdbItemAdapter;
import com.sburba.tvdbapi.model.Actor;
import com.sburba.tvdbapi.model.Season;
import com.sburba.tvdbapi.model.Series;

import java.util.Collection;

import apackage.thetvdb.App;
import apackage.thetvdb.R;


public class SeasonsFragment extends Fragment {
    public static final String EXTRA_SERIES = "series";
    private TvdbItemAdapter<Season> mSeasonAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_actors,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        App app = App.getInstance(getActivity());

        mSeasonAdapter = new TvdbItemAdapter<>(getActivity(), app.getImageLoader(),
                R.layout.tvdb_item, R.id.title,
                R.id.image);

        GridView gridView = (GridView) view.findViewById(R.id.grid_view);
        gridView.setAdapter(mSeasonAdapter);


        Intent intent = getActivity().getIntent();
        Series series = intent.getParcelableExtra(EXTRA_SERIES);

        if (series != null) {
            TvdbApi tvdbApi = new TvdbApi(App.TVDB_API_KEY, "en", app.getRequestQueue());
            tvdbApi.getSeasons(series, mSeasonResponseListener, mErrorListener);
        }
    }

    private Response.Listener<Collection<Season>> mSeasonResponseListener =
            new Response.Listener<Collection<Season>>() {
                @Override
                public void onResponse(Collection<Season> seasons) {
                    mSeasonAdapter.addAll(seasons);
                }
            };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(getActivity(), "Oh noes! Something has gone awry.",
                    Toast.LENGTH_SHORT).show();
        }
    };

}
