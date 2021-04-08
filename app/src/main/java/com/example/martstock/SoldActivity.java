package com.example.martstock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SoldActivity extends AppCompatActivity {

    TextView soldAdTitle;
    EditText descriptionText, numSoldText, avgWeightText, priceText, dateText,martText;
    Button soldButton;
    String title, desc, key, key1;
    DatabaseReference reference,fireDB;
    FirebaseAuth mAuth;
    ArrayList<SoldAds> ads = new ArrayList<SoldAds>();
    String askingPrice;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("OldAds").push();


        soldAdTitle = findViewById(R.id.soldAdTitle);
        descriptionText = findViewById(R.id.descriptionText);
        numSoldText = findViewById(R.id.numSoldText);
        avgWeightText = findViewById(R.id.avgWeightText);
        priceText = findViewById(R.id.soldPriceText);
        dateText = findViewById(R.id.soldDateText);
        soldButton = findViewById(R.id.soldButton);
        martText = findViewById(R.id.martText);

        dateText.setInputType(InputType.TYPE_NULL);

        title = getIntent().getExtras().getString("title");
        desc = getIntent().getExtras().getString("desc");
        askingPrice = getIntent().getExtras().getString("prices");
        final int askingpriceReal = Integer.parseInt(askingPrice);
        key = getIntent().getExtras().getString("key");

        soldAdTitle.setText(title);
        descriptionText.setText(desc);

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(dateText);
            }
        });

        soldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String adTitle = soldAdTitle.getText().toString();
                final String adDate = dateText.getText().toString();
                final String mart = martText.getText().toString();
                final String id = mAuth.getCurrentUser().getUid();
                final int numSold = Integer.parseInt(numSoldText.getText().toString());
                final int price = Integer.parseInt(priceText.getText().toString());
                final double avgWeight = Double.parseDouble(avgWeightText.getText().toString());
                final double pricePerKilo = (price/avgWeight);
                final double difference = (price-askingpriceReal);
                new DecimalFormat("#.##").format(difference);
                new DecimalFormat("#.##").format(pricePerKilo);


                Intent i = new Intent(v.getContext(), UpdateAd.class);
                i.putExtra("title", title);
                i.putExtra("desc", desc);
                v.getContext().startActivity(i);


                fireDB = FirebaseDatabase.getInstance().getReference("Ad");

                fireDB.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for(DataSnapshot userSnapshot : snapshot.getChildren()) {
                            Ad ad = userSnapshot.getValue(Ad.class);

                            key1 = userSnapshot.getKey();

                            if(key.equals(key1)){

                                SoldAds soldAds = new SoldAds(adTitle, adDate,mart,id, numSold, price, avgWeight, pricePerKilo, askingpriceReal,difference);
                                ads.add(soldAds);

                                 fireDB= FirebaseDatabase.getInstance().getReference("Ad");
                                 fireDB.child(key).removeValue();

                                reference.setValue(soldAds).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SoldActivity.this, "Your Ad Has Been Marked As Sold", Toast.LENGTH_LONG).show();
                                            Intent i = new Intent(SoldActivity.this, SoldAdsActivity.class);
                                            startActivity(i);
                                        } else {
                                            Toast.makeText(SoldActivity.this, "Error!", Toast.LENGTH_LONG).show();
                                        }

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SoldActivity.this, "Process Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });

                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



            }
        });
    }
    private void showDateDialog(final EditText dateText){
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR,year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy");
                dateText.setText(simpleDateFormat.format(calendar.getTime()));

            }
        };
        new DatePickerDialog(SoldActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}