package com.sebastianbrzustowicz.robottaskerclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

public class CustomVehicleRegistrationActivity extends AppCompatActivity {

    Button btn_BackToStandardRegister, btn_CustomRegisterSend;
    EditText et_CustomVehicleId, et_VehicleName, et_VehicleType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_custom_vehicle);

        btn_BackToStandardRegister = findViewById(R.id.btn_BackToStandardRegister);
        btn_CustomRegisterSend = findViewById(R.id.btn_CustomRegisterSend);

        et_CustomVehicleId = findViewById(R.id.et_CustomVehicleId);
        et_VehicleName = findViewById(R.id.et_VehicleName);
        et_VehicleType = findViewById(R.id.et_VehicleType);

        btn_CustomRegisterSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queue = Volley.newRequestQueue(CustomVehicleRegistrationActivity.this);

                String url = "http://10.0.2.2:8080/rest/vehicle/custom/register";

                Context applicationContext = getApplicationContext();

                final String userId = ((MyApplication) applicationContext).getUserId();
                final String vehicleId = et_CustomVehicleId.getText().toString();
                final String vehicleName = et_VehicleName.getText().toString();
                final String vehicleType = et_VehicleType.getText().toString();

                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("userId", userId);
                    requestBody.put("vehicleId", vehicleId);
                    requestBody.put("vehicleName", vehicleName);
                    requestBody.put("vehicleType", vehicleType);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                StringRequest registerCustomVehicleRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(CustomVehicleRegistrationActivity.this, response, Toast.LENGTH_SHORT).show();
                                if (response.equals("Registration successful")) {
                                    Intent intent = new Intent(CustomVehicleRegistrationActivity.this, MenuActivity.class);
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

                queue.add(registerCustomVehicleRequest);

            }
        });

        btn_BackToStandardRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomVehicleRegistrationActivity.this, VehicleRegistrationActivity.class);
                startActivity(intent);
            }
        });

    }
}