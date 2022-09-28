package com.example.smartsociety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class parkingManagement extends AppCompatActivity {
    ListView vehicle;
    EditText name;
    EditText number;
    Button addvehicle;
//        public String arr[]={"MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000","MH AD 2000"};
    public ArrayList<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_management);
        vehicle=findViewById(R.id.vehicles);
        name=findViewById(R.id.company);
        number=findViewById(R.id.number);
        addvehicle=findViewById(R.id.addvehicle);

//        for(int i=0;i<arr.length;i++){
//            list.add(arr[i]);
//        }

        FirebaseDatabase.getInstance().getReference("Vehicles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    list.clear();
                    for(DataSnapshot s:snapshot.getChildren()){
                        Vehicles ad=s.getValue(Vehicles.class);
                        String t=(ad.getName() +" : "+ad.getNum());
                        list.add(t);
                    }
//                    ad.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        vehicleListView ad=new vehicleListView(parkingManagement.this,R.layout.activity1_parking_management,list);
        vehicle.setAdapter(ad);
        addvehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nam=name.getText().toString();
                String num=number.getText().toString();
                if(TextUtils.isEmpty(nam) || TextUtils.isEmpty(num)){
                    Toast.makeText(parkingManagement.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                }else {
                    Map<String, String> map = new HashMap<>();
                    map.put("name", nam);
                    map.put("mobile_no", num);

                    FirebaseDatabase.getInstance().getReference().child("Vehicles").push().setValue(map);
                }
            }
        });

    }
}