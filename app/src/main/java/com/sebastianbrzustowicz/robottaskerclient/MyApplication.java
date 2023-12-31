package com.sebastianbrzustowicz.robottaskerclient;

import android.app.Application;

public class MyApplication extends Application {

    private String userId;
    private String email;
    private String password;
    private String vehicleId;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVehicleId() { return vehicleId; }

    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }
}