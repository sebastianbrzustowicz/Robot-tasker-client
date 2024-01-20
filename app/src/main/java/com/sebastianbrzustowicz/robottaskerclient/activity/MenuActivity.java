package com.sebastianbrzustowicz.robottaskerclient.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sebastianbrzustowicz.robottaskerclient.global.MyApplication;
import com.sebastianbrzustowicz.robottaskerclient.R;
import com.sebastianbrzustowicz.robottaskerclient.model.Vehicle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    Button btn_Logout;
    ImageButton btn_add_vehicle;
    ListView lv_vehicleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_Logout = findViewById(R.id.btn_Logout);
        btn_add_vehicle = findViewById(R.id.btn_add_vehicle);
        lv_vehicleList = findViewById(R.id.lv_vehicleList);

        Context applicationContext = getApplicationContext();
        String userId = ((MyApplication) applicationContext).getUserId();
        //Log.d("test", "Wartość zmiennej: " + userId);
        //Toast.makeText(MenuActivity.this, userId, Toast.LENGTH_SHORT).show();
        String url = "http://10.0.2.2:8080/rest/vehicle/information?userId=" + userId;


        RequestQueue queue = Volley.newRequestQueue(MenuActivity.this);
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
                            List<Vehicle> vehicleList = new ArrayList<>();
                            List<String> vehicleUUIDList = new ArrayList<>();
                            List<String> vehicleNameList = new ArrayList<>();
                            List<String> ListViewList = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                String userId = jsonObject.getString("userId");
                                String vehicleId = jsonObject.getString("vehicleId");
                                String vehicleName = jsonObject.getString("vehicleName");
                                String vehicleType = jsonObject.getString("vehicleType");
                                String registrationTime = jsonObject.getString("registrationTime");

                                Vehicle vehicle = new Vehicle(userId, vehicleId, vehicleName, vehicleType, registrationTime);
                                vehicleList.add(vehicle);
                                vehicleUUIDList.add(vehicleId);
                                vehicleNameList.add(vehicleName);
                                ListViewList.add("Name: " + vehicleName + ", \nUUID: " + vehicleId);

                                Log.d("Vehicle Info", "userId: " + userId +
                                        ", vehicleId: " + vehicleId +
                                        ", vehicleName: " + vehicleName +
                                        ", vehicleType: " + vehicleType +
                                        ", registrationTime: " + registrationTime);
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(MenuActivity.this, android.R.layout.simple_list_item_1, ListViewList);
                            lv_vehicleList.setAdapter(adapter);

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

        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Context applicationContext = getApplicationContext();

                        ((MyApplication) applicationContext).setEmail("none");
                        ((MyApplication) applicationContext).setEmail("none");
                        ((MyApplication) applicationContext).setPassword("none");
                        ((MyApplication) applicationContext).setVehicleId("none");

                    }
                });
                // Change activity
                Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        lv_vehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Get item data
                String clickedVehicle = (String) parent.getItemAtPosition(position);

                int nameIndex = clickedVehicle.indexOf("Name: ");
                int uuidIndex = clickedVehicle.indexOf("UUID: ");

                String name = "no name";
                String uuid = "no UUID";

                if (nameIndex != -1 && uuidIndex != -1) {
                    name = clickedVehicle.substring(nameIndex + "Name: ".length(), clickedVehicle.indexOf(',', nameIndex)).trim();
                    uuid = clickedVehicle.substring(uuidIndex + "UUID: ".length()).trim();
                }
                //Log.d("test", "Wartość zmiennej: " + name + " " + uuid);

                Context applicationContext = getApplicationContext();
                ((MyApplication) applicationContext).setVehicleId(uuid);

                Intent intent = new Intent(MenuActivity.this, VehicleMenuActivity.class);
                intent.putExtra("vehicleName", name);
                intent.putExtra("vehicleUUID", uuid);
                startActivity(intent);
            }
        });

        btn_add_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, VehicleRegistrationActivity.class);
                startActivity(intent);
            }
        });

    }
}