package com.example.martstock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    EditText nameText;
    EditText emailText;
    EditText usernameText;
    EditText passwordText;
    Button registerButton;
    Button loginButton;
    String userID;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        reference = FirebaseDatabase.getInstance().getReference().child("User").push();
        mAuth = FirebaseAuth.getInstance();

        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);
        registerButton = findViewById(R.id.registerButton);
        loginButton = findViewById(R.id.loginButton1);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_LONG).show();
                } else if (email.isEmpty()) {
                    emailText.setError("Please enter your email address");
                    emailText.requestFocus();
                } else if (password.isEmpty()) {
                    passwordText.setError("Please enter your password");
                    passwordText.requestFocus();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_LONG).show();
                            } else {
                                createUser();
                                startActivity(new Intent(MainActivity.this, BuyingActivity.class));
                            }

                        }

                    }).addOnFailureListener(new OnFailureListener(){
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Insert user failed" +e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });


                }
            }

        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
    public void createUser(){
        String name = nameText.getText().toString();
        String phone = usernameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        String id = mAuth.getCurrentUser().getUid();

        userID = mAuth.getCurrentUser().getUid();

        User user = new User(name, email, phone, password, id);
        reference = FirebaseDatabase.getInstance().getReference().child("User").child(userID);


        reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "User Created", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error occured creating new user", Toast.LENGTH_LONG).show();
                }

            }

        });
    }

}