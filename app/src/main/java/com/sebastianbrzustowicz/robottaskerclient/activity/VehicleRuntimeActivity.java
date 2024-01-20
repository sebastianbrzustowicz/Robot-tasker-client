package com.sebastianbrzustowicz.robottaskerclient.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sebastianbrzustowicz.robottaskerclient.R;
import com.sebastianbrzustowicz.robottaskerclient.model.TriStateButton;
import com.sebastianbrzustowicz.robottaskerclient.service.WebSocketClientManager;

public class VehicleRuntimeActivity extends AppCompatActivity {

    Button btn_Disconnect;
    ImageButton btn_upArrow;
    TriStateButton triStateButton;
    // WebSocket Singleton
    WebSocketClientManager socketManager = WebSocketClientManager.getInstance();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_runtime);

        btn_Disconnect = findViewById(R.id.btn_Disconnect);
        btn_upArrow = findViewById(R.id.btn_upArrow);
        triStateButton = findViewById(R.id.triStateButton);

        btn_Disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                socketManager = null;

                // Change activity
                Intent intent = new Intent(VehicleRuntimeActivity.this, VehicleMenuActivity.class);
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

        btn_upArrow.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // saving x value to singleton (have to create singleton first)
                        // sending vehicleData.getFrame to websocket
                        socketManager.sendMessage("x down update");
                        //Toast.makeText(VehicleRuntimeActivity.this, "Przycisk naciśnięty", Toast.LENGTH_SHORT).show();
                        return true;

                    case MotionEvent.ACTION_UP:
                        // saving x value to singleton
                        // sending vehicleData.getFrame to websocket
                        socketManager.sendMessage("x up update");
                        //Toast.makeText(VehicleRuntimeActivity.this, "Przycisk puszczony", Toast.LENGTH_SHORT).show();
                        return true;

                    default:
                        return false;
                }
            }
        });

    }
}