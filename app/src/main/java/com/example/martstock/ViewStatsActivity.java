package com.example.martstock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;

public class ViewStatsActivity extends AppCompatActivity {
    DatabaseReference userRef;
    int numBulls=0, numBullocks=0, numCalves=0, numDry=0, numCow=0, numHeifers=0,
        numCulls=0, numEwes=0, numLambs=0,numRams = 0;
    double priceBulls, priceBullocks, priceCalves, priceDry, priceCow, priceHeifers,
            priceCull, priceEwe, priceLamb, priceRam;
    TextView numBullsText, bullsPriceText,numBullocksText, bullockPriceText,numCalfText, calfPriceText,
    numCowText, cowPriceText,numDryText, dryPriceText,numHeiferText, heiferPriceText, numCullsText, cullspriceTex,
    numEwesText, ewesPriceText, numLambsText, lambPriceText, numRamText, ramPriceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stats);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        numBullsText = findViewById(R.id.numBullsText);
        bullsPriceText = findViewById(R.id.bullsPriceText);
        numBullocksText = findViewById(R.id.numBullocksText);
        bullockPriceText = findViewById(R.id.bullockPriceText);
        numCalfText = findViewById(R.id.numCalfText);
        calfPriceText = findViewById(R.id.calfPriceText);
        numCowText = findViewById(R.id.numCowText);
        cowPriceText = findViewById(R.id.cowPriceText);
        numDryText = findViewById(R.id.numDryText);
        dryPriceText = findViewById(R.id.dryPriceText);
        numHeiferText = findViewById(R.id.numHeiferText);
        heiferPriceText = findViewById(R.id.heiferPriceText);
        numCullsText = findViewById(R.id.numCullText);
        cullspriceTex = findViewById(R.id.cullPriceText);
        numEwesText = findViewById(R.id.numEweText);
        ewesPriceText = findViewById(R.id.ewePriceText);
        numLambsText = findViewById(R.id.numLambText);
        lambPriceText = findViewById(R.id.lambPriceText);
        numRamText = findViewById(R.id.numRamText);
        ramPriceText = findViewById(R.id.ramPriceText);




        userRef =  FirebaseDatabase.getInstance().getReference("Ad");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot s : dataSnapshot.getChildren()) {

                    Ad a = s.getValue(Ad.class);

                    if(a.getSubsection().equals("Bull")) {
                        numBulls = numBulls +1;
                        priceBulls = ( priceBulls +  a.getPrice())/numBulls;
                    }
                    if(a.getSubsection().equals("Bullock")) {
                        numBullocks = numBullocks +1;
                        priceBullocks = (priceBullocks + a.getPrice())/numBullocks;
                    }
                    if(a.getSubsection().equals("Cow")) {
                        numCow = numCow +1;
                        priceCow = (priceCow + a.getPrice())/numCow;
                    }
                    if(a.getSubsection().equals("Calf")) {
                        numCalves= numCalves +1;
                        priceCalves = (priceCalves + a.getPrice())/numCalves;
                    }

                    if(a.getSubsection().equals("Heifer")) {
                        numHeifers = numHeifers +1;
                        priceHeifers = (priceHeifers + a.getPrice())/numHeifers;
                    }
                    if(a.getSubsection().equals("Dry/Fat Cow")) {
                        numDry = numDry +1;
                        priceDry = (priceDry + a.getPrice())/numDry;
                    }

                    if(a.getSubsection().equals("Cull")) {
                        numCulls = numCulls +1;
                        priceCull = (priceCull + a.getPrice())/numCulls;
                    }
                    if(a.getSubsection().equals("Ewe")) {
                        numEwes= numEwes +1;
                        priceEwe = (priceEwe + a.getPrice())/numEwes;
                    }
                    if(a.getSubsection().equals("Lamb")) {
                        numLambs = numLambs +1;
                        priceLamb = (priceLamb + a.getPrice())/numLambs;
                    }
                    if(a.getSubsection().equals("Ram")) {
                        numRams = numRams +1;
                        priceRam = (priceRam + a.getPrice())/numRams;
                    }




                }
                numBullsText.setText( String.valueOf(numBulls));
                bullsPriceText.setText(new DecimalFormat("#.##").format(priceBulls));

                numBullocksText.setText(String.valueOf(numBullocks));
                bullockPriceText.setText(new DecimalFormat("#.##").format(priceBullocks));

                numCalfText.setText(String.valueOf(numCalves));
                calfPriceText.setText(new DecimalFormat("#.##").format(priceCalves));

                numCowText.setText(String.valueOf(numCow));
                cowPriceText.setText(new DecimalFormat("#.##").format(priceCow));

                numDryText.setText(String.valueOf(numDry));
                dryPriceText.setText(new DecimalFormat("#.##").format(priceDry));

                numHeiferText.setText(String.valueOf(numHeifers));
                heiferPriceText.setText(new DecimalFormat("#.##").format(priceHeifers));

                numCullsText.setText(String.valueOf(numCulls));
                cullspriceTex.setText(new DecimalFormat("#.##").format(priceCull));

                numEwesText.setText(String.valueOf(numEwes));
                ewesPriceText.setText(new DecimalFormat("#.##").format(priceEwe));

                numRamText.setText(String.valueOf(numRams));
                ramPriceText.setText(new DecimalFormat("#.##").format(priceRam));

                numLambsText.setText(String.valueOf(numLambs));
                lambPriceText.setText(new DecimalFormat("#.##").format(priceLamb));
            }




            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(ViewStatsActivity.this, "Error", Toast.LENGTH_LONG).show();
            }

        });



    }
    }
