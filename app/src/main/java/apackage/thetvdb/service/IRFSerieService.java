package apackage.thetvdb.service;

import java.util.Map;

import apackage.thetvdb.entity.ActorList;
import apackage.thetvdb.entity.SerieList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by gianniazizi on 16/12/2017.
 */

public interface IRFSerieService {

    @GET("/search/series")
    Call<SerieList> getSerie(@HeaderMap Map<String, String> map, @Query("name") String name);

    @GET("/series/{id}/actors")
    Call<ActorList> getActors(@HeaderMap Map<String, String> map, @Path("id") int id);

}


