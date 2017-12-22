package apackage.thetvdb;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by gianniazizi on 22/12/2017.
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(config);
    }
}
