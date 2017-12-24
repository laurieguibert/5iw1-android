package apackage.thetvdb.storage;

import java.util.Map;

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

    @Override
    public void addAccount(final Map<String, String> credentials) {

        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                /* delete old account */
                Account oldAccount = realm.where(Account.class).findFirst();
                if(oldAccount != null) {
                    oldAccount.deleteFromRealm();
                }

                /* add new account */
                Account newAccount = realm.createObject(Account.class);
                newAccount.setUsername(credentials.get("username"));
                newAccount.setPassword(credentials.get("userkey"));
            }
        });
    }
}
