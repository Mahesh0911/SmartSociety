package com.example.smartsociety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.security.identity.CipherSuiteNotSupportedException;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class profileActivity extends AppCompatActivity {
TextView nm;
TextView emal;
TextView mno;
TextView flatno;
TextView status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nm=findViewById(R.id.nm);
        emal=findViewById(R.id.emal);
        mno=findViewById(R.id.mno);
        flatno=findViewById(R.id.flatno);
        status=findViewById(R.id.status);

        setContentView(R.layout.activity_profile);
        Intent intent=getIntent();
        String eml=intent.getStringExtra(Dashboard.extraName);
//nm.setText(eml);
//        DatabaseReference reference=;
//        Query checUser=reference.orderByChild("email").equalTo(eml);

        FirebaseDatabase.getInstance().getReference("Gokuldham_Society").equalTo(eml).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    UserData  u=snapshot.getValue(UserData.class);
                    nm.setText("Name : "+ u.getName());
                    emal.setText("Email : "+ u.getMobile_no());
                    mno.setText("Mobile No : "+ u.getMobile_no());
                    flatno.setText("Flat No : "+ u.getFlat_no());
                    status.setText("Relation : "+ u.getRelation_status());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}