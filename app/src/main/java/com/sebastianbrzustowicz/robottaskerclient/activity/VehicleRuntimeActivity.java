package com.sebastianbrzustowicz.robottaskerclient.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.sebastianbrzustowicz.robottaskerclient.R;
import com.sebastianbrzustowicz.robottaskerclient.model.TriStateButton;
import com.sebastianbrzustowicz.robottaskerclient.service.VehicleData;
import com.sebastianbrzustowicz.robottaskerclient.service.WebSocketClientManager;

public class VehicleRuntimeActivity extends AppCompatActivity {

    Button btn_Disconnect;
    // right pad
    ImageButton btn_x_up, btn_x_down, btn_y_left, btn_y_right;
    // left pad
    ImageButton btn_altitude_up, btn_altitude_down, btn_yaw_left, btn_yaw_right;
    // functional buttons
    ImageButton btn_takeoff_landing, btn_clamp, btn_camTrig, btn_camPitch, btn_camTog;
    TriStateButton triStateButton;
    // WebSocket Singleton
    WebSocketClientManager socketManager = WebSocketClientManager.getInstance();
    // Vehicle data to transfer Singleton
    VehicleData vehicleData = VehicleData.getInstance();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_runtime);

        btn_Disconnect = findViewById(R.id.btn_Disconnect);
        btn_x_up = findViewById(R.id.btn_x_up);
        btn_x_down = findViewById(R.id.btn_x_down);
        btn_y_left = findViewById(R.id.btn_y_left);
        btn_y_right = findViewById(R.id.btn_y_right);
        btn_altitude_up = findViewById(R.id.btn_altitude_up);
        btn_altitude_down = findViewById(R.id.btn_altitude_down);
        btn_yaw_left = findViewById(R.id.btn_yaw_left);
        btn_yaw_right = findViewById(R.id.btn_yaw_right);
        btn_takeoff_landing = findViewById(R.id.btn_takeoff_landing);
        btn_clamp = findViewById(R.id.btn_clamp);
        btn_camTrig = findViewById(R.id.btn_camTrig);
        btn_camPitch = findViewById(R.id.btn_camPitch);
        btn_camTog = findViewById(R.id.btn_camTog);

        triStateButton = findViewById(R.id.triStateButton);

        btn_Disconnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vehicleData.resetValues();
                socketManager = null;

                // Change activity
                Intent intent = new Intent(VehicleRuntimeActivity.this, VehicleMenuActivity.class);
                startActivity(intent);
            }
        });

        triStateButton.setOnTriStateChangeListener(new TriStateButton.OnTriStateChangeListener() {
            @Override
            public void onStateChanged(int newState) {
                // newState values - 0, 1, 2 (slow, normal, fast)
                // saving mode value to singleton
                vehicleData.setMode(newState);
                // sending actual frame to websocket
                socketManager.sendMessage(vehicleData.getFrame());
            }
        });

        btn_x_up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // saving x value to singleton
                        vehicleData.setX(1);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        //Toast.makeText(VehicleRuntimeActivity.this, "X down", Toast.LENGTH_SHORT).show();
                        return true;

                    case MotionEvent.ACTION_UP:
                        // saving x value to singleton
                        vehicleData.setX(0);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        //Toast.makeText(VehicleRuntimeActivity.this, "X up", Toast.LENGTH_SHORT).show();
                        return true;

                    default:
                        return false;
                }
            }
        });

        btn_x_down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // saving x value to singleton
                        vehicleData.setX(-1);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    case MotionEvent.ACTION_UP:
                        // saving x value to singleton
                        vehicleData.setX(0);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    default:
                        return false;
                }
            }
        });

        btn_y_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // saving y value to singleton
                        vehicleData.setY(-1);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    case MotionEvent.ACTION_UP:
                        // saving y value to singleton
                        vehicleData.setY(0);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    default:
                        return false;
                }
            }
        });

        btn_y_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // saving y value to singleton
                        vehicleData.setY(1);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    case MotionEvent.ACTION_UP:
                        // saving y value to singleton
                        vehicleData.setY(0);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    default:
                        return false;
                }
            }
        });

        btn_altitude_up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // saving alt value to singleton
                        vehicleData.setAlt(1);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    case MotionEvent.ACTION_UP:
                        // saving alt value to singleton
                        vehicleData.setAlt(0);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    default:
                        return false;
                }
            }
        });

        btn_altitude_down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // saving alt value to singleton
                        vehicleData.setAlt(-1);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    case MotionEvent.ACTION_UP:
                        // saving alt value to singleton
                        vehicleData.setAlt(0);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    default:
                        return false;
                }
            }
        });

        btn_yaw_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // saving alt value to singleton
                        vehicleData.setYaw(-1);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    case MotionEvent.ACTION_UP:
                        // saving alt value to singleton
                        vehicleData.setYaw(0);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    default:
                        return false;
                }
            }
        });

        btn_yaw_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // saving yaw value to singleton
                        vehicleData.setYaw(1);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    case MotionEvent.ACTION_UP:
                        // saving yaw value to singleton
                        vehicleData.setYaw(0);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    default:
                        return false;
                }
            }
        });

        btn_takeoff_landing.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // saving vtol value to singleton
                        vehicleData.setVtol(1);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    case MotionEvent.ACTION_UP:
                        // saving vtol value to singleton
                        vehicleData.setVtol(0);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    default:
                        return false;
                }
            }
        });

        btn_clamp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // saving clamp value to singleton
                        vehicleData.setClamp(true);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    case MotionEvent.ACTION_UP:
                        // saving clamp value to singleton
                        vehicleData.setClamp(false);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    default:
                        return false;
                }
            }
        });

        btn_camTrig.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // saving camTrig value to singleton
                        vehicleData.setCamTrig(true);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    case MotionEvent.ACTION_UP:
                        // saving camTrig value to singleton
                        vehicleData.setCamTrig(false);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    default:
                        return false;
                }
            }
        });

        btn_camPitch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // saving camPitch value to singleton
                        vehicleData.setCamPitch(1);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    case MotionEvent.ACTION_UP:
                        // saving camPitch value to singleton
                        vehicleData.setCamPitch(0);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    default:
                        return false;
                }
            }
        });

        btn_camTog.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // saving camTog value to singleton
                        vehicleData.setCamTog(true);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    case MotionEvent.ACTION_UP:
                        // saving camTog value to singleton
                        vehicleData.setCamTog(false);
                        // sending actual frame to websocket
                        socketManager.sendMessage(vehicleData.getFrame());
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
}