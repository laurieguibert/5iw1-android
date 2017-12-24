package apackage.thetvdb.storage;

import java.util.Map;

import apackage.thetvdb.entity.Account;

/**
 * Created by gianniazizi on 23/12/2017.
 */

public interface IAccountService {
    Account getAccount();
    void addAccount(Map<String, String> credentials);
}
