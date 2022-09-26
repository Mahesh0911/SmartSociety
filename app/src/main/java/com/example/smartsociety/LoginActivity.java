package com.example.smartsociety;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    public EditText username;
    public EditText password;
    public Button login;
    public Button register;
    public Button forgotpass;
    public FirebaseAuth auth;
    public SharedPreferences sharedPreferences;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=findViewById(R.id.login);
        forgotpass=findViewById(R.id.forgotpass);
        register=findViewById(R.id.register);
        username =findViewById(R.id.username);
        password=findViewById(R.id.password);
        auth=FirebaseAuth.getInstance();

        sharedPreferences=getSharedPreferences("Data", Context.MODE_PRIVATE);

        String type =sharedPreferences.getString("Email","");
        if(type.isEmpty()){
            Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent=new Intent(LoginActivity.this,Dashboard.class);
            startActivity(intent);
        }

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

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecoverPasswordDialog();
            }
        });
    }

    private void loggin(String eml, String pass) {
        auth.signInWithEmailAndPassword(eml, pass).addOnSuccessListener(LoginActivity.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Email", eml);
                editor.putString("Password", pass);
                editor.commit();
                Toast.makeText(LoginActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });
        auth.signInWithEmailAndPassword(eml, pass).addOnFailureListener(LoginActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                username.setText("");
                password.setText("");
                Toast.makeText(LoginActivity.this, "Invalid Credentials.", Toast.LENGTH_SHORT).show();
            }
        });
    }
        private void showRecoverPasswordDialog() {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Recover Password");
            LinearLayout linearLayout=new LinearLayout(this);
            final EditText emailet= new EditText(this);

            // write the email using which you registered
            emailet.setText("Email");
            emailet.setMinEms(16);
            emailet.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            linearLayout.addView(emailet);
            linearLayout.setPadding(10,10,10,10);
            builder.setView(linearLayout);

            // Click on Recover and a email will be sent to your registered email id
            builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String email=emailet.getText().toString().trim();
                    beginRecovery(email);
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }
    private void beginRecovery(String email) {
        ProgressDialog loadingBar = new ProgressDialog(this);
        loadingBar.setMessage("Sending Email....");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        // calling sendPasswordResetEmail
        // open your email and write the new
        // password and then you can login
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                loadingBar.dismiss();
                if(task.isSuccessful())
                {
                    // if isSuccessful then done message will be shown
                    // and you can change the password
                    Toast.makeText(LoginActivity.this,"Done sent",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(LoginActivity.this,"Error Occurred",Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
//                    loadingBar.dismiss();
                Toast.makeText(LoginActivity.this,"Error Failed",Toast.LENGTH_LONG).show();
            }
        });
    }


}