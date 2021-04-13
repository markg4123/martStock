package com.example.martstock;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>  {
    private ArrayList<ChatMessage> chats;


    FirebaseAuth mAuth;
    String userID;

    public MessageAdapter(ArrayList<ChatMessage> chats) {
        this.chats = chats;
        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.messagelayout, parent, false);

        return new ViewHolder(v);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ChatMessage chatMessage = chats.get(position);
        if (userID.equals(chatMessage.getMessageSender()) || userID.equals(chatMessage.getMessageReciever())) {
            holder.messageDisplay.setText(chatMessage.getMessageText());
            holder.senderDisplay.setText(chatMessage.getMessageSender());

            holder.messageDisplay.setText("Message: " +chatMessage.getMessageText());
            holder.senderDisplay.setText("From: "+chatMessage.getMessageSender());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(), ChatActivity.class);
                    i.putExtra("id",chatMessage.getMessageReciever());
                    v.getContext().startActivity(i);
                }
            });

        }






    }


    @Override
    public int getItemCount() {
        return chats.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView messageDisplay, senderDisplay;


        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            messageDisplay = (TextView) itemView.findViewById(R.id.messageDisplay);
            senderDisplay = (TextView) itemView.findViewById(R.id.senderDisplay);

        }

        @Override
        public void onClick(View v) {
            int position = this.getLayoutPosition();

        }
    }
}
