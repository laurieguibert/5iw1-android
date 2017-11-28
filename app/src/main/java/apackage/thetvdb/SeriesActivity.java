package apackage.thetvdb;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import apackage.thetvdb.fragments.SeriesFragment;

public class SeriesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SeriesFragment())
                .commit();

    }

}
