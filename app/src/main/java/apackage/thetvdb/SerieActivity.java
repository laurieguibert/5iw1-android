package apackage.thetvdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import apackage.thetvdb.entity.Serie;

public class SerieActivity extends AppCompatActivity {

    private final static String SERIE_KEY = "serie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie);

        Intent intent = getIntent();
        Serie serie = intent.getParcelableExtra(SERIE_KEY);
    }
}
