package com.example.martstock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManageMyAccount extends AppCompatActivity {
    DatabaseReference userRef;
    FirebaseDatabase database;
    FirebaseUser user;
    FirebaseAuth mAuth;
    String userId;
    TextView nameView, emailView, editView, adsView, logoutView,soldAdsView, likedView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_my_account);

        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        user = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        nameView = findViewById(R.id.nameView);
        emailView = findViewById(R.id.emailView);
        editView = findViewById(R.id.editView);
        adsView = findViewById(R.id.adsView);
        logoutView = findViewById(R.id.logoutView);
        soldAdsView =findViewById(R.id.soldAdsView);
        likedView =findViewById(R.id.likedView);



        userRef = database.getReference("User");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot userSnapshot : snapshot.getChildren()) {
                    User users = userSnapshot.getValue(User.class);

                    if (userId.equals(users.getId())) {
                        nameView.setText(users.getName());
                        emailView.setText(users.getEmail());

                    }

                }
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManageMyAccount.this, UpdateProfileActivity.class);
                startActivity(i);
            }
        });

        adsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(ManageMyAccount.this, ViewAdsActivity.class);
                startActivity(ii);
            }
        });

        soldAdsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iii = new Intent(ManageMyAccount.this, SoldAdsActivity.class);
                startActivity(iii);
            }
        });

        likedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iv = new Intent(ManageMyAccount.this, LikedAdsActivity.class);
                startActivity(iv);
            }
        });

    }
}