package apackage.thetvdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import apackage.thetvdb.adapter.ActorListAdapter;
import apackage.thetvdb.adapter.IRecyclerViewClickListener;
import apackage.thetvdb.adapter.SerieListAdapter;
import apackage.thetvdb.entity.Actor;
import apackage.thetvdb.entity.Rating;
import apackage.thetvdb.entity.RatingList;
import apackage.thetvdb.entity.Serie;
import apackage.thetvdb.entity.SerieDetails;
import apackage.thetvdb.entity.ServiceResponse;
import apackage.thetvdb.service.ISerieService;
import apackage.thetvdb.service.IUserService;
import apackage.thetvdb.service.ResponseListener;
import apackage.thetvdb.service.SerieService;
import apackage.thetvdb.service.UserService;
import apackage.thetvdb.utils.ApiUtils;

public class SerieActivity extends AppCompatActivity {

    private final static String SERIE_KEY = "serie";

    private ISerieService serieService;
    private IUserService userService;

    private TextView seriesName;
    private TextView overview;
    private TextView date;
    private TextView genre;
    private ImageView banner;
    private TextView rating;
    private TextView countRating;
    private RecyclerView recyclerView;
    private ActorListAdapter actorListAdapter;
    private List<Actor> actorList = new ArrayList<>();
    private SerieDetails serie;
    private Map<String, String> token = null;
    private Serie serieRequested = null;
    private RatingBar ratingBar;

    private ISerieService getSerieService() {
        if(serieService == null) {
            serieService = new SerieService();
        }

        return serieService;
    }

    private IUserService getUserService() {
        if(userService == null) {
            userService = new UserService();
        }

        return userService;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie);

        Intent intent = getIntent();
        serieRequested = intent.getParcelableExtra(SERIE_KEY);

        seriesName = (TextView) findViewById(R.id.series_name);
        overview = (TextView) findViewById(R.id.overview);
        banner = (ImageView) findViewById(R.id.banner);
        date = (TextView) findViewById(R.id.date);
        genre = (TextView) findViewById(R.id.genre);
        rating = (TextView) findViewById(R.id.rating);
        countRating = (TextView) findViewById(R.id.count_rating);
        ratingBar = (RatingBar) findViewById(R.id.rating_bar);

        loadSerie(serieRequested);


        ApiUtils.getConnection(new ResponseListener<Map<String, String>>() {
            @Override
            public void onSuccess(ServiceResponse<Map<String, String>> serviceResponse) {
                token = serviceResponse.getData();

                getSerieService().get(token, serieRequested.getId(), new ResponseListener<SerieDetails>() {
                    @Override
                    public void onSuccess(ServiceResponse<SerieDetails> serviceResponse) {
                        serie = serviceResponse.getData();
                        loadMoreSerie(serie);
                    }
                });

                getSerieService().getActors(token, serieRequested.getId(), new ResponseListener<List<Actor>>() {
                    @Override
                    public void onSuccess(ServiceResponse<List<Actor>> serviceResponse) {
                        List<Actor> actors = serviceResponse.getData();
                        if(actors != null) {
                            for(Actor actor : actors) {
                                actorList.add(actor);
                            }
                            actorListAdapter.notifyDataSetChanged();
                        }
                    }
                });

                // TODO IF ACCOUNT IS SET
                getUserService().getRatings(token, new ResponseListener<List<Rating>>() {
                    @Override
                    public void onSuccess(ServiceResponse<List<Rating>> serviceResponse) {
                        List<Rating> ratings = serviceResponse.getData();
                        for(Rating rating: ratings) {
                            if(rating.getRatingItemId().equals(serieRequested.getId())) {
                                if(rating.getRating() != null) {
                                    Log.e("DEV", "TEST : " + rating.getRating());
                                    ratingBar.setRating(rating.getRating() / 2);
                                }
                            }
                        }

                        ratingBar.setVisibility(View.VISIBLE);
                    }
                });

                // TODO IF ACCOUNT IS SET
                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    int rating = (int) ratingBar.getRating() * 2;
                    getUserService().evaluate(token ,"series", serieRequested.getId(), rating);
                    }
                });
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

    private void loadSerie(Serie serie) {
        seriesName.setText(serie.getSeriesName());
        overview.setText(serie.getOverview());
        date.setText(serie.getFirstAired());

        if(serie.getBanner().length() == 0) {
            banner.setImageResource(R.drawable.default_image);
        }else{
            Picasso.with(this).load("https://www.thetvdb.com/banners/_cache/" + serie.getBanner()).into(banner);
        }
    }

    private void loadMoreSerie(SerieDetails serie) {
        StringBuilder genreString = new StringBuilder();
        int count = 0;
        if(serie.getGenre() != null) {
            for(String genre : serie.getGenre()) {
                genreString.append(genre);
                if(count > 1) { break; }
                genreString.append(" / ");
                count++;
            }
            genre.setText(genreString);
        }
        rating.setText(serie.getSiteRating() + "/10");
        countRating.setText(serie.getSiteRatingCount() + " votes");

        //TODO Add rating and favorite if the user is connected
    }

}
