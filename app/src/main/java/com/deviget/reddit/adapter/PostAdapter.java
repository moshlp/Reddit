package com.deviget.reddit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.deviget.reddit.R;
import com.deviget.reddit.entities.Child;
import com.deviget.reddit.entities.Data_;
import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    List<Child> posts;

    private final OnItemClickListener listener;
    private Context context;

    public PostAdapter(Context context, List<Child> posts, OnItemClickListener listener) {
        this.posts = posts;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postlayout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bind(posts.get(position).data, listener);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView title;
        TextView author;
        TextView date;
        ImageView thumbnail;
        TextView comments;
        ImageView status;
        ImageButton button;

        PostViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            title = itemView.findViewById(R.id.title);
            author = itemView.findViewById(R.id.author);
            date = itemView.findViewById(R.id.date);
            comments = itemView.findViewById(R.id.comments);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            status = itemView.findViewById(R.id.status);
            button = itemView.findViewById(R.id.button);
        }


        public void bind(Data_ item, OnItemClickListener listener) {
            title.setText(item.title);
            author.setText(item.author);
            date.setText(context.getResources().getString(R.string.daysago, getDifferenceDays(new Date(item.created), new Date())));
            comments.setText(context.getResources().getString(R.string.comments, item.getNumComments()));
            if (item.getThumbnail().isEmpty()){
                thumbnail.setVisibility(View.GONE);
            } else {
                Picasso.get().load(item.getThumbnail()).into(thumbnail);
            }
            itemView.setOnClickListener(v -> {
                status.setVisibility(View.INVISIBLE);
                listener.onItemClick(item);
            });
            button.setOnClickListener(v -> {
                posts.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
                notifyItemRangeChanged(getAdapterPosition(), posts.size());
            });
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public interface OnItemClickListener {
        void onItemClick(Data_ item);
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

}