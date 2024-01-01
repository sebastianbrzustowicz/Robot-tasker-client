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

public class CustomVehicleRegistrationActivity extends AppCompatActivity {

    Button btn_BackToStandardRegister, btn_CustomRegisterSend;
    EditText et_dataInputLoginRegisterForm, et_dataInputPasswordRegisterForm, et_dataInputEmailRegisterForm, et_dataInputPhoneNumberRegisterForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_custom_vehicle);

        // assign values to each control on the layout
        btn_BackToStandardRegister = findViewById(R.id.btn_BackToStandardRegister);
        btn_CustomRegisterSend = findViewById(R.id.btn_CustomRegisterSend);

        et_dataInputLoginRegisterForm = findViewById(R.id.et_CustomVehicleId);
        et_dataInputPasswordRegisterForm = findViewById(R.id.et_VehicleName);
        et_dataInputEmailRegisterForm = findViewById(R.id.et_VehicleType);
        et_dataInputPhoneNumberRegisterForm = findViewById(R.id.et_dataInputPhoneNumberRegisterForm);

        btn_CustomRegisterSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo
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