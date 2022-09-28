package com.example.smartsociety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class societyDashboard extends AppCompatActivity {
    ListView members;
//    public String arr[]={"MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000"};
    public ArrayList<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society_dashboard);
        members=findViewById(R.id.membrs);
//        for(int i=0;i<arr.length;i++){
//            list.add(arr[i]);
//        }
        FirebaseDatabase.getInstance().getReference("Family").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    list.clear();
                    for(DataSnapshot s:snapshot.getChildren()){
                        Vehicles ad=s.getValue(Vehicles.class);
                        String t=ad.getName() ;
                        list.add(t);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        vehicleListView ad=new vehicleListView(societyDashboard.this,R.layout.activity1_parking_management,list);
        members.setAdapter(ad);
    }
}