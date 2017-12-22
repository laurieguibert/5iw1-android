package apackage.thetvdb.service;

import apackage.thetvdb.entity.Account;

/**
 * Created by gianniazizi on 22/12/2017.
 */

public interface IAccountService {
    void getCredentials(ResponseListener<Account> responseListener);
    void login(String username, String Password, ResponseListener<Account> responseListener);
}
