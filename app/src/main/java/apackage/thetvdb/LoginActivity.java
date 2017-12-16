package apackage.thetvdb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import apackage.thetvdb.service.LoginService;


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
        TextView errorMessage = (TextView) findViewById(R.id.login_error);

        Log.d("DEV", "username : " + username.getText() + " accountID : " + accountIdentifier.getText());

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("apikey", getResources().getString(R.string.api_key));
            jsonObject.put("username", username.getText());
            jsonObject.put("userkey", accountIdentifier.getText());
        } catch(JSONException e) {
            e.printStackTrace();
        }

        new LoginService(this, getSharedPreferences("settings", Context.MODE_PRIVATE), errorMessage).execute(String.valueOf(jsonObject));


    }
}
