package com.jhy.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    EditText etEmail;
    EditText etPassword;
    String stEmail;
    String stPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);

        mAuth = FirebaseAuth.getInstance();
        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "onAuthStateChanged: signed in: " + user.getUid());
                } else {
                    Log.d(TAG, "onAuthStateChanged: signed out");
                }
            }
        };

        Button btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stEmail = etEmail.getText().toString();
                stPassword = etPassword.getText().toString();
                //Toast.makeText(MainActivity.this, stEmail + ", " + stPassword, Toast.LENGTH_LONG).show();
                registerUser(stEmail, stPassword);
            }
        });

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stEmail = etEmail.getText().toString();
                stPassword = etPassword.getText().toString();
                userLogin(stEmail, stPassword);


            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListner);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListner != null) {
            mAuth.removeAuthStateListener(mAuthListner);
        }
    }

    public void registerUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Auth faild", Toast.LENGTH_LONG).show();
                } else {
                    Log.d(TAG, "onComplete: " + task.isSuccessful());
                    Toast.makeText(MainActivity.this, "👍👍👍 Auth success", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void userLogin(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                        Log.d(TAG, "\n\n🌈🌈🌈 signInWithCustomToken:success");
//                        Intent in = new Intent(MainActivity.this, ChatActivity.class);
//                        startActivity(in);
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            //updateUI(user);
//                        }
                        if (!task.isSuccessful()) {
                            // If sign in fails, display a message to the user.
                            Log.d(TAG, "signInWithEmailAndPassword:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        } else {
                            Log.d(TAG, "\n\n🌈🌈🌈 signInWithCustomToken:success");
                            Intent in = new Intent(MainActivity.this, ChatActivity.class);
                            startActivity(in);
                        }
                    }
                });
    }
}