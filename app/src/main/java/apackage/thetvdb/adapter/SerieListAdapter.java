package apackage.thetvdb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import apackage.thetvdb.R;
import apackage.thetvdb.entity.Serie;

/**
 * Created by gianniazizi on 16/12/2017.
 */

public class SerieListAdapter extends RecyclerView.Adapter<SerieListAdapter.MyViewHolder> {

    private List<Serie> serieList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView seriesName;
        public ImageView banner;
        public TextView firstAired;

        public MyViewHolder(View view) {
            super(view);
            seriesName = (TextView) view.findViewById(R.id.name);
            banner = (ImageView) view.findViewById(R.id.image);
            firstAired = (TextView) view.findViewById(R.id.firstAired);
        }
    }


    public SerieListAdapter(List<Serie> serieList, Context context) {
        this.serieList = serieList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.series_list_layout, parent, false);

        return new MyViewHolder(itemView);
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

