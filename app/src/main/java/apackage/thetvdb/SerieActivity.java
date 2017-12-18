package apackage.thetvdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import apackage.thetvdb.adapter.ActorListAdapter;
import apackage.thetvdb.adapter.IRecyclerViewClickListener;
import apackage.thetvdb.adapter.SerieListAdapter;
import apackage.thetvdb.entity.Actor;
import apackage.thetvdb.entity.Serie;
import apackage.thetvdb.entity.ServiceResponse;
import apackage.thetvdb.service.ISerieService;
import apackage.thetvdb.service.ResponseListener;
import apackage.thetvdb.service.SerieService;
import apackage.thetvdb.utils.ApiUtils;

public class SerieActivity extends AppCompatActivity {

    private final static String SERIE_KEY = "serie";

    private ISerieService serieService;

    private TextView seriesName;
    private TextView overview;
    private TextView date;
    private ImageView banner;
    private RecyclerView recyclerView;
    private ActorListAdapter actorListAdapter;
    private List<Actor> actorList = new ArrayList<>();

    private ISerieService getSerieService() {
        if(serieService == null) {
            serieService = new SerieService();
        }

        return serieService;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie);

        Intent intent = getIntent();
        Serie serie = intent.getParcelableExtra(SERIE_KEY);

        seriesName = (TextView) findViewById(R.id.series_name);
        overview = (TextView) findViewById(R.id.overview);
        banner = (ImageView) findViewById(R.id.banner);
        date = (TextView) findViewById(R.id.date);

        seriesName.setText(serie.getSeriesName());
        overview.setText(serie.getOverview());
        date.setText(serie.getFirstAired());


        if(serie.getBanner().length() == 0) {
            banner.setImageResource(R.drawable.default_image);
        }else{
            Picasso.with(this).load("https://www.thetvdb.com/banners/_cache/" + serie.getBanner()).into(banner);
        }

        getSerieService().getActors(ApiUtils.getHeaders(), serie.getId(), new ResponseListener<List<Actor>>() {
            @Override
            public void onSuccess(ServiceResponse<List<Actor>> serviceResponse) {
                List<Actor> actors = serviceResponse.getData();
                for(Actor actor : actors) {
                    actorList.add(actor);
                }
                actorListAdapter.notifyDataSetChanged();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        actorListAdapter = new ActorListAdapter(actorList, this, new IRecyclerViewClickListener() {
            @Override
            public void onClickListener(int position) {
                // TODO implement on click
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(actorListAdapter);

    }
}
