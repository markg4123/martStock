package com.example.martstock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class PlaceAd extends AppCompatActivity {

    Button placeAdButton,martText;
    EditText adTitleText, priceText, ageText;
    Spinner spinner,spinner2, breedSpinner, subsectionspinner;
    ImageView addPhotos;
    EditText descText, datetext;
    ArrayList<Ad> ads = new ArrayList<Ad>();
    DatabaseReference reference;
    FirebaseAuth mAuth;
    RecyclerView.Adapter adapter;
    StorageReference mStorageRef;
    private Uri imageUri;
    Uri downloadUri;

    String val,val2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_ad);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Ad").push();
        mStorageRef = FirebaseStorage.getInstance().getReference();



        addPhotos = findViewById(R.id.addPhotos);
        placeAdButton = findViewById(R.id.placeAdButton);
        adTitleText = findViewById(R.id.adTitleText);
        priceText = findViewById(R.id.priceText);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        martText = findViewById(R.id.martText);
        breedSpinner = findViewById(R.id.breedSpinner);
        ageText = findViewById(R.id.ageText);
        descText = findViewById(R.id.descText);
        datetext = findViewById(R.id.dateText);
        subsectionspinner = findViewById(R.id.subsectionspinner);


        datetext.setInputType(InputType.TYPE_NULL);

        final String[] sectionList = { "Cattle","Sheep"};
        final String[] ageList = {"Months", "Years"};
        final String[] sheepBreed ={"Blackfaced Mountain","Bluefaced Leicester","Charolais","Cheviot",
                "Galway"," Hampshire","Sufflok","Texel","Vendeen","Other"};
        final String[] cattleBreed ={"Aberdeen Angus","Aubrac","Ayrshire","Belgian Blue",
                "Blonde","Charolais","Dexter","Friesian","Hereford","Jersey","Limousin",
                "Montbelliarde","Norwegian Red","Parthenaise","Saler","Shorthorn","Simmental","Other"};
        final String[] subListCattle = {"Bull","Bullock", "Cow", "Calf","Dry/Fat Cow", "Heifer"};
        final String[] subListSheep = {"Cull","Ewe","Lamb","Ram" };

        spinner.setAdapter(new ArrayAdapter<>(PlaceAd.this, android.R.layout
                .simple_spinner_dropdown_item, sectionList));
        spinner2.setAdapter(new ArrayAdapter<>(PlaceAd.this, android.R.layout
                .simple_spinner_dropdown_item, ageList));



        val = spinner.getSelectedItem().toString();

        if(val.equals("Sheep")){
            breedSpinner.setAdapter(new ArrayAdapter<>(PlaceAd.this, android.R.layout
                    .simple_spinner_dropdown_item, sheepBreed));
        }
        if (val.equals("Cattle")){
            breedSpinner.setAdapter(new ArrayAdapter<>(PlaceAd.this, android.R.layout
                    .simple_spinner_dropdown_item, cattleBreed));

        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 val2 = spinner.getSelectedItem().toString();
                if(val2.equals("Sheep")){
                    breedSpinner.setAdapter(new ArrayAdapter<>(PlaceAd.this, android.R.layout
                            .simple_spinner_dropdown_item, sheepBreed));
                    subsectionspinner.setAdapter(new ArrayAdapter<>(PlaceAd.this, android.R.layout
                            .simple_spinner_dropdown_item, subListSheep));

                }
                if (val2.equals("Cattle")){
                    breedSpinner.setAdapter(new ArrayAdapter<>(PlaceAd.this, android.R.layout
                            .simple_spinner_dropdown_item, cattleBreed));
                    subsectionspinner.setAdapter(new ArrayAdapter<>(PlaceAd.this, android.R.layout
                            .simple_spinner_dropdown_item, subListCattle));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        placeAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String title = adTitleText.getText().toString();
                int price = Integer.parseInt(String.valueOf(priceText.getText()));
                if(priceText.toString().equals(""))
                    price = 0;
                String section = spinner.getSelectedItem().toString();
                Bundle extras = getIntent().getExtras();
                String mart = extras.getString("martName");
                String breed = breedSpinner.getSelectedItem().toString();
                String age = ageText.getText().toString() + " " + spinner2.getSelectedItem().toString();
                String desc = descText.getText().toString();
                String id = mAuth.getCurrentUser().getUid();
                String uri = downloadUri.toString();
                String date = datetext.getText().toString();
                String key = reference.getKey();
                String sub = subsectionspinner.getSelectedItem().toString();


                if (title.equals("") || section.equals("")||age.equals("")||desc.equals("")||uri.equals("")||
                date.equals("")||sub.equals("")) {
                    Toast.makeText(PlaceAd.this, "Please fill in all fields!", Toast.LENGTH_LONG).show();
                } else {
                    Ad ad = new Ad(title, price, section, mart, breed, age, desc, id, uri, date, key, sub);

                    ads.add(ad);


                    reference.setValue(ad).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(PlaceAd.this, "Your Ad Has Been Created", Toast.LENGTH_LONG).show();
                                adapter = new MyAdapter(ads);
                                Intent i = new Intent(PlaceAd.this, ViewAdsActivity.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(PlaceAd.this, "Your Ad Has Not Been Created", Toast.LENGTH_LONG).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PlaceAd.this, "Insert ad failed" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });


                    ((EditText) findViewById(R.id.adTitleText)).setText("");
                    ((EditText) findViewById(R.id.priceText)).setText("");
                    ((EditText) findViewById(R.id.ageText)).setText("");
                    ((EditText) findViewById(R.id.descText)).setText("");

                }
            }


        });
        martText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlaceAd.this, MapsActivity.class);
                startActivity(intent);
            }
        });


        datetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(datetext);
            }
        });

        addPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhotos();
            }


        }); }
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
            addPhotos.setImageURI(imageUri);
            uploadPhoto();
        }
    }

    private void uploadPhoto() {
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

                } else {
                    Toast.makeText(PlaceAd.this, "upload image failed", Toast.LENGTH_LONG).show();
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
        new DatePickerDialog(PlaceAd.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.statsmenu, menu);
        return true;

    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item1:
                Intent i = new Intent(PlaceAd.this, ViewStatsActivity.class);
                startActivity(i);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


}