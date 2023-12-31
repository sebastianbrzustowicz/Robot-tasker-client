package com.sebastianbrzustowicz.robottaskerclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VehicleMenuActivity extends AppCompatActivity {

    Button btn_backToList, btn_connect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_menu);

        btn_backToList = findViewById(R.id.btn_backToList);
        btn_connect = findViewById(R.id.btn_connect);

        btn_backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Change activity
                Intent intent = new Intent(VehicleMenuActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        btn_connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Change activity
                Intent intent = new Intent(VehicleMenuActivity.this, VehicleActivity.class);
                startActivity(intent);
            }
        });

    }
}