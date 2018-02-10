package com.twenty.four.crafts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thefinestartist.finestwebview.FinestWebView;

import java.util.ArrayList;

/**
 * Created by rakesh on 14/1/18.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder>{

    ArrayList<FeedItem> feedItems;
    Context context;
    RecyclerView recyclerView;

    FeedAdapter(Context context,RecyclerView recyclerView,ArrayList<FeedItem> feedItems)
    {

        this.recyclerView = recyclerView;
        this.context = context;
        this.feedItems  =feedItems;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(context).inflate(R.layout.custom_row_news_item,parent,false);
        FeedViewHolder holder = new FeedViewHolder(myView);

        return holder;
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {

        FeedItem current = feedItems.get(position);
        /*holder.date.setText(current.getPubDate());*/
        holder.title.setText(current.getTitle());
        holder.description.setText(current.getDecription());
        //Picasso.with(context).load(current.getThumbnailURL()).into(holder.thumbnail);

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                new FinestWebView.Builder(context).show(feedItems.get(position).getLink());
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));

    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {

        TextView title,description,date;
        ImageView thumbnail;
        public FeedViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.largeTextView);
            /*date = itemView.findViewById(R.id.smallTextView);*/
            description = itemView.findViewById(R.id.mediumTextView);
            thumbnail = itemView.findViewById(R.id.imageView);
        }
    }
}
