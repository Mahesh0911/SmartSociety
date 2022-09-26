package com.example.smartsociety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registerPage extends AppCompatActivity{
    public EditText email;
    public EditText password;
    public Button submit;
    public FirebaseAuth firebaseAuth;
//    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        submit=findViewById(R.id.submit) ;
        email=findViewById(R.id.email1) ;
        password=findViewById(R.id.Password);
        submit=findViewById(R.id.submit);
        firebaseAuth=FirebaseAuth.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eml=email.getText().toString();
                String pass=password.getText().toString();
                if(TextUtils.isEmpty(eml) || TextUtils.isEmpty(pass)){
                    Toast.makeText(registerPage.this, "Something Incorrect", Toast.LENGTH_SHORT).show();
                }else{
                    regist(eml,pass);
                }
            }
        });
    }

    private void regist(String eml, String pass) {
        firebaseAuth.createUserWithEmailAndPassword(eml,pass).addOnCompleteListener(registerPage.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(registerPage.this, "success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(registerPage.this,LoginActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(registerPage.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
