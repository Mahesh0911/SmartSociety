package com.example.smartsociety;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    public EditText username;
    public EditText password;
    public Button login;
    public Button register;
    public FirebaseAuth auth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
        username =findViewById(R.id.username);
        password=findViewById(R.id.password);
        auth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eml=username.getText().toString();
                String pass=password.getText().toString();
                if(TextUtils.isEmpty(eml) || TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginActivity.this, "Please enter password/Email.", Toast.LENGTH_SHORT).show();
                }else {
                    loggin(eml,pass);
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, registerPage.class);
                startActivity(intent);
            }
        });
    }

    private void loggin(String eml, String pass) {
        auth.signInWithEmailAndPassword(eml,pass).addOnSuccessListener(LoginActivity.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });
        auth.signInWithEmailAndPassword(eml,pass).addOnFailureListener(LoginActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                username.setText("");
                password.setText("");
                Toast.makeText(LoginActivity.this, "Invalid Credentials.", Toast.LENGTH_SHORT).show();
            }
        });
    }


}