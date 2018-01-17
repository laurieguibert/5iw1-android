package apackage.thetvdb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import apackage.thetvdb.adapter.EpisodeListAdapter;
import apackage.thetvdb.adapter.IRecyclerViewClickListener;
import apackage.thetvdb.adapter.SeasonListAdapter;
import apackage.thetvdb.entity.Episode;
import apackage.thetvdb.entity.Serie;
import apackage.thetvdb.entity.ServiceResponse;
import apackage.thetvdb.service.ISerieService;
import apackage.thetvdb.service.ResponseListener;
import apackage.thetvdb.service.SerieService;
import apackage.thetvdb.utils.ApiUtils;

public class EpisodeActivity extends AppCompatActivity {

    private final static String SERIE_KEY = "serie";
    private Serie serie;
    private String seasonNumber;
    private TextView seriesName;
    private TextView seasonNumberTextView;
    private ISerieService serieService;
    private Map<String, String> token = null;
    private EpisodeListAdapter episodeListAdapter;
    private List<Episode> episodesList = new ArrayList<>();
    private RecyclerView recyclerView;



    private ISerieService getSerieService() {
        if(serieService == null) {
            serieService = new SerieService();
        }

        return serieService;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_episode);

        Intent intent = getIntent();
        serie = intent.getParcelableExtra(SERIE_KEY);
        seasonNumber = intent.getStringExtra("seasonNumber");

        seriesName = (TextView) findViewById(R.id.series_name);
        seasonNumberTextView = (TextView) findViewById(R.id.season_number);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        seriesName.setText(serie.getSeriesName());
        seasonNumberTextView.setText(seasonNumber);


        ApiUtils.getConnection(new ResponseListener<Map<String, String>>() {
            @Override
            public void onSuccess(ServiceResponse<Map<String, String>> serviceResponse) {
                token = serviceResponse.getData();

                getSerieService().getEpisodes(token, serie.getId(), new ResponseListener<List<Episode>>() {
                    @Override
                    public void onSuccess(ServiceResponse<List<Episode>> serviceResponse) {
                        List<Episode> episodes = serviceResponse.getData();

                        episodeListAdapter = new EpisodeListAdapter(episodesList, EpisodeActivity.this, new IRecyclerViewClickListener() {
                            @Override
                            public void onClickListener(int position) {
                                showPopup(position);
                            }
                        });

                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(EpisodeActivity.this);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(episodeListAdapter);


                        for(Episode episode : episodes) {
                            if(episode.getAiredSeason() == Integer.parseInt(seasonNumber)) {
                                episodesList.add(episode);
                                episodeListAdapter.notifyDataSetChanged();
                            }

                        }
                    }
                });
            }
        });

    }

    private PopupWindow pw;
    Button Close;
    Button Create;
    TextView episodeNumber;
    TextView episodeName;
    TextView episodeDescription;
    private void showPopup(int position) {
        try {

            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            int height = size.y;

            // We need to get the instance of the LayoutInflater
            LayoutInflater inflater = LayoutInflater.from(EpisodeActivity.this);
            getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View layout = inflater.inflate(R.layout.popup,
                    (ViewGroup) findViewById(R.id.rl_custom_layout));

            Episode episode = episodesList.get(position);

            pw = new PopupWindow(layout, width, height, true);

            episodeNumber = (TextView) pw.getContentView().findViewById(R.id.episode_number);
            episodeName = (TextView) pw.getContentView().findViewById(R.id.episode_name);
            episodeDescription = (TextView) pw.getContentView().findViewById(R.id.episode_description);

            if(episode.getAbsoluteNumber() != null) {
                episodeNumber.setText(episode.getAbsoluteNumber().toString());
            }
            episodeName.setText(episode.getEpisodeName());
            if(episode.getOverview() != "") {
                episodeDescription.setText(episode.getOverview());
            }else{
                episodeDescription.setText("Overview unavailable");
            }


            pw.showAtLocation(layout, Gravity.CENTER, 0, 0);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View.OnClickListener cancel_button = new View.OnClickListener() {
        public void onClick(View v) {
            pw.dismiss();
        }
    };
}

