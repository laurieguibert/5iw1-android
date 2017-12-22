package apackage.thetvdb.service;

import apackage.thetvdb.entity.Account;
import apackage.thetvdb.entity.ServiceResponse;
import io.realm.Realm;

/**
 * Created by gianniazizi on 22/12/2017.
 */

public class AccountService implements IAccountService {
    private Realm realm;

    private Realm getRealmInstance() {
        if(realm == null) {
            realm = Realm.getDefaultInstance();
        }

        return realm;
    }

    @Override
    public void getCredentials(ResponseListener<Account> responseListener) {
        Account account = getRealmInstance().where(Account.class).findFirst();
        responseListener.onSuccess(new ServiceResponse<>(account));
    }

    @Override
    public void login(String username, String Password, ResponseListener<Account> responseListener) {

    }
}
