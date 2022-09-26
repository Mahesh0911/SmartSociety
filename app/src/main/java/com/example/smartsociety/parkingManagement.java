package com.example.smartsociety;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class parkingManagement extends AppCompatActivity {
    ListView vehicle;
    public String list[]={"MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_management);
        vehicle=findViewById(R.id.vehicles);
        vehicleListView ad= (vehicleListView) new vehicleListView(parkingManagement.this,R.layout.activity1_parking_management,list);
        vehicle.setAdapter(ad);

    }
}