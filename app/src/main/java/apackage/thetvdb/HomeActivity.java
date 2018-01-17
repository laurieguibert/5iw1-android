package apackage.thetvdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import apackage.thetvdb.entity.Account;
import apackage.thetvdb.fragments.SearchFragment;
import apackage.thetvdb.storage.AccountService;
import apackage.thetvdb.storage.IAccountService;
import apackage.thetvdb.utils.ApiUtils;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private IAccountService storageAccountService;
    private RelativeLayout noConnected;
    private Button connect;
    private LinearLayout menu;
    private Button logout;
    private Button favorite;
    private static Realm realm;

    private Realm getRealm() {
        if(realm == null) {
            realm = Realm.getDefaultInstance();
        }

        return realm;
    }

    private IAccountService getStorageAccountService() {
        if(storageAccountService == null) {
            storageAccountService = new AccountService();
        }

        return storageAccountService;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SearchFragment())
                .commit();

        noConnected = (RelativeLayout) findViewById(R.id.no_connected);
        connect = (Button) findViewById(R.id.connect);
        menu = (LinearLayout) findViewById(R.id.menu);
        logout = (Button) findViewById(R.id.logout);
        favorite = (Button) findViewById(R.id.favorite);


        Account account = getStorageAccountService().getAccount();
        if(account != null) {
            menu.setVisibility(View.VISIBLE);

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getRealm().executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.deleteAll();
                        }
                    });
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            });

            favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HomeActivity.this, FavoriteActivity.class);
                    startActivity(intent);
                }
            });

        }else{
            noConnected.setVisibility(View.VISIBLE);
        }

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed()
    {

        // super.onBackPressed(); // Comment this super call to avoid calling finish() or fragmentmanager's backstack pop operation.
    }

}
