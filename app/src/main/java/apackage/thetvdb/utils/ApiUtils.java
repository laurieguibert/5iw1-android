package apackage.thetvdb.utils;

import java.util.HashMap;
import java.util.Map;

import apackage.thetvdb.entity.Account;
import apackage.thetvdb.entity.ServiceResponse;
import apackage.thetvdb.entity.Token;
import apackage.thetvdb.remote.RetrofitClient;
import apackage.thetvdb.service.ILoginService;
import apackage.thetvdb.service.IRFLoginService;
import apackage.thetvdb.service.IRFSerieService;
import apackage.thetvdb.service.LoginService;
import apackage.thetvdb.service.ResponseListener;
import apackage.thetvdb.storage.AccountService;
import apackage.thetvdb.storage.IAccountService;
import apackage.thetvdb.storage.ITokenService;
import apackage.thetvdb.storage.TokenService;

/**
 * Created by gianniazizi on 16/12/2017.
 */

public class ApiUtils {
    private static final String BASE_URL = "https://api.thetvdb.com";
    private static Map<String, String> headers = new HashMap<>();
    private static Map<String, String> body = new HashMap<>();
    public static String API_KEY = "97DCD0C43454616A";
    private static ITokenService storageTokenService;
    private static IAccountService storageAccountService;
    private static ILoginService loginService;

    private static IAccountService getStorageAccountService() {
        if(storageAccountService == null) {
            storageAccountService = new AccountService();
        }

        return storageAccountService;
    }

    private static ITokenService getStorageTokenService() {
        if(storageTokenService == null) {
            storageTokenService = new TokenService();
        }

        return storageTokenService;
    }

    private static ILoginService getILoginService() {
        if(loginService == null) {
            loginService = new LoginService();
        }

        return loginService;
    }

    public static IRFSerieService getSerieService() {
        return RetrofitClient.getClient(BASE_URL).create(IRFSerieService.class);
    }

    public static IRFLoginService getLoginService() {
        return RetrofitClient.getClient(BASE_URL).create(IRFLoginService.class);
    }

    public static void getConnection(final ResponseListener<Map<String, String>> responseListener) {
        Token token = getStorageTokenService().getToken();
        body.put("apikey", API_KEY);
        if(token == null) {
            Account account = getStorageAccountService().getAccount();
            if(account == null) {
                getILoginService().login(body , new ResponseListener<Token>() {
                    @Override
                    public void onSuccess(ServiceResponse<Token> serviceResponse) {
                        Token token = serviceResponse.getData();
                        headers.put("Authorization", "Bearer " + token.getToken());
                        headers.put("Content-Type", "application/json");
                        responseListener.onSuccess(new ServiceResponse<Map<String, String>>(headers));
                    }
                });
            }else{
                body.put("username", account.getUsername());
                body.put("userkey", account.getPassword());
                getILoginService().login(body , new ResponseListener<Token>() {
                    @Override
                    public void onSuccess(ServiceResponse<Token> serviceResponse) {
                        Token token = serviceResponse.getData();
                        headers.put("Authorization", "Bearer " + token.getToken());
                        headers.put("Content-Type", "application/json");
                        responseListener.onSuccess(new ServiceResponse<Map<String, String>>(headers));
                    }
                });
            }
        }else{
            headers.put("Authorization", "Bearer " + token.getToken());
            headers.put("Content-Type", "application/json");
            responseListener.onSuccess(new ServiceResponse<Map<String, String>>(headers));
        }
    }
}



