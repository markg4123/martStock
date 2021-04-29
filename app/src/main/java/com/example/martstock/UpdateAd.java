package com.example.martstock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class UpdateAd extends AppCompatActivity {
    Button updateButton;
    EditText adTitleText2, priceText2, ageText2, descText2, datetext2, martText2;
    Spinner ageSpinner, sectionSpinner, breedSpinner2;
    ImageView addPhotos2;
    String title, price, age, breed, desc, date, mart, key,key1;
    String val, val2;
    Uri uri;
    FirebaseAuth mAuth;
    private Uri imageUri;
    StorageReference mStorageRef;
    DatabaseReference fireDB;
    Uri downloadUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ad);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        addPhotos2 = findViewById(R.id.addPhotos2);
        updateButton = findViewById(R.id.updateButton);
        adTitleText2 = findViewById(R.id.adTitleText2);
        priceText2 = findViewById(R.id.priceText2);
        ageSpinner = findViewById(R.id.ageSpinner);
        sectionSpinner = findViewById(R.id.sectionSpinner);
        martText2 = findViewById(R.id.martText2);
        breedSpinner2 = findViewById(R.id.breedSpinner2);
        ageText2 = findViewById(R.id.ageText2);
        descText2 = findViewById(R.id.descText2);
        datetext2 = findViewById(R.id.dateText2);

        mAuth = FirebaseAuth.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        final String[] sectionList = {"Cattle", "Sheep"};
        final String[] ageList = {"Months", "Years"};
        final String[] sheepBreed = {"Blackfaced Mountain", "Bluefaced Leicester", "Charolais", "Cheviot",
                "Galway", " Hampshire", "Sufflok", "Texel", "Vendeen", "Other"};

        final String[] cattleBreed = {"Aberdeen Angus", "Aubrac", "Ayrshire", "Belgian Blue",
                "Blonde", "Charolais", "Dexter", "Friesian", "Hereford", "Jersey", "Limousin",
                "Montbelliarde", "Norwegian Red", "Parthenaise", "Saler", "Shorthorn", "Simmental", "Other"};

        sectionSpinner.setAdapter(new ArrayAdapter<>(UpdateAd.this, android.R.layout
                .simple_spinner_dropdown_item, sectionList));
        ageSpinner.setAdapter(new ArrayAdapter<>(UpdateAd.this, android.R.layout
                .simple_spinner_dropdown_item, ageList));


        val = sectionSpinner.getSelectedItem().toString();

        if (val.equals("Sheep")) {
            breedSpinner2.setAdapter(new ArrayAdapter<>(UpdateAd.this, android.R.layout
                    .simple_spinner_dropdown_item, sheepBreed));
        }
        if (val.equals("Cattle")) {
            breedSpinner2.setAdapter(new ArrayAdapter<>(UpdateAd.this, android.R.layout
                    .simple_spinner_dropdown_item, cattleBreed));

        }
        sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                val2 = sectionSpinner.getSelectedItem().toString();
                if (val2.equals("Sheep")) {
                    breedSpinner2.setAdapter(new ArrayAdapter<>(UpdateAd.this, android.R.layout
                            .simple_spinner_dropdown_item, sheepBreed));
                }
                if (val2.equals("Cattle")) {
                    breedSpinner2.setAdapter(new ArrayAdapter<>(UpdateAd.this, android.R.layout
                            .simple_spinner_dropdown_item, cattleBreed));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        title = getIntent().getExtras().getString("title");
        price = getIntent().getExtras().getString("price");
        age = getIntent().getExtras().getString("age");
        breed = getIntent().getExtras().getString("breed");
        desc = getIntent().getExtras().getString("desc");
        date = getIntent().getExtras().getString("date");
        uri = getIntent().getExtras().getParcelable("uri");
        mart = getIntent().getExtras().getString("mart");
        key = getIntent().getExtras().getString("key");

        downloadUri = getIntent().getExtras().getParcelable("uri");

        adTitleText2.setText(title);
        priceText2.setText(price);
        ageText2.setText(age);
        descText2.setText(desc);
        datetext2.setText(date);
        martText2.setText(mart);


        Picasso.get().load(uri).into(addPhotos2);

        datetext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(datetext2);
            }
        });


        //update an ad
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String title = adTitleText2.getText().toString();
                final int price = Integer.parseInt(String.valueOf(priceText2.getText()));
                final String section = sectionSpinner.getSelectedItem().toString();
                final String breed = breedSpinner2.getSelectedItem().toString();
                final String mart = martText2.getText().toString();
                final String age = ageText2.getText().toString() + " " + ageSpinner.getSelectedItem().toString();
                final String desc = descText2.getText().toString();
                final String id = mAuth.getCurrentUser().getUid();
                final String date = datetext2.getText().toString();
                final String uri = downloadUri.toString();


                fireDB = FirebaseDatabase.getInstance().getReference("Ad");

                fireDB.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for(DataSnapshot userSnapshot : snapshot.getChildren()) {
                            Ad ad = userSnapshot.getValue(Ad.class);

                            key1 = userSnapshot.getKey();

                            if(key.equals(key1)){


                                fireDB.child(key).child("adTitle").setValue(title);
                                fireDB.child(key).child("age").setValue(age);
                                fireDB.child(key).child("breed").setValue(breed);
                                fireDB.child(key).child("description").setValue(desc);
                                fireDB.child(key).child("id").setValue(id);
                                fireDB.child(key).child("mart").setValue(mart);
                                fireDB.child(key).child("price").setValue(price);
                                fireDB.child(key).child("section").setValue(section);
                                fireDB.child(key).child("date").setValue(date);
                                fireDB.child(key).child("url").setValue(uri);

                                Toast.makeText(UpdateAd.this, "Ad Updated!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(UpdateAd.this, ViewAdsActivity.class);
                                startActivity(i);
                            }

                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

        addPhotos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                choosePhotos();
            }
        });
    }

    private void choosePhotos() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(i,1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null&& data.getData()!=null){
            imageUri = data.getData();
            addPhotos2.setImageURI(imageUri);
            uploadPhoto();
        }
    }

    private void uploadPhoto(){
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("uploading Image...");
        pd.show();
        final String randomKey = UUID.randomUUID().toString();
        final StorageReference riversRef = mStorageRef.child("images/" + randomKey);

        riversRef.putFile(imageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return riversRef.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    downloadUri = task.getResult();
                    Log.i("url", downloadUri.toString());
                }
                else {
                    Toast.makeText(UpdateAd.this, "upload image failed", Toast.LENGTH_LONG).show();
                }
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
        new DatePickerDialog(UpdateAd.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

}