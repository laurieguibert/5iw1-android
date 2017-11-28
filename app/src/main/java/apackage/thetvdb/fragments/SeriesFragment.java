package apackage.thetvdb.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sburba.tvdbapi.model.Series;

import apackage.thetvdb.R;
import apackage.thetvdb.utils.DownloadImageTask;



public class SeriesFragment extends Fragment {

    public static final String EXTRA_SERIES = "series";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_series,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView description = (TextView) view.findViewById(R.id.description);
        Button showActors = (Button) view.findViewById(R.id.show_actors);


        Intent intent = getActivity().getIntent();
        Series series = intent.getParcelableExtra(EXTRA_SERIES);


        new DownloadImageTask(imageView).execute(series.getImageUrl().replace("http", "https"));
        title.setText(series.getTitleText());
        description.setText(series.getDescText());

        showActors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new ActorsFragment())
                        .addToBackStack("tag")
                        .commit();
            }
        });
    }
}
