package apackage.thetvdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import apackage.thetvdb.entity.Serie;

public class SerieActivity extends AppCompatActivity {

    private final static String SERIE_KEY = "serie";

    private TextView seriesName;
    private TextView overview;
    private TextView date;
    private ImageView banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie);

        Intent intent = getIntent();
        Serie serie = intent.getParcelableExtra(SERIE_KEY);

        seriesName = (TextView) findViewById(R.id.series_name);
        overview = (TextView) findViewById(R.id.overview);
        banner = (ImageView) findViewById(R.id.banner);
        date = (TextView) findViewById(R.id.date);

        seriesName.setText(serie.getSeriesName());
        overview.setText(serie.getOverview());
        date.setText(serie.getFirstAired());


        if(serie.getBanner().length() == 0) {
            banner.setImageResource(R.drawable.default_image);
        }else{
            Picasso.with(this).load("https://www.thetvdb.com/banners/_cache/" + serie.getBanner()).into(banner);
        }

    }
}
