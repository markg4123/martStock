package com.example.martstock;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private ArrayList<Comment> comments;

    public CommentAdapter(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.commentlayout, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = comments.get(position);


        holder.commentTextview.setText(comment.getComment());
        holder.userTextview.setText(comment.getSender());
        holder.commentDate.setText(comment.getDate());

    }
    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView commentTextview, userTextview, commentDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            commentTextview = (TextView) itemView.findViewById(R.id.commentTextview);
            userTextview = (TextView) itemView.findViewById(R.id.userTextview);
            commentDate = (TextView) itemView.findViewById(R.id.commentDate);

        }
    }




}
