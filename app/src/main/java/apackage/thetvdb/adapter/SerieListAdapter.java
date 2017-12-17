package apackage.thetvdb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import apackage.thetvdb.R;
import apackage.thetvdb.entity.Serie;
import apackage.thetvdb.fragments.SearchFragment;

/**
 * Created by gianniazizi on 16/12/2017.
 */

public class SerieListAdapter extends RecyclerView.Adapter<SerieListAdapter.MyViewHolder> {

    private List<Serie> serieList;
    private Context context;
    private IRecyclerViewClickListener iRecyclerViewClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {
        public TextView seriesName;
        public ImageView banner;
        public TextView firstAired;
        private IRecyclerViewClickListener iRecyclerViewClickListener;

        public MyViewHolder(View view, IRecyclerViewClickListener iRecyclerViewClickListener) {
            super(view);
            seriesName = (TextView) view.findViewById(R.id.name);
            banner = (ImageView) view.findViewById(R.id.image);
            firstAired = (TextView) view.findViewById(R.id.firstAired);
            this.iRecyclerViewClickListener = iRecyclerViewClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iRecyclerViewClickListener.onClickListener(getAdapterPosition());
        }
    }


    public SerieListAdapter(List<Serie> serieList, Context context, IRecyclerViewClickListener iRecyclerViewClickListener) {
        this.serieList = serieList;
        this.context = context;
        this.iRecyclerViewClickListener = iRecyclerViewClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.series_list_layout, parent, false);

        return new MyViewHolder(itemView, iRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Serie serie = serieList.get(position);
        holder.seriesName.setText(serie.getSeriesName());
        holder.firstAired.setText(serie.getFirstAired());


        if(serie.getBanner().length() == 0) {
            holder.banner.setImageResource(R.drawable.default_image);
        }else{
            Picasso.with(context).load("https://www.thetvdb.com/banners/_cache/" + serie.getBanner()).into(holder.banner);
        }


    }



    @Override
    public int getItemCount() {
        return serieList.size();
    }
}

