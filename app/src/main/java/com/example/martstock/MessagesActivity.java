package com.example.martstock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessagesActivity extends AppCompatActivity {
    RecyclerView messagesRcv;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    String userId;
    DatabaseReference ref;

    RecyclerView.Adapter adapter;
    ArrayList<ChatMessage> chatMessages = new ArrayList<ChatMessage>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        messagesRcv = findViewById(R.id.messagesRcv);
        messagesRcv.setHasFixedSize(true);
        messagesRcv.setLayoutManager(new LinearLayoutManager(this));

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userId = mAuth.getCurrentUser().getUid();



        ref =  FirebaseDatabase.getInstance().getReference("Chat");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()) {

                    ChatMessage chatMessage = s.getValue(ChatMessage.class);

                    chatMessages.add(chatMessage);


                    adapter = new MessageAdapter(chatMessages);
                    messagesRcv.setAdapter(adapter);
                    adapter.notifyItemInserted(chatMessages.size() - 1);

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MessagesActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });



    }
}