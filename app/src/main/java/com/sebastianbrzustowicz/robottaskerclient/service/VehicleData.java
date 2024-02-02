package com.sebastianbrzustowicz.robottaskerclient.service;

public class VehicleData {
    private static VehicleData instance;

    private String vehicleId;
    private int mode;
    private int vtol;
    private int pitchd;
    private int rolld;
    private int altituded;
    private int yawd;
    private boolean camTrig;
    private boolean camTog;
    private int camPitch;
    private boolean clamp;
    // sensors values
    private double roll;
    private double pitch;
    private double yaw;
    private double altitude;
    private boolean isClamp;

    private VehicleData() {
        this.vehicleId = "";
        this.mode = 1;
        this.vtol = 0;
        this.pitchd = 0;
        this.rolld = 0;
        this.altituded = 0;
        this.yawd = 0;
        this.camTrig = false;
        this.camTog = false;
        this.camPitch = 0;
        this.clamp = false;

        this.roll = 0.0;
        this.pitch = 0.0;
        this.yaw = 0.0;
        this.altitude = 0.0;
        this.isClamp = false;
    }

    // Instance

    public static VehicleData getInstance() {
        if (instance == null) {
            instance = new VehicleData();
        }
        return instance;
    }

    public String getFrame() {
        // there handshake should be established between server and client
        // data is sent in raw format but values stands for these variables:
        // CLIENT                                   <- fixed prefix for client message
        // 4436ed9a-5228-46c0-b825-6d0a3cd90437     <- vehicleId
        // 1                                        <- mode
        // 0                                        <- vtol
        // 0                                        <- x
        // 0                                        <- y
        // 0                                        <- alt
        // 0                                        <- yaw
        // false                                    <- camTrig
        // false                                    <- camTog
        // 0                                        <- camPitch
        // false                                    <- clamp
        // END                                      <- fixed ending statement of message

        StringBuilder dataFrame = new StringBuilder();

        dataFrame.append("CLIENT").append("\n");
        dataFrame.append(vehicleId).append("\n");
        dataFrame.append(mode).append("\n");
        dataFrame.append(vtol).append("\n");
        dataFrame.append(pitchd).append("\n");
        dataFrame.append(rolld).append("\n");
        dataFrame.append(altituded).append("\n");
        dataFrame.append(yawd).append("\n");
        dataFrame.append(camTrig).append("\n");
        dataFrame.append(camTog).append("\n");
        dataFrame.append(camPitch).append("\n");
        dataFrame.append(clamp).append("\n");
        dataFrame.append("END");

        return dataFrame.toString();
    }

    public void resetValues() {
        this.vehicleId = "";
        this.mode = 1;
        this.vtol = 0;
        this.pitchd = 0;
        this.rolld = 0;
        this.altituded = 0;
        this.yawd = 0;
        this.camTrig = false;
        this.camTog = false;
        this.camPitch = 0;
        this.clamp = false;

        this.altitude = 0;
    }

    public void saveSensorsValues(double altitude) {
        this.altitude = altitude;
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

    public int getPitchd() {
        return pitchd;
    }

    public void setPitchd(int pitchd) {
        this.pitchd = pitchd;
    }

    public int getRolld() {
        return rolld;
    }

    public void setRolld(int rolld) {
        this.rolld = rolld;
    }

    public int getAltituded() {
        return altituded;
    }

    public void setAltituded(int altituded) {
        this.altituded = altituded;
    }

    public int getYawd() {
        return yawd;
    }

    public void setYawd(int yawd) {
        this.yawd = yawd;
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