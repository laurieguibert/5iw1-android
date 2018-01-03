package apackage.thetvdb.utils;

import android.util.Log;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import apackage.thetvdb.entity.Account;
import apackage.thetvdb.entity.ServiceResponse;
import apackage.thetvdb.entity.Token;
import apackage.thetvdb.remote.RetrofitClient;
import apackage.thetvdb.service.ILoginService;
import apackage.thetvdb.service.IRFLoginService;
import apackage.thetvdb.service.IRFSerieService;
import apackage.thetvdb.service.IRFUserService;
import apackage.thetvdb.service.LoginService;
import apackage.thetvdb.service.ResponseListener;
import apackage.thetvdb.storage.AccountService;
import apackage.thetvdb.storage.IAccountService;
import apackage.thetvdb.storage.ITokenService;
import apackage.thetvdb.storage.TokenService;
import io.realm.Realm;

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
    private static Realm realm;
    private static Account account;

    private static Realm getRealm() {
        if(realm == null) {
            realm = Realm.getDefaultInstance();
        }

        return realm;
    }

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

    public static IRFUserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(IRFUserService.class);
    }

    public static void getConnection(final ResponseListener<Map<String, String>> responseListener) {
        Token token = getStorageTokenService().getToken();
        body.put("apikey", API_KEY);
        if(token == null || token.getToken() == null) {
            getToken(responseListener);
        }else{
            Log.e("DEV", "DATE TOKEN : " + token.getDate().getTime());
            if (new Date().getTime() - token.getDate().getTime() > 3600*23) {
               getToken(responseListener);
            }else{
                headers.put("Authorization", "Bearer " + token.getToken());
                headers.put("Content-Type", "application/json");
                responseListener.onSuccess(new ServiceResponse<Map<String, String>>(headers));
            }
        }
    }

    private static void getToken(final ResponseListener<Map<String, String>> responseListener) {
        account = getStorageAccountService().getAccount();

        if(account == null) {
            getILoginService().login(body , new ResponseListener<Token>() {
                @Override
                public void onSuccess(ServiceResponse<Token> serviceResponse) {
                    final Token token = serviceResponse.getData();

                    getStorageTokenService().addToken(token);

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
    }

    public static void checkAccount(final Map<String, String> body, final ResponseListener<Boolean> responseListener) {
        body.put("apikey", API_KEY);
        getILoginService().login(body, new ResponseListener<Token>() {
            @Override
            public void onSuccess(ServiceResponse<Token> serviceResponse) {
                final Token token = serviceResponse.getData();
                Boolean response = false;

                if(token != null) {
                    getStorageTokenService().addToken(token);
                    getStorageAccountService().addAccount(body);
                    response = true;
                }

                responseListener.onSuccess(new ServiceResponse<>(response));
            }
        });
    }
}



