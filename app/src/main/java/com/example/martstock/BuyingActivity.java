package com.example.martstock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Collections;

public class BuyingActivity extends AppCompatActivity {


    DatabaseReference userRef;
    RecyclerView rcv;
    RecyclerView.Adapter adapter;
    ArrayList<Ad> ads = new ArrayList<Ad>();

    Spinner filterSpinner;
    Button filterButton;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying);



        rcv = findViewById(R.id.rcv);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(this));


        filterButton = findViewById(R.id.filterButton);
        filterSpinner = findViewById(R.id.filterSpinner);

        final String[] martList={ "All","Abbeyfeale Mart","Ardee Mart","Athenry Mart","Balla Mart","Ballina Mart","Ballinasloe Mart","Ballinrobe Mart",
                "Ballbay Mart","Ballybofey & Stranorlar Mart","Ballyjamesduff Mart","Ballymahon Mart","Ballymeana Mart","Ballymote Mart","Ballyshannon Mart",
                "Baltinglass Mart","Birr Mart","Blesington Mart","Borris Mart","Cahir Mart","Cardonagh Mart","Carlow Mart",
                "Carnaross Mart","Carnew Mart","Carrick on Suir Mart","Cashel Mart","Carrigallen Mart","Castleblayney Mart","Castleisland Mart",
                "Castlerea Mart","Cavan Mart","Clifden Mart","Clogher Mart","Devlin Mart","Dingle Mart","Downpatrick Mart",
                "Dowra Mart","Drumshanbo Mart","Dungrarven Mart","Elphin Mart","Ennis Mart","Enniscorthy Mart","Fermoy Mart",
                "Gort Mart","Gortin Mart","Granard Mart","Headford Mart","Hilltown Mart","Iveragh Mart","Kanturk Mart",
                "Kenmare Mart","Kilcullen Mart","Kilkenny Mart","Kilmallock Mart","Kilera Mart","Kilrush Mart","Kingscourt Mart",
                "Lisahally Mart","Listowel Mart","Loughrea Mart","Maam Cross Mart","Manorhamilton Mart","Markethil Mart","Mid Kerry Mart",
                "Milford Mart","Mohill Mart","Mountbellew Mart","Mountrath Mart","Nenagh Mart","Newport Mart","Newross Mart",
                "Newtownstewart Mart","Omagh Mart","Raphoe Mart","Roscommon Mart","Roscrea Mart","Thurles Mart","Tuam Mart",
                "Tullamore Mart","Tullow Mart"};


        filterSpinner.setAdapter(new ArrayAdapter<>(BuyingActivity.this, android.R.layout
                .simple_spinner_dropdown_item, martList));

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = filterSpinner.getSelectedItem().toString();
                userRef =  FirebaseDatabase.getInstance().getReference("Ad");

                userRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (final DataSnapshot s : snapshot.getChildren()) {
                            final Ad a = s.getValue(Ad.class);

                            ads.clear();
                            if (result.equals(a.getMart())) {
                                ads.add(a);
                            }
                            adapter = new MyAdapter(ads);
                            rcv.setAdapter(adapter);
                            adapter.notifyItemInserted(ads.size() - 1);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(BuyingActivity.this, "Error", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });


        userRef =  FirebaseDatabase.getInstance().getReference("Ad");


        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ads.clear();
                for (DataSnapshot s : dataSnapshot.getChildren()) {

                    Ad a = s.getValue(Ad.class);


                    ads.add(a);
                    Collections.reverse(ads);
                    adapter = new MyAdapter(ads);
                    rcv.setAdapter(adapter);
                    adapter.notifyItemInserted(ads.size() - 1);

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(BuyingActivity.this, "Error", Toast.LENGTH_LONG).show();
            }
        });



    }



    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scanning Code");
        integrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents()!=null){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(result.getContents());
                builder.setTitle("Scanning Result");
                builder.setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scanCode();
                    }
                }).setNegativeButton("Finish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else{
                Toast.makeText(this, "No Results", Toast.LENGTH_LONG).show();
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item1:
                Intent i = new Intent(BuyingActivity.this, ViewReportActivity.class);
                startActivity(i);
                return true;
            case R.id.item2:
                Intent ii = new Intent(BuyingActivity.this, ManageMyAccount.class);
                startActivity(ii);
                return true;
            case R.id.item3:
                scanCode();
                return true;
            case R.id.item4:
                Intent iii = new Intent(BuyingActivity.this, PlaceAd.class);
                startActivity(iii);
                return true;
            case R.id.item5:
                Intent iv = new Intent(BuyingActivity.this, QRCode.class);
                startActivity(iv);
                return true;
            case R.id.item6:
                Intent v = new Intent(BuyingActivity.this, LiveMartsActivity.class);
                startActivity(v);
                return true;
            case R.id.item7:
                Intent vi = new Intent(BuyingActivity.this, MessagesActivity.class);
                startActivity(vi);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }


}