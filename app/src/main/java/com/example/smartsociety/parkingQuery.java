package com.example.smartsociety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class parkingQuery extends AppCompatActivity {
    public Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_query);
        submit=findViewById(R.id.submitQuery);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(parkingQuery.this,Dashboard.class);
                startActivity(intent);
            }
        });

    }
}