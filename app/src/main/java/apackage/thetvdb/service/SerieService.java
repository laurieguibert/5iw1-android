package apackage.thetvdb.service;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import apackage.thetvdb.entity.Actor;
import apackage.thetvdb.entity.ActorList;
import apackage.thetvdb.entity.Episode;
import apackage.thetvdb.entity.EpisodeList;
import apackage.thetvdb.entity.Season;
import apackage.thetvdb.entity.SeasonList;
import apackage.thetvdb.entity.Serie;
import apackage.thetvdb.entity.SerieDetails;
import apackage.thetvdb.entity.SerieDetailsList;
import apackage.thetvdb.entity.SerieList;
import apackage.thetvdb.entity.ServiceResponse;
import apackage.thetvdb.utils.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gianniazizi on 16/12/2017.
 */


public class SerieService implements ISerieService {

    private IRFSerieService serieService;
    private List<Serie> series = new ArrayList<>();

    public IRFSerieService getSerieService() {
        if(serieService == null) {
            serieService = ApiUtils.getSerieService();
        }

        return serieService;
    }

    @Override
    public void list(Map<String, String> map, String search, final ResponseListener<List<Serie>> responseListener) {

        getSerieService().getSerie(map, search).enqueue(new Callback<SerieList>() {
            @Override
            public void onResponse(Call<SerieList> call, Response<SerieList> response) {
                series.clear();

                if(response.body() != null) {
                    series = response.body().getSeries();
                }
                responseListener.onSuccess(new ServiceResponse(series));
            }

            @Override
            public void onFailure(Call<SerieList> call, Throwable t) {

            }
        });
    }

    @Override
    public void getActors(Map<String, String> map, int id, final ResponseListener<List<Actor>> responseListener) {

        getSerieService().getActors(map, id).enqueue(new Callback<ActorList>() {
            @Override
            public void onResponse(Call<ActorList> call, Response<ActorList> response) {
                List<Actor> actors = response.body().getActors();
                responseListener.onSuccess(new ServiceResponse<>(actors));
            }

            @Override
            public void onFailure(Call<ActorList> call, Throwable t) {

            }
        });
    }

    @Override
    public void get(Map<String, String> map, int id, final ResponseListener<SerieDetails> responseListener) {

        getSerieService().getOne(map, id).enqueue(new Callback<SerieDetailsList>() {
            @Override
            public void onResponse(Call<SerieDetailsList> call, Response<SerieDetailsList> response) {
                SerieDetails serie = response.body().getSerie();
                responseListener.onSuccess(new ServiceResponse<>(serie));
            }

            @Override
            public void onFailure(Call<SerieDetailsList> call, Throwable t) {
            }
        });
    }

    @Override
    public void getSeasons(Map<String, String> token, int id, final ResponseListener<Season> responseListener) {

        getSerieService().getSeasons(token, id).enqueue(new Callback<SeasonList>() {
            @Override
            public void onResponse(Call<SeasonList> call, Response<SeasonList> response) {
                Season seasons = response.body().getSeason();
                responseListener.onSuccess(new ServiceResponse<>(seasons));
            }

            @Override
            public void onFailure(Call<SeasonList> call, Throwable t) {

            }
        });
    }

    @Override
    public void getEpisodes(Map<String, String> token, int id, final ResponseListener<List<Episode>> responseListener) {
        getSerieService().getEpisodes(token, id).enqueue(new Callback<EpisodeList>() {
            @Override
            public void onResponse(Call<EpisodeList> call, Response<EpisodeList> response) {
                List<Episode> episodes = response.body().getEpisodeList();
                responseListener.onSuccess(new ServiceResponse<>(episodes));
            }

            @Override
            public void onFailure(Call<EpisodeList> call, Throwable t) {

            }
        });
    }
}
