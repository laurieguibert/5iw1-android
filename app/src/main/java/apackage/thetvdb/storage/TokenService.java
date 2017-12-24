package apackage.thetvdb.storage;

import java.util.Date;

import apackage.thetvdb.entity.ServiceResponse;
import apackage.thetvdb.entity.Token;
import apackage.thetvdb.service.ResponseListener;
import io.realm.Realm;

/**
 * Created by gianniazizi on 23/12/2017.
 */

public class TokenService implements ITokenService {

    private Realm realm;

    private Realm getRealm() {
        if(realm == null) {
            realm = Realm.getDefaultInstance();
        }

        return realm;
    }

    @Override
    public Token getToken() {
        Token token = getRealm().where(Token.class).findFirst();
        return token;
    }

    @Override
    public void addToken(final Token token) {

        getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                 /* delete old token */
                Token oldToken = realm.where(Token.class).findFirst();
                if(oldToken != null) {
                    oldToken.deleteFromRealm();
                }

                /* add new token */
                Token newToken = realm.createObject(Token.class);
                newToken.setToken(token.getToken());
                newToken.setDate(new Date());
            }
        });

    }
}
