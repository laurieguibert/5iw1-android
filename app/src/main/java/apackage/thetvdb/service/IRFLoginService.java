package apackage.thetvdb.service;

import java.util.Map;

import apackage.thetvdb.entity.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by gianniazizi on 23/12/2017.
 */

public interface IRFLoginService {
    @POST("/login")
    Call<Token> login(@Body Map<String, String> body);
}
