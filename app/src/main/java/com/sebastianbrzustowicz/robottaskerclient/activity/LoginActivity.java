package com.sebastianbrzustowicz.robottaskerclient.activity;

import androidx.appcompat.app.AppCompatActivity;

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

import android.content.Context;

public class LoginActivity extends AppCompatActivity {

    Button btn_LoginRequest, btn_SwapToRegister;
    EditText et_dataInputEmail, et_dataInputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn_LoginRequest = findViewById(R.id.btn_LoginRequest);
        btn_SwapToRegister = findViewById(R.id.btn_SwapToRegister);
        et_dataInputEmail = findViewById(R.id.et_dataInputEmail);
        et_dataInputPassword = findViewById(R.id.et_dataInputPassword);

        btn_LoginRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

                Resources resources = getResources();
                String domain = resources.getString(R.string.address);
                String url = domain + "/rest/user/login";

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
                                    Context context = getApplicationContext();
                                    MyApplication myApplication = (MyApplication) context;

                                    int length = response.length();
                                    String userId = response.substring(Math.max(0, length - 36));

                                    myApplication.setUserId(userId);
                                    myApplication.setEmail(email);
                                    myApplication.setPassword(password);

                                    Intent intent = new Intent(LoginActivity.this, MenuActivity.class);
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

        btn_SwapToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

    }
}