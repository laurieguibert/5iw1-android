package apackage.thetvdb.service;

import android.util.Log;

import java.util.List;
import java.util.Map;

import apackage.thetvdb.entity.Rating;
import apackage.thetvdb.entity.RatingList;
import apackage.thetvdb.entity.ServiceResponse;
import apackage.thetvdb.utils.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserService implements IUserService {

    private IRFUserService userService;

    private IRFUserService getUserService() {
        if(userService == null) {
            userService = ApiUtils.getUserService();
        }

        return userService;
    }

    @Override
    public void evaluate(Map<String, String> token, String itemType, int itemId, int itemRating) {
        getUserService().evaluate(token, itemType, itemId, itemRating).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    @Override
    public void getRatings(Map<String, String> token, final ResponseListener<List<Rating>> responseListener) {
        getUserService().getRatings(token).enqueue(new Callback<RatingList>() {
            @Override
            public void onResponse(Call<RatingList> call, Response<RatingList> response) {
                Log.e("DEV", "TEST : " + response.body());
                List<Rating> ratingList = response.body().getRatingList();
                responseListener.onSuccess(new ServiceResponse<>(ratingList));
            }

            @Override
            public void onFailure(Call<RatingList> call, Throwable t) {

            }
        });
    }
}
