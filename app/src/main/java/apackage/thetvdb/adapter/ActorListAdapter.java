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
import apackage.thetvdb.entity.Actor;
import apackage.thetvdb.entity.Serie;

/**
 * Created by gianniazizi on 16/12/2017.
 */

public class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.MyViewHolder> {

    private List<Actor> actorList;
    private Context context;
    private IRecyclerViewClickListener iRecyclerViewClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {
        public TextView name;
        public ImageView image;

        private IRecyclerViewClickListener iRecyclerViewClickListener;

        public MyViewHolder(View view, IRecyclerViewClickListener iRecyclerViewClickListener) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            image = (ImageView) view.findViewById(R.id.image);
            this.iRecyclerViewClickListener = iRecyclerViewClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            iRecyclerViewClickListener.onClickListener(getAdapterPosition());
        }
    }


    public ActorListAdapter(List<Actor> actorList, Context context, IRecyclerViewClickListener iRecyclerViewClickListener) {
        this.actorList = actorList;
        this.context = context;
        this.iRecyclerViewClickListener = iRecyclerViewClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.actors_list_layout, parent, false);

        return new MyViewHolder(itemView, iRecyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Actor actor = actorList.get(position);
        holder.name.setText(actor.getName());


        if(actor.getImage().length() == 0) {
            holder.image.setImageResource(R.drawable.default_image);
        }else{
            Picasso.with(context).load("https://www.thetvdb.com/banners/_cache/" + actor.getImage()).into(holder.image);
        }


    }



    @Override
    public int getItemCount() {
        return actorList.size();
    }
}

