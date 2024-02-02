package com.sebastianbrzustowicz.robottaskerclient.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
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
import com.sebastianbrzustowicz.robottaskerclient.global.MyApplication;
import com.sebastianbrzustowicz.robottaskerclient.R;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class VehicleRegistrationActivity extends AppCompatActivity {

    Button btn_RegisterVehicle, btn_BackToMenu, btn_CustomVehicle;
    EditText et_VehicleId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_vehicle);

        // assign values to each control on the layout
        btn_RegisterVehicle = findViewById(R.id.btn_RegisterVehicle);
        btn_BackToMenu = findViewById(R.id.btn_BackToMenu);
        btn_CustomVehicle = findViewById(R.id.btn_CustomVehicle);
        et_VehicleId = findViewById(R.id.et_VehicleId);

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

        btn_RegisterVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queue = Volley.newRequestQueue(VehicleRegistrationActivity.this);

                Resources resources = getResources();
                String domain = resources.getString(R.string.address);
                String url = domain + "/rest/vehicle/register";

                Context applicationContext = getApplicationContext();

                final String vehicleId = et_VehicleId.getText().toString();
                final String userId = ((MyApplication) applicationContext).getUserId();
                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("userId", userId);
                    requestBody.put("vehicleId", vehicleId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                StringRequest registerVehicleRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(VehicleRegistrationActivity.this, response, Toast.LENGTH_SHORT).show();
                                if (response.equals("Registration successful")) {
                                    Intent intent = new Intent(VehicleRegistrationActivity.this, MenuActivity.class);
                                    startActivity(intent);
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Volley Error", "Error occurred: " + error.toString());
                            }
                        }) {
                    @Override
                    public byte[] getBody() {
                        return requestBody.toString().getBytes();
                    }
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };

                queue.add(registerVehicleRequest);

            }
        });
    }
}