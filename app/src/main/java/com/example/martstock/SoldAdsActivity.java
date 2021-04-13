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
import java.util.Collections;

public class SoldAdsActivity extends AppCompatActivity {

    RecyclerView soldAdsRcv;
    RecyclerView.Adapter adapter;
    ArrayList<SoldAds> soldAds = new ArrayList<SoldAds>();

    FirebaseDatabase database;
    FirebaseAuth mAuth;
    String userId;

    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold_ads);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        soldAdsRcv = findViewById(R.id.soldAdsRcv);
        soldAdsRcv.setHasFixedSize(true);
        soldAdsRcv.setLayoutManager(new LinearLayoutManager(this));

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        userId = mAuth.getCurrentUser().getUid();


        ref =  FirebaseDatabase.getInstance().getReference("OldAds");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot s : dataSnapshot.getChildren()) {

                    SoldAds a = s.getValue(SoldAds.class);

                    if (userId.equals(a.getId())) {

                        Collections.reverse(soldAds);
                        soldAds.add(a);
                        adapter = new SoldAdsAdapter(soldAds);
                        soldAdsRcv.setAdapter(adapter);
                        adapter.notifyItemInserted(soldAds.size() - 1);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(SoldAdsActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });
    }


    }
