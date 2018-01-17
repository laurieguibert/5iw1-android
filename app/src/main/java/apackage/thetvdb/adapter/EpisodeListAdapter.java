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
import apackage.thetvdb.entity.Episode;


public class EpisodeListAdapter extends RecyclerView.Adapter<EpisodeListAdapter.MyViewHolder> {

    private List<Episode> episodeList;
    private Context context;
    private IRecyclerViewClickListener iRecyclerViewClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {
        public TextView episodeNumber;
        public TextView episodeName;
        private IRecyclerViewClickListener iRecyclerViewClickListener;

        public MyViewHolder(View view, IRecyclerViewClickListener iRecyclerViewClickListener) {
            super(view);
            episodeNumber = (TextView) view.findViewById(R.id.episode_number);
            episodeName = (TextView) view.findViewById(R.id.episode_name);
            this.iRecyclerViewClickListener = iRecyclerViewClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iRecyclerViewClickListener.onClickListener(getAdapterPosition());
        }
    }


    public EpisodeListAdapter(List<Episode> episodeList, Context context, IRecyclerViewClickListener iRecyclerViewClickListener) {
        this.episodeList = episodeList;
        this.context = context;
        this.iRecyclerViewClickListener = iRecyclerViewClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.episodes_list_layout, parent, false);

        return new MyViewHolder(itemView, iRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Episode episode = episodeList.get(position);
        if(episode.getAbsoluteNumber() != null) {
            holder.episodeNumber.setText(episode.getAbsoluteNumber().toString());
        }
        holder.episodeName.setText(episode.getEpisodeName());
    }



    @Override
    public int getItemCount() {
        return episodeList.size();
    }
}


