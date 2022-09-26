package com.example.smartsociety;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class vehicleListView extends ArrayAdapter<String> {
    public  String[] arr=null;
    public vehicleListView(@NonNull Context context, int resource,@NonNull String[] arr) {
        super(context, resource, arr);
        this.arr=arr;
    }
    @Nullable
    @Override
    public String getItem(int position) {
        return arr[position];
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(getContext()).inflate(R.layout.activity1_parking_management,parent,false);
        TextView t=convertView.findViewById(R.id.vehicleNo);
        t.setText(getItem(position));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Clicked On "+position, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
