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

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private IAccountService storageAccountService;
    private RelativeLayout noConnected;
    private Button connect;

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


        Account account = getStorageAccountService().getAccount();
        if(account != null) {

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

}
