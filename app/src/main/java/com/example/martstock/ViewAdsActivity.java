package com.example.martstock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ViewAdsActivity extends AppCompatActivity {
    DatabaseReference userRef;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    String userId;


    RecyclerView rcv2;
    RecyclerView.Adapter adapter;
    ArrayList<Ad> ads = new ArrayList<Ad>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ads);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userId = mAuth.getCurrentUser().getUid();

        rcv2 = findViewById(R.id.rcv2);
        rcv2.setHasFixedSize(true);
        rcv2.setLayoutManager(new LinearLayoutManager(this));
        userRef =  FirebaseDatabase.getInstance().getReference("Ad");

        adapter = new MyAdsAdapter(ads);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot s : dataSnapshot.getChildren()) {

                    Ad a = s.getValue(Ad.class);


                    if (userId.equals(a.getId())) {

                        Collections.reverse(ads);
                        ads.add(a);
                        rcv2.setAdapter(adapter);
                        adapter.notifyItemInserted(ads.size() - 1);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(ViewAdsActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });


    }

}