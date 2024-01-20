package com.sebastianbrzustowicz.robottaskerclient.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sebastianbrzustowicz.robottaskerclient.global.MyApplication;
import com.sebastianbrzustowicz.robottaskerclient.R;
import com.sebastianbrzustowicz.robottaskerclient.service.VehicleData;
import com.sebastianbrzustowicz.robottaskerclient.service.WebSocketClientManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.net.URI;
import java.net.URISyntaxException;
import tech.gusavila92.websocketclient.WebSocketClient;

public class VehicleMenuActivity extends AppCompatActivity {

    Button btn_backToList, btn_connect;
    ImageButton imageButton_DeleteVehicle;
    TextView textView_VehicleNameInfo, textView_VehicleTypeInfo, textView_VehicleID, textView_UserID, textView_RegistrationDate;
    //private WebSocketClient webSocketClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_menu);

        btn_backToList = findViewById(R.id.btn_backToList);
        btn_connect = findViewById(R.id.btn_connect);
        imageButton_DeleteVehicle = findViewById(R.id.imageButton_DeleteVehicle);
        textView_VehicleNameInfo = findViewById(R.id.textView_VehicleNameInfo);
        textView_VehicleTypeInfo = findViewById(R.id.textView_VehicleTypeInfo);
        textView_VehicleID = findViewById(R.id.textView_VehicleID);
        textView_UserID = findViewById(R.id.textView_UserID);
        textView_RegistrationDate = findViewById(R.id.textView_RegistrationDate);

        Context applicationContext = getApplicationContext();
        String thisVehicleId = ((MyApplication) applicationContext).getVehicleId();
        String userId = ((MyApplication) applicationContext).getUserId();

        String url = "http://10.0.2.2:8080/rest/vehicle/information?userId=" + userId;

        RequestQueue queue = Volley.newRequestQueue(VehicleMenuActivity.this);
        JSONObject requestBody = null;
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String vehicleId = jsonObject.getString("vehicleId");

                                if (thisVehicleId.equals(vehicleId)) {
                                    String userId = jsonObject.getString("userId");
                                    String vehicleName = jsonObject.getString("vehicleName");
                                    String vehicleType = jsonObject.getString("vehicleType");
                                    String registrationTime = jsonObject.getString("registrationTime");
                                    textView_VehicleNameInfo.setText(vehicleName);
                                    textView_VehicleTypeInfo.setText(vehicleType);
                                    textView_VehicleID.setText(vehicleId);
                                    textView_UserID.setText(userId);
                                    textView_RegistrationDate.setText(registrationTime);
                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", "Error occurred: " + error.toString());
                    }
                });

        requestQueue.add(jsonArrayRequest);

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

                WebSocketClientManager socketManager = WebSocketClientManager.getInstance();
                String WebSocketUrl = "ws://10.0.2.2:8080/websocket-single-room";
                socketManager.createWebSocketClient(WebSocketUrl);

                Context applicationContext = getApplicationContext();
                final String vehicleId = ((MyApplication) applicationContext).getVehicleId();
                socketManager.sendMessage("vehicleId: " + vehicleId);

                VehicleData vehicleData = VehicleData.getInstance();
                vehicleData.setVehicleId(vehicleId);

                Intent intent = new Intent(VehicleMenuActivity.this, VehicleRuntimeActivity.class);
                startActivity(intent);

            }
        });

        imageButton_DeleteVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(VehicleMenuActivity.this);

                String url = "http://10.0.2.2:8080/rest/vehicle/delete";

                Context applicationContext = getApplicationContext();
                final String vehicleId = ((MyApplication) applicationContext).getVehicleId();
                //Log.d("test", "Wartość zmiennej: " + vehicleId);
                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("vehicleId", vehicleId);
                    requestBody.put("vehicleId2", vehicleId);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                StringRequest deleteVehicleRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(VehicleMenuActivity.this, response, Toast.LENGTH_SHORT).show();
                                if (response.startsWith("Deleted vehicle with ID:")) {
                                    Intent intent = new Intent(VehicleMenuActivity.this, MenuActivity.class);
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

                queue.add(deleteVehicleRequest);

            }
        });
    }

}