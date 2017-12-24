package apackage.thetvdb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import apackage.thetvdb.entity.ServiceResponse;
import apackage.thetvdb.entity.Token;
import apackage.thetvdb.service.ILoginService;
import apackage.thetvdb.service.LoginService;
import apackage.thetvdb.service.ResponseListener;
import apackage.thetvdb.utils.ApiUtils;


public class LoginActivity extends AppCompatActivity {

    private ILoginService loginService;
    private Map<String, String> token = null;

    private ILoginService getLoginService() {
        if(loginService == null) {
            loginService = new LoginService();
        }

        return loginService;
    }

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
        final TextView errorMessage = (TextView) findViewById(R.id.login_error);

        Map<String, String> body = new HashMap<>();
        body.put("username", username.getText().toString());
        body.put("userkey", accountIdentifier.getText().toString());

        ApiUtils.checkAccount(body, new ResponseListener<Boolean>() {
            @Override
            public void onSuccess(ServiceResponse<Boolean> serviceResponse) {
                if(serviceResponse.getData()) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                }else{
                    errorMessage.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
