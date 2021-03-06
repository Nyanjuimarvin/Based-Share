package com.nyanjuimarvin.basedshare.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nyanjuimarvin.basedshare.R;
import com.nyanjuimarvin.basedshare.models.GeneralFeed;

import java.util.List;

//Adapter class for cards
public class FeedViewAdapter extends RecyclerView.Adapter<FeedViewAdapter.MyViewHolder>{

    private Context context;
    private List<GeneralFeed> feedList;
    private CustomOnItemClickListener listener;

    public interface CustomOnItemClickListener {
        void onItemClick (GeneralFeed feed);
    }


    public FeedViewAdapter(Context context, List<GeneralFeed> feedList, CustomOnItemClickListener listener) {
        this.context = context;
        this.feedList = feedList;
        this.listener = listener;
    }




    //Inflate both layouts :: cardView and recyclerView
    @NonNull
    @Override
    public FeedViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_general_feed,parent,false);
        return new MyViewHolder(view);
    }

    //Bind Data to the textViews in cardView
    @Override
    public void onBindViewHolder(@NonNull FeedViewAdapter.MyViewHolder holder, int position) {
        GeneralFeed feed = feedList.get(position);
        holder.userNameText.setText(feed.getUserName());
        holder.categoryText.setText(feed.getCategory());
        holder.descriptionText.setText(feed.getDescription());
        holder.bind(feed,listener);

    }

    @Override
    public int getItemCount() {
        return feedList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView userNameText;
        private TextView categoryText;
        private TextView descriptionText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userNameText = itemView.findViewById(R.id.userNameTextView);
            categoryText = itemView.findViewById(R.id.categoryTextView);
            descriptionText = itemView.findViewById(R.id.descriptionTextView);
        }

        //Bind Function
        public void bind(final GeneralFeed feed, final CustomOnItemClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    listener.onItemClick(feed);
                }
            });
        }

    }
}
