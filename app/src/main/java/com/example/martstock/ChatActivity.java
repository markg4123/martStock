package com.example.martstock;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class ChatActivity extends AppCompatActivity {
    LinearLayout layout;
    RelativeLayout layout_2;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    Firebase reference1;
    Firebase reference2;
    DatabaseReference reference3;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        layout = findViewById(R.id.layout1);
        layout_2 = findViewById(R.id.layout2);
        sendButton = findViewById(R.id.sendButton);
        messageArea = findViewById(R.id.messageArea);
        scrollView = findViewById(R.id.scrollView);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        final String reciever = getIntent().getExtras().getString("id");


        Firebase.setAndroidContext(this);
        reference1 = new Firebase("https://martstock-cb651.firebaseio.com/Chat");
        reference2 = new Firebase("https://martstock-cb651.firebaseio.com/" + reciever + "_" + userId);
        reference3 =  FirebaseDatabase.getInstance().getReference("Chat").push();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();
                 String messageID = reference3.getKey();
                if(!messageText.equals("")){
                    Map<String, String> map = new HashMap<String,String>();
                    map.put("messageText", messageText);
                    map.put("messageSender", userId);
                    map.put("messageReciever", reciever);
                    map.put("messageID", messageID);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    messageArea.setText("");

                }
            }
        });

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("messageText").toString();
                String sender = map.get("messageSender").toString();
                String key = map.get("messageID").toString();

                Log.i(userId, "This is the user id"+ key + "This is the key"+ dataSnapshot.getKey());
                if(sender.equals(userId)&&(key.equals(dataSnapshot.getKey()))){
                    addMessageBox(message, 1);
                }
                 if(reciever.equals(userId)&&(key.equals(dataSnapshot.getKey()))){
                    addMessageBox(message, 2);
                }else{
                     Toast.makeText(ChatActivity.this, "No Messages!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void addMessageBox(String message, int type){
        TextView textView = new TextView(ChatActivity.this);
        textView.setText(message);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 7.0f;

        if(type == 1) {
            lp2.gravity = Gravity.LEFT;
            textView.setBackgroundResource(R.drawable.my_message);
        }
        else{
            lp2.gravity = Gravity.RIGHT;
            textView.setBackgroundResource(R.drawable.their_message);
        }
        textView.setLayoutParams(lp2);
        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}
