package com.sebastianbrzustowicz.robottaskerclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;

public class LoginActivity extends AppCompatActivity {

    Button btn_SignIn, btn_SignUp;
    EditText et_dataInputEmail, et_dataInputPassword;
    ListView lv_weatherReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // assign values to each control on the layout
        btn_SignIn = findViewById(R.id.btn_SignIn);
        btn_SignUp = findViewById(R.id.btn_SignUp);
        //btn_getWeatherByCityName = findViewById(R.id.btn_getWeatherByCityName);

        et_dataInputEmail = findViewById(R.id.et_dataInputEmail);
        et_dataInputPassword = findViewById(R.id.et_dataInputPassword);
        //lv_weatherReport = findViewById(R.id.lv_weatherReport);

        // click listeners for each button
        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

                String url = "http://10.0.2.2:8080/rest/user/login";

                // Data to transfer
                final String email = et_dataInputEmail.getText().toString();
                final String password = et_dataInputPassword.getText().toString();

                // Prepare JSON object
                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("email", email);
                    requestBody.put("password", password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Request a string response from the provided URL.
                StringRequest loginRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                                if (response.startsWith("Logged in, your UUID is:")) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Context applicationContext = getApplicationContext();

                                            int length = response.length();
                                            String userId = response.substring(Math.max(0, length - 36));

                                            ((MyApplication) applicationContext).setUserId(userId);
                                            ((MyApplication) applicationContext).setEmail(email);
                                            ((MyApplication) applicationContext).setPassword(password);

                                            Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
                                            startActivity(intent);

                                            // getter - for later purpose
                                            //String s = ((MyApplication) applicationContext).getEmail();
                                        }
                                    });
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
                        // Send data as a string, even if it is actually in JSON form
                        return requestBody.toString().getBytes();
                    }

                    @Override
                    public Map<String, String> getHeaders() {
                        // Set header Content-Type as application/json
                        Map<String, String> headers = new HashMap<>();
                        headers.put("Content-Type", "application/json");
                        return headers;
                    }
                };

                // Add the request to the RequestQueue.
                queue.add(loginRequest);

            }
        });

        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tworzenie nowego Intentu, który przeniesie do RegistrationActivity
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);

                // Uruchamianie nowej aktywności
                startActivity(intent);
            }
        });

    }
}