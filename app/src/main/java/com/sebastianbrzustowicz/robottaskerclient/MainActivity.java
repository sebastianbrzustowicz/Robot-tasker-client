package com.sebastianbrzustowicz.robottaskerclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    Button btn_getCityID, btn_getWeatherByCityID, btn_getWeatherByCityName;
    EditText et_dataInput;
    ListView lv_weatherReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assign values to each control on the layout
        btn_getCityID = findViewById(R.id.btn_getCityID);
        btn_getWeatherByCityID = findViewById(R.id.btn_getWeatherByCityID);
        btn_getWeatherByCityName = findViewById(R.id.btn_getWeatherByCityName);

        et_dataInput = findViewById(R.id.et_dataInput);
        lv_weatherReport = findViewById(R.id.lv_weatherReport);

        // click listeners for each button
        btn_getCityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                //String url = "http://localhost:8080/rest/user/register";
                String url = "http://10.0.2.2:8080/rest/vehicle/information?userId=732a2c2e-9fba-11ee-83ae-0242ac110002";
                //String url = "https://dummyjson.com/products/1";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley Error", "Error occurred: " + error.toString());
                    }
                });

                // Add the request to the RequestQueue.
                queue.add(stringRequest);

                // Toast.makeText(MainActivity.this, "Button 1 clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btn_getWeatherByCityID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Button 2 clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btn_getWeatherByCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "You typed " + et_dataInput.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}