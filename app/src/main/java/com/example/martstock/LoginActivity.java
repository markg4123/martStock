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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText emailText;
    EditText passwordText;
    Button loginButton1;
    FirebaseAuth mAuth;
    String email, password;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        mAuth = FirebaseAuth.getInstance();
        passwordText = findViewById(R.id.passwordText);
        emailText = findViewById(R.id.emailText);
        loginButton1 = findViewById(R.id.loginButton1);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mUser = mAuth.getCurrentUser();
                if (mUser == null) {
                    Toast.makeText(LoginActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, BuyingActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginActivity.this, "Please Login", Toast.LENGTH_SHORT).show();
                }
            }

        };
        loginButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                     email = emailText.getText().toString();
                     password = passwordText.getText().toString();

                    if (email.isEmpty() && password.isEmpty())
                    Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_LONG).show();
                    else if (email.isEmpty()) {
                        emailText.setError("Please enter your email address");
                        emailText.requestFocus();
                    } else if (password.isEmpty()) {
                        passwordText.setError("Please enter your password");
                        passwordText.requestFocus();
                    } else {
                        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Login Error Please Try Again", Toast.LENGTH_LONG).show();
                                } else {
                                    Intent intent1 = new Intent(LoginActivity.this, BuyingActivity.class);
                                    startActivity(intent1);
                                }
                            }
                        });


                    }
            }
        });

    }

        protected void onStart() {
            super.onStart();
            mAuth.addAuthStateListener(mAuthStateListener);
        }

    }