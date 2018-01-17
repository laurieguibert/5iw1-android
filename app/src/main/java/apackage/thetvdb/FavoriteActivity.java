package apackage.thetvdb;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import apackage.thetvdb.adapter.IRecyclerViewClickListener;
import apackage.thetvdb.adapter.SerieListAdapter;
import apackage.thetvdb.entity.Favorite;
import apackage.thetvdb.entity.Serie;
import apackage.thetvdb.entity.SerieDetails;
import apackage.thetvdb.entity.ServiceResponse;
import apackage.thetvdb.service.ISerieService;
import apackage.thetvdb.service.IUserService;
import apackage.thetvdb.service.ResponseListener;
import apackage.thetvdb.service.SerieService;
import apackage.thetvdb.service.UserService;
import apackage.thetvdb.utils.ApiUtils;

public class FavoriteActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SerieListAdapter serieListAdapter;
    private List<Serie> serieList = new ArrayList<>();
    private final static String SERIE_KEY = "serie";
    private Map<String, String> token;
    private ISerieService serieService;
    private IUserService userService;
    private Button cancel;
    private LinearLayout noResult;

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
        setContentView(R.layout.activity_favorite);

        noResult = (LinearLayout) findViewById(R.id.no_result);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        serieListAdapter = new SerieListAdapter(serieList, this, new IRecyclerViewClickListener() {
            @Override
            public void onClickListener(int position) {
                Intent intent = new Intent(FavoriteActivity.this, SerieActivity.class);
                intent.putExtra(SERIE_KEY, serieList.get(position));
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(serieListAdapter);


        /* Change drawables color */

        int tintColor = ContextCompat.getColor(this, R.color.orange);

        Drawable drawable1 = ContextCompat.getDrawable(this, R.drawable.ic_sentiment_dissatisfied_black_24dp);
        DrawableCompat.setTint(drawable1.mutate(), tintColor);
        ImageView imageView = (ImageView) findViewById(R.id.sad);
        imageView.setBackground(drawable1);


        ApiUtils.getConnection(new ResponseListener<Map<String, String>>() {
            @Override
            public void onSuccess(ServiceResponse<Map<String, String>> serviceResponse) {
                token = serviceResponse.getData();

                getUserService().getFavorites(token, new ResponseListener<List<String>>() {
                    @Override
                    public void onSuccess(ServiceResponse<List<String>> serviceResponse) {
                        serieList.clear();
                        if(serviceResponse.getData().get(0) == "") {
                            noResult.setVisibility(View.VISIBLE);
                        }else{
                            noResult.setVisibility(View.INVISIBLE);
                            for(String id: serviceResponse.getData()) {
                                getSerieService().get(token, Integer.parseInt(id), new ResponseListener<SerieDetails>() {
                                    @Override
                                    public void onSuccess(ServiceResponse<SerieDetails> serviceResponse) {
                                        SerieDetails serieDetails = serviceResponse.getData();
                                        Serie serie = new Serie(serieDetails.getId(), serieDetails.getOverview(), serieDetails.getStatus(), serieDetails.getSeriesName(), serieDetails.getBanner(), serieDetails.getFirstAired());
                                        serieList.add(serie);
                                        serieListAdapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(FavoriteActivity.this, FavoriteActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FavoriteActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
