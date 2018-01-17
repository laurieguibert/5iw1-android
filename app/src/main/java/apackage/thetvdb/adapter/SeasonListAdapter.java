package apackage.thetvdb.adapter;

/**
 * Created by gianniazizi on 15/01/2018.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import apackage.thetvdb.R;


public class SeasonListAdapter extends RecyclerView.Adapter<SeasonListAdapter.MyViewHolder> {

    private List<String> seasonList;
    private Context context;
    private IRecyclerViewClickListener iRecyclerViewClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {
        public TextView season;
        private IRecyclerViewClickListener iRecyclerViewClickListener;

        public MyViewHolder(View view, IRecyclerViewClickListener iRecyclerViewClickListener) {
            super(view);
            season = (TextView) view.findViewById(R.id.season);
            this.iRecyclerViewClickListener = iRecyclerViewClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iRecyclerViewClickListener.onClickListener(getAdapterPosition());
        }
    }


    public SeasonListAdapter(List<String> seasonList, Context context, IRecyclerViewClickListener iRecyclerViewClickListener) {
        this.seasonList = seasonList;
        this.context = context;
        this.iRecyclerViewClickListener = iRecyclerViewClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.seasons_list_layout, parent, false);

        return new MyViewHolder(itemView, iRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        String season = seasonList.get(position);
        holder.season.setText(season);
    }



    @Override
    public int getItemCount() {
        return seasonList.size();
    }
}


