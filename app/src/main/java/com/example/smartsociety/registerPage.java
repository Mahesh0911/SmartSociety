package com.example.smartsociety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class registerPage extends AppCompatActivity{
    public EditText name;
    public EditText phone;
    public EditText flatNo;
    public EditText relationStatus;
    public EditText email;
    public EditText password;
    public Button submit;
    public FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.mobileno);
        flatNo=findViewById(R.id.flatNo);
        relationStatus=findViewById(R.id.relationStatus);


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
                String nam=name.getText().toString();
                String mno=phone.getText().toString();
                String flat=flatNo.getText().toString();
                String relStatus=relationStatus.getText().toString();

                if(TextUtils.isEmpty(eml) || TextUtils.isEmpty(pass)|| TextUtils.isEmpty(nam)|| TextUtils.isEmpty(mno)|| TextUtils.isEmpty(flat)|| TextUtils.isEmpty(relStatus)){
                    Toast.makeText(registerPage.this, "Something Incorrect", Toast.LENGTH_SHORT).show();
                }else{
                    regist(eml,pass,nam,mno,flat,relStatus);
                }
            }
        });
    }

    private void regist(String eml, String pass, String nam, String mno, String flat,String relStatus) {
        Map<String, String> map = new HashMap<>();
        map.put("name", nam);
        map.put("mobile_no",mno);
        map.put("email", eml);
        map.put("flat_no", flat);
        map.put("relation_status",relStatus);


        firebaseAuth.createUserWithEmailAndPassword(eml,pass).addOnCompleteListener(registerPage.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Map<String, String> arr = new HashMap<>();
                    arr.put("name",nam);
                    arr.put("mobile_no",mno);
                    FirebaseDatabase.getInstance().getReference().child("Gokuldham_Society").push().setValue(map);
                    FirebaseDatabase.getInstance().getReference().child("Vehicles").push().setValue(arr);
                    FirebaseDatabase.getInstance().getReference().child("Family").push().setValue(arr);

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
