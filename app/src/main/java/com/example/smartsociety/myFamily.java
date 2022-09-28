package com.example.smartsociety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class myFamily extends AppCompatActivity {
ListView fami;
ArrayList<String> arr=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_family);
        fami=findViewById(R.id.viewOfFamily);




        vehicleListView ad= (vehicleListView) new vehicleListView(myFamily.this,R.layout.activity1_parking_management,arr);
        fami.setAdapter(ad);
    }
}