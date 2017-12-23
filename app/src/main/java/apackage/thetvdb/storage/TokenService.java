package apackage.thetvdb.storage;

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
}
