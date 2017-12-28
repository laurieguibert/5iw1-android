package apackage.thetvdb.service;

import java.util.Map;

import apackage.thetvdb.entity.RatingList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface IRFUserService {
    @PUT("/user/ratings/{itemType}/{itemId}/{itemRating}")
    Call<Void> evaluate(@HeaderMap Map<String, String> map, @Path("itemType") String itemType, @Path("itemId") int itemId, @Path("itemRating") int itemRating);

    @GET("/user/ratings")
    Call<RatingList> getRatings(@HeaderMap Map<String, String> map);
}
