package com.sebastianbrzustowicz.robottaskerclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class VehicleActivity extends AppCompatActivity {

    Button btn_Disconnect;
    TriStateButton triStateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        btn_Disconnect = findViewById(R.id.btn_Disconnect);
        triStateButton = findViewById(R.id.triStateButton);

        btn_Disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Change activity
                Intent intent = new Intent(VehicleActivity.this, VehicleMenuActivity.class);
                startActivity(intent);
            }
        });

        triStateButton.setOnTriStateChangeListener(new TriStateButton.OnTriStateChangeListener() {
            @Override
            public void onStateChanged(int newState) {
                Context context = getApplicationContext();
                //Toast.makeText(context, "Current mode: " + newState, Toast.LENGTH_SHORT).show();
            }
        });



    }
}