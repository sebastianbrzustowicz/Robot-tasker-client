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
import com.sebastianbrzustowicz.robottaskerclient.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    Button btn_SignUpRegisterForm, btn_SwapToLoginRegisterForm;
    EditText et_dataInputLoginRegisterForm, et_dataInputPasswordRegisterForm, et_dataInputEmailRegisterForm, et_dataInputPhoneNumberRegisterForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // assign values to each control on the layout
        btn_SignUpRegisterForm = findViewById(R.id.btn_CustomRegisterSend);
        btn_SwapToLoginRegisterForm = findViewById(R.id.btn_SwapToLoginRegisterForm);

        et_dataInputLoginRegisterForm = findViewById(R.id.et_CustomVehicleId);
        et_dataInputPasswordRegisterForm = findViewById(R.id.et_VehicleName);
        et_dataInputEmailRegisterForm = findViewById(R.id.et_VehicleType);
        et_dataInputPhoneNumberRegisterForm = findViewById(R.id.et_dataInputPhoneNumberRegisterForm);

        btn_SignUpRegisterForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // send request and toast
                // Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(RegistrationActivity.this);

                Resources resources = getResources();
                String domain = resources.getString(R.string.address);
                String url = domain + "/rest/user/register";

                // Data to transfer
                final String login = et_dataInputLoginRegisterForm.getText().toString();
                final String password = et_dataInputPasswordRegisterForm.getText().toString();
                final String email = et_dataInputEmailRegisterForm.getText().toString();
                final String phoneNumberString = et_dataInputPhoneNumberRegisterForm.getText().toString();
                final int phoneNumber = phoneNumberString.isEmpty() ? 0 : Integer.valueOf(phoneNumberString);

                //final String login = "123";
                //final String password = "123";
                //final String email = "123";
                //final int phoneNumber = 123;
                //int phoneNumber = Integer.parseInt(phoneNumberString);

                // Prepare JSON object
                JSONObject requestBody = new JSONObject();
                try {
                    requestBody.put("userId", "id");
                    requestBody.put("login", login);
                    requestBody.put("password", password);
                    requestBody.put("email", email);
                    requestBody.put("phoneNumber", phoneNumber);
                    requestBody.put("role", "user");
                    requestBody.put("accCreated", "2023");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Request a JSON response from the provided URL.
                StringRequest registerRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                final int resValue = Integer.valueOf(response);
                                String message = (response != null && resValue == 1) ? "Registered successfully" : "Cannot register";
                                Toast.makeText(RegistrationActivity.this, message, Toast.LENGTH_SHORT).show();
                                if (response != null && resValue == 1) {
                                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
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

                queue.add(registerRequest);
            }
        });

        btn_SwapToLoginRegisterForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating a new Intent, which will transfer to RegistrationActivity
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);

                // Launching a new activity
                startActivity(intent);
            }
        });

    }
}