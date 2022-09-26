package com.example.smartsociety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class Dashboard extends AppCompatActivity {
    public Button parking;
    public Button vehiclebtn;
    public Button alertbtn;
    public Button societybtn;
    public Switch switchTheme;


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        parking=findViewById(R.id.parkingbtn);
        vehiclebtn=findViewById(R.id.vehiclebtn);
        alertbtn=findViewById(R.id.alertbtn);
        societybtn=findViewById(R.id.societybtn);

        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.navigation_view);

        actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.notificationSetting:
                        Toast.makeText(Dashboard.this, "Notification Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.changePass:
                        Toast.makeText(Dashboard.this, "Change Password", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.uiTheme:
                        switchTheme=findViewById(R.id.chTheme);
                        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                if(b){
                                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                                }else{
                                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                                }
                        Toast.makeText(Dashboard.this, "Theme Changed", Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case R.id.logout:
                        Toast.makeText(Dashboard.this, "Logged Out", Toast.LENGTH_SHORT).show();
                        final SharedPreferences sharedPreferences=getSharedPreferences("Data", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent=new Intent(Dashboard.this,LoginActivity.class);
                        startActivity(intent);

                        break;
                }
                return true;
            }
        });


        vehiclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Dashboard.this,parkingManagement.class);
                startActivity(intent);
            }
        });
        alertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Dashboard.this,emergencyAlerts.class);
                startActivity(intent);
            }
        });

        parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Dashboard.this,parkingQuery.class);
                startActivity(intent);
            }
        });

        societybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Dashboard.this,societyDashboard.class);
                startActivity(intent);
            }
        });
    }

}