package com.jhy.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etId, etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         etId = (EditText)findViewById(R.id.etId);
         etPassword = (EditText)findViewById(R.id.etPassword);


        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"Login",Toast.LENGTH_LONG).show();
                Intent in = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(in);
            }
        });

        Button btnRegister = (Button)findViewById(R.id.btnResigter);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stEmail = etId.getText().toString();
                String stPassword = etPassword.getText().toString();
                if(stEmail.isEmpty()) {
                    Toast.makeText(MainActivity.this,"Please insert Email", Toast.LENGTH_LONG).show();
                    return;
                }

                if(stPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this,"Please insert Password", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast.makeText(MainActivity.this, "Email: "+stEmail+", stPassword : "+stPassword, Toast.LENGTH_LONG).show();
            }
        });
    }
}