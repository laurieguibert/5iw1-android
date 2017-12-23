package apackage.thetvdb.service;

import java.util.Map;

import apackage.thetvdb.entity.Token;

/**
 * Created by gianniazizi on 23/12/2017.
 */

public interface ILoginService {
    void login(Map<String, String> body, ResponseListener<Token> responseListener);
}
