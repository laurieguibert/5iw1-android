package apackage.thetvdb.service;

import android.util.Log;

import java.util.Map;

import apackage.thetvdb.entity.ServiceResponse;
import apackage.thetvdb.entity.Token;
import apackage.thetvdb.utils.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gianniazizi on 23/12/2017.
 */

// TODO gérer le onFailure -> No internet connection

public class LoginService implements ILoginService {

    private IRFLoginService loginService;

    public IRFLoginService getLoginService() {
        if(loginService == null) {
            loginService = ApiUtils.getLoginService();
        }

        return loginService;
    }

    @Override
    public void login(Map<String, String> body, final ResponseListener<Token> responseListener) {
        getLoginService().login(body).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Token token = response.body();
                responseListener.onSuccess(new ServiceResponse<>(token));
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });
    }
}
