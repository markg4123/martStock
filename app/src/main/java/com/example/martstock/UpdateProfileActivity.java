package com.example.martstock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfileActivity extends AppCompatActivity {
    EditText updateNameText, updateUsernameText;
    TextView updatePasswordText,updateEmailText;
    Button updateButton2;

    DatabaseReference userRef;
    FirebaseDatabase database;
    FirebaseUser user;
    FirebaseAuth mAuth;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        updateNameText = findViewById(R.id.updateNameText);
        updateUsernameText = findViewById(R.id.updateUsernameText);
        updatePasswordText = findViewById(R.id.updatePasswordText);
        updateEmailText = findViewById(R.id.updateEmailText);
        updateButton2 = findViewById(R.id.updateButton2);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        userRef = database.getReference("User");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot userSnapshot : snapshot.getChildren()) {
                    User users = userSnapshot.getValue(User.class);
                    if (userId.equals(users.getId())) {

                        updateNameText.setText(users.getName());
                        updateEmailText.setText((users.getEmail()));
                        updateUsernameText.setText(users.getUsername());
                        updatePasswordText.setText(users.getPassword());

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //update profile
        updateButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = updateNameText.getText().toString();
                String uname = updateUsernameText.getText().toString();


                user = mAuth.getCurrentUser();


                DatabaseReference fireDB = FirebaseDatabase.getInstance().getReference("User").child(userId);

                fireDB.child("name").setValue(name);
                fireDB.child("username").setValue(uname);


                Toast.makeText(UpdateProfileActivity.this, "User profiler updated!", Toast.LENGTH_LONG).show();
            }

        });

    }


}
