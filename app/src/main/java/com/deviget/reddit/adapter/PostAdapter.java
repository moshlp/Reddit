package com.deviget.reddit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.deviget.reddit.R;
import com.deviget.reddit.entities.Child;
import com.deviget.reddit.entities.Data_;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    
    List<Child> posts;

    private Context context;

    public PostAdapter(Context context, List<Child> posts) {
        this.posts = posts;
        this.context = context;
    }

    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postlayout, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bind(posts.get(position).data);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        TextView author;

        PostViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            author = itemView.findViewById(R.id.author);
        }


        public void bind(Data_ item) {
            author.setText(item.author);

        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}