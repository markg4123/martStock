package com.example.martstock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    ListView listView;
    EditText messageText;
    ImageButton sendButton;

    ArrayList<ChatMessage> chatMessages = new ArrayList<>();

    DatabaseReference ref;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    String userId;

    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ref = FirebaseDatabase.getInstance().getReference().child("Chat").push();
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        listView = findViewById(R.id.listView);
        messageText = findViewById(R.id.messageText);
        sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message = messageText.getText().toString();
                String sender = userId;
                String reciever = getIntent().getExtras().getString("id");

                ChatMessage chatMessage = new ChatMessage(sender, reciever, message);
                chatMessages.add(chatMessage);

                ref.setValue(chatMessage).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ChatActivity.this, "Your Message Has Been Sent", Toast.LENGTH_LONG).show();
                            Adapter adapter = new ArrayAdapter<ChatMessage>(ChatActivity.this, android.R.layout.simple_list_item_1, chatMessages);
                            listView.setAdapter((ListAdapter) adapter);
                        } else {
                            Toast.makeText(ChatActivity.this, "Your Message Has Not Been Sent", Toast.LENGTH_LONG).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ChatActivity.this, "Insert ad failed" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}