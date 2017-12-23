package apackage.thetvdb.storage;

import apackage.thetvdb.entity.Account;
import io.realm.Realm;

/**
 * Created by gianniazizi on 23/12/2017.
 */

public class AccountService implements IAccountService {

    private Realm realm;

    private Realm getRealm() {
        if(realm == null) {
            realm = Realm.getDefaultInstance();
        }

        return realm;
    }

    @Override
    public Account getAccount() {
        Account account = getRealm().where(Account.class).findFirst();
        return account;
    }
}
