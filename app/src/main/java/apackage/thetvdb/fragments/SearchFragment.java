package apackage.thetvdb.fragments;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import apackage.thetvdb.R;
import apackage.thetvdb.SerieActivity;
import apackage.thetvdb.adapter.IRecyclerViewClickListener;
import apackage.thetvdb.adapter.SerieListAdapter;
import apackage.thetvdb.entity.Serie;
import apackage.thetvdb.entity.ServiceResponse;
import apackage.thetvdb.service.ISerieService;
import apackage.thetvdb.service.ResponseListener;
import apackage.thetvdb.service.SerieService;
import apackage.thetvdb.utils.ApiUtils;


public class SearchFragment extends Fragment {

    public ISerieService serieService;
    private SerieListAdapter serieListAdapter;
    private RecyclerView recyclerView;
    private List<Serie> serieList = new ArrayList<>();
    private final static String SERIE_KEY = "serie";

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

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        serieListAdapter = new SerieListAdapter(serieList, getContext(), new IRecyclerViewClickListener() {
            @Override
            public void onClickListener(int position) {
                // TODO new intent to serieActivity
                Intent intent = new Intent(getActivity(), SerieActivity.class);
                intent.putExtra(SERIE_KEY, serieList.get(position));
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(serieListAdapter);


        final EditText edit_txt = (EditText) view.findViewById(R.id.search_bar);

        /* Change drawable color */

        int tintColor = ContextCompat.getColor(view.getContext(), R.color.orange);

        Drawable drawable = ContextCompat.getDrawable(view.getContext(), R.drawable.ic_search_black_24dp);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable.mutate(), tintColor);

        drawable.setBounds( 0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        edit_txt.setCompoundDrawables(drawable, null, null, null);



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
                                serieList.add(serie);
                            }

                            serieListAdapter.notifyDataSetChanged();
                        }
                    });


                }
                return false;
            }
        });
    }
}
