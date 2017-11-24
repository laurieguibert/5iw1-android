package apackage.thetvdb.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.sburba.tvdbapi.TvdbApi;
import com.sburba.tvdbapi.TvdbItemAdapter;
import com.sburba.tvdbapi.model.Series;

import java.util.Collection;

import apackage.thetvdb.App;
import apackage.thetvdb.R;


public class SearchFragment extends ListFragment {

    private TvdbItemAdapter<Series> mSeriesAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText edit_txt = (EditText) view.findViewById(R.id.search_bar);

        edit_txt.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String search = edit_txt.getText().toString();


                    App app = App.getInstance(getActivity());
                    ImageLoader imageLoader = app.getImageLoader();
                    mSeriesAdapter = new TvdbItemAdapter<>(getActivity(), imageLoader, R.layout.tvdb_item, R.id.title, R.id.image);
                    setListAdapter(mSeriesAdapter);

                    // TODO ask language

                    TvdbApi tvdbApi = new TvdbApi(App.TVDB_API_KEY, "en", app.getRequestQueue());
                    tvdbApi.searchSeries(search, mSeriesResponseListener, mErrorListener);


                }
                return false;
            }
        });
    }

    private Response.Listener<Collection<Series>> mSeriesResponseListener = new Response.Listener<Collection<Series>>() {
        @Override
        public void onResponse(Collection<Series> series) {
            mSeriesAdapter.addAll(series);
        }
    };

    private Response.ErrorListener mErrorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(getActivity(), "Oh noes! Something has gone awry.", Toast.LENGTH_SHORT).show();
        }

    };
}
