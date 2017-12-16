package apackage.thetvdb.service;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

import apackage.thetvdb.HomeActivity;
import apackage.thetvdb.R;

import static android.content.ContentValues.TAG;


public class LoginService extends AsyncTask<String, String, String> {

    private final String url = "https://api.thetvdb.com/login";
    private Activity activity;
    private SharedPreferences sharedPreferences;
    private TextView errorMessage;

    public LoginService(Activity activity, SharedPreferences sharedPreferences, TextView errorMessage) {
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;
        this.errorMessage = errorMessage;
    }

    @Override
    protected String doInBackground(String... params) {

        JSONObject response;
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(this.url);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");


            Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
            writer.write(params[0]);
            writer.close();


            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String inputLine;
            while ((inputLine = reader.readLine()) != null)
                buffer.append(inputLine + "\n");
            if (buffer.length() == 0) {
                return null;
            }


            try {
                return buffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;


        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(TAG, "Error closing stream", e);
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        String token = "";

        try {

            if(s != null) {
                JSONObject data = new JSONObject(s);
                token = data.getString("token");

                if(token != null) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("token", token);
                    editor.commit();
                    activity.startActivity(new Intent(activity, HomeActivity.class));
                }else{
                    this.errorMessage.setText(R.string.login_error);
                }

            }else{
                this.errorMessage.setText(R.string.login_error);
            }

        } catch(JSONException e) {
            e.printStackTrace();
        }

    }

}
