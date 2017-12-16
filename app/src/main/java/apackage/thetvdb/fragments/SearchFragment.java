package apackage.thetvdb.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import apackage.thetvdb.R;
import apackage.thetvdb.entity.Serie;
import apackage.thetvdb.entity.ServiceResponse;
import apackage.thetvdb.service.ISerieService;
import apackage.thetvdb.service.ResponseListener;
import apackage.thetvdb.service.SerieService;
import apackage.thetvdb.utils.ApiUtils;


public class SearchFragment extends ListFragment {

    public ISerieService serieService;

    public ISerieService getSerieService() {
        if(serieService == null) {
            serieService = new SerieService();
        }

        return serieService;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final EditText edit_txt = (EditText) view.findViewById(R.id.search_bar);

        edit_txt.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String search = edit_txt.getText().toString();


                    getSerieService().list(ApiUtils.getHeaders(), search, new ResponseListener<List<Serie>>() {
                        @Override
                        public void onSuccess(ServiceResponse<List<Serie>> serviceResponse) {
                            List<Serie> series = serviceResponse.getData();
                            for(Serie serie : series) {
                                Log.e("DEV", "NAME : " + serie.getSeriesName());
                            }
                        }
                    });


                }
                return false;
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }
}
