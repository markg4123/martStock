package com.example.martstock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class QRCode extends AppCompatActivity {

    EditText animalText, ageText, breedText, weightText;
    Button button, saveButton;
    ImageView image;

    OutputStream outputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_code);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        animalText = findViewById(R.id.animalText);
        ageText = findViewById(R.id.ageText);
        breedText = findViewById(R.id.breedText);
        weightText = findViewById(R.id.weightText);
        button = findViewById(R.id.button);
        saveButton = findViewById(R.id.saveButton);
        image = findViewById(R.id.image);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String animal = "Animal: " +animalText.getText().toString().trim() +"\n"
                        + "Age: " + ageText.getText().toString().trim() + "\n"
                        + "Breed: " + breedText.getText().toString().trim() + "\n"
                        + "Weight: " + weightText.getText().toString().trim() + "\n"
                        + "Animal Number: "+  UUID.randomUUID(). toString();


                MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    BitMatrix matrix = writer.encode(animal, BarcodeFormat.QR_CODE,
                            350, 350);
                    BarcodeEncoder encoder = new BarcodeEncoder();

                    Bitmap bitmap = encoder.createBitmap(matrix);

                    image.setImageBitmap(bitmap);

                    InputMethodManager manager = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE);

                    manager.hideSoftInputFromWindow(animalText.getApplicationWindowToken(),0);

                } catch (WriterException e) {
                    e.printStackTrace();
                }

            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable draw = (BitmapDrawable) image.getDrawable();
                Bitmap bitmap = draw.getBitmap();

                FileOutputStream outStream = null;
                File sdCard = Environment.getExternalStorageDirectory();
                File dir = new File(sdCard.getAbsolutePath() + "/SaveImages");
                dir.mkdirs();
                String fileName = String.format("%d.jpg", System.currentTimeMillis());
                File outFile = new File(dir, fileName);
                try {
                    outStream = new FileOutputStream(outFile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
                try {
                    outStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }



}

