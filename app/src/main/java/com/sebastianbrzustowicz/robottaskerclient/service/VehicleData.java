package com.sebastianbrzustowicz.robottaskerclient.service;

public class VehicleData {
    private static VehicleData instance;

    private String vehicleId;
    private int mode;
    private int vtol;
    private int x;
    private int y;
    private int alt;
    private int yaw;
    private boolean camTrig;
    private boolean camTog;
    private int camPitch;
    private boolean clamp;

    private VehicleData() {
        this.vehicleId = "";
        this.mode = 1;
        this.vtol = 0;
        this.x = 0;
        this.y = 0;
        this.alt = 0;
        this.yaw = 0;
        this.camTrig = false;
        this.camTog = false;
        this.camPitch = 0;
        this.clamp = false;
    }

    // Instance

    public static VehicleData getInstance() {
        if (instance == null) {
            instance = new VehicleData();
        }
        return instance;
    }

    public String getFrame() {
        StringBuilder dataFrame = new StringBuilder();

        dataFrame.append("CLIENT").append("\n");
        dataFrame.append("vehicleId: ").append(vehicleId).append("\n");
        dataFrame.append("mode: ").append(mode).append("\n");
        dataFrame.append("vtol: ").append(vtol).append("\n");
        dataFrame.append("x: ").append(x).append("\n");
        dataFrame.append("y: ").append(y).append("\n");
        dataFrame.append("altitude: ").append(alt).append("\n");
        dataFrame.append("yaw: ").append(yaw).append("\n");
        dataFrame.append("camTrig: ").append(camTrig).append("\n");
        dataFrame.append("camTog: ").append(camTog).append("\n");
        dataFrame.append("camPitch: ").append(camPitch).append("\n");
        dataFrame.append("clamp: ").append(clamp).append("\n");

        return dataFrame.toString();
    }

    public void resetValues() {
        this.vehicleId = "";
        this.mode = 1;
        this.vtol = 0;
        this.x = 0;
        this.y = 0;
        this.alt = 0;
        this.yaw = 0;
        this.camTrig = false;
        this.camTog = false;
        this.camPitch = 0;
        this.clamp = false;
    }

    // Getter and setter methods

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getVtol() {
        return vtol;
    }

    public void setVtol(int vtol) {
        this.vtol = vtol;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }

    public int getYaw() {
        return yaw;
    }

    public void setYaw(int yaw) {
        this.yaw = yaw;
    }

    public boolean isCamTrig() {
        return camTrig;
    }

    public void setCamTrig(boolean camTrig) {
        this.camTrig = camTrig;
    }

    public boolean isCamTog() {
        return camTog;
    }

    public void setCamTog(boolean camTog) {
        this.camTog = camTog;
    }

    public int getCamPitch() {
        return camPitch;
    }

    public void setCamPitch(int camPitch) {
        this.camPitch = camPitch;
    }

    public boolean isClamp() {
        return clamp;
    }

    public void setClamp(boolean clamp) {
        this.clamp = clamp;
    }
}