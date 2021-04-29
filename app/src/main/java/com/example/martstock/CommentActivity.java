package com.example.martstock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CommentActivity extends AppCompatActivity {
    EditText commentText;
    Button commentButton;
    TextView adTitle;
    DatabaseReference reference, reference1, userRef;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    String userId;
    ArrayList<Comment> comments = new ArrayList<Comment>();
    RecyclerView commentRCV;
    RecyclerView.Adapter adapter;
     String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        commentRCV = findViewById(R.id.commentRCV);
        commentRCV.setHasFixedSize(true);
        commentRCV.setLayoutManager(new LinearLayoutManager(this));

        final String reciever = getIntent().getExtras().getString("id");
        final String adID = getIntent().getExtras().getString("adId");
        final String title = getIntent().getExtras().getString("title");


        commentText = findViewById(R.id.commentText);
        commentButton = findViewById(R.id.commentButton);
        adTitle = findViewById(R.id.adTitle);

        adTitle.setText(title);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        userRef = FirebaseDatabase.getInstance().getReference("User");

        //get current users name
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    User user = snapshot1.getValue(User.class);

                    if(userId.equals(user.getId())){
                        name =  user.getName();
                    }
                }
                if(userId.equals(reciever)){
                    name = name + " (Owner)";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        reference = FirebaseDatabase.getInstance().getReference("Comment").push();
        reference1 = FirebaseDatabase.getInstance().getReference("Comment");

        //add a comment to database
        commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String comment = commentText.getText().toString();


                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date date = new Date();

                if (!comment.equals("")) {

                    Comment comment1 = new Comment(comment, reciever, name, adID, formatter.format(date));
                    comments.add(comment1);

                    reference.setValue(comment1).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CommentActivity.this, "Posted", Toast.LENGTH_LONG).show();
                                commentText.setText("");

                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CommentActivity.this, "Error Posting" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }
            }
        });

        //show all comments for an ad

        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                comments.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    Comment comment = snapshot1.getValue(Comment.class);

                    if(comment.adID.equals(adID)) {
                        comments.add(comment);
                    }

                    adapter = new CommentAdapter(comments);
                    commentRCV.setAdapter(adapter);
                    adapter.notifyItemInserted(comments.size() - 1);
                }

                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    }
