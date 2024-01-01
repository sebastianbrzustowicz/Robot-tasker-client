package com.sebastianbrzustowicz.robottaskerclient;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class VehicleRegistrationActivity extends AppCompatActivity {

    Button btn_RegisterVehicle, btn_BackToMenu, btn_CustomVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_vehicle);

        // assign values to each control on the layout
        btn_RegisterVehicle = findViewById(R.id.btn_RegisterVehicle);
        btn_BackToMenu = findViewById(R.id.btn_BackToMenu);
        btn_CustomVehicle = findViewById(R.id.btn_CustomVehicle);

        btn_BackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VehicleRegistrationActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        btn_CustomVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VehicleRegistrationActivity.this, CustomVehicleRegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}