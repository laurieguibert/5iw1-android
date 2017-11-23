package apackage.thetvdb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import apackage.thetvdb.services.LoginService;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = (Button) findViewById(R.id.submit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit();
            }
        });
    }

    private void onSubmit() {
        EditText username = (EditText) findViewById(R.id.username);
        EditText accountIdentifier = (EditText) findViewById(R.id.account_identifier);

        Log.d("DEV", "username : " + username.getText() + " accountID : " + accountIdentifier.getText());

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("apikey", getResources().getString(R.string.api_key));
            jsonObject.put("username", username.getText());
            jsonObject.put("userkey", accountIdentifier.getText());
        } catch(JSONException e) {
            e.printStackTrace();
        }

        new LoginService(this, getSharedPreferences("settings", Context.MODE_PRIVATE)).execute(String.valueOf(jsonObject));


    }
}