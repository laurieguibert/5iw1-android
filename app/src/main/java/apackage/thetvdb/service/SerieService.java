package apackage.thetvdb.service;

import java.util.List;
import java.util.Map;

import apackage.thetvdb.entity.Actor;
import apackage.thetvdb.entity.ActorList;
import apackage.thetvdb.entity.Serie;
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

    public IRFSerieService getSerieService() {
        if(serieService == null) {
            serieService = ApiUtils.getSerieService();
        }

        return serieService;
    }

    @Override
    public void list(Map<String, String> map, String search, final ResponseListener<List<Serie>> responseListener) {
        // TODO ici choisir entre API ou local, si API ne pas oublier d'enregistrer en local

        getSerieService().getSerie(map, search).enqueue(new Callback<SerieList>() {
            @Override
            public void onResponse(Call<SerieList> call, Response<SerieList> response) {
                List<Serie> series = response.body().getSeries();
                responseListener.onSuccess(new ServiceResponse(series));
            }

            @Override
            public void onFailure(Call<SerieList> call, Throwable t) {

            }
        });
    }

    @Override
    public void getActors(Map<String, String> map, int id, final ResponseListener<List<Actor>> responseListener) {
        // TODO ici choisir entre API ou local, si API ne pas oublier d'enregistrer en local

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
}
