package com.sebastianbrzustowicz.robottaskerclient.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sebastianbrzustowicz.robottaskerclient.activity.LoginActivity;
import com.sebastianbrzustowicz.robottaskerclient.activity.MenuActivity;
import com.sebastianbrzustowicz.robottaskerclient.global.MyApplication;

import java.net.URI;

import tech.gusavila92.websocketclient.WebSocketClient;

public class WebSocketClientManager {

    private static WebSocketClientManager instance;
    private WebSocketClient webSocketClient;

    private WebSocketClientManager() {
        // Private constructor to prevent instantiation.
    }

    public static synchronized WebSocketClientManager getInstance() {
        if (instance == null) {
            instance = new WebSocketClientManager();
        }
        return instance;
    }

    public void createWebSocketClient(String webSocketUrl) {
        URI uri;
        try {
            uri = new URI(webSocketUrl);
        } catch (Exception e) {
            System.out.println("Cannot create host URI resource");
            return;
        }

        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen() {
                System.out.println("Connection opened");
            }

            @Override
            public void onTextReceived(String msg) {
                System.out.println("Received message:\n" + msg);

                VehicleData vehicleData = VehicleData.getInstance();
                if (msg.startsWith("VEHICLE")) {
                    String[] lines = msg.split("\n");
                    int altitude = Integer.parseInt(lines[1]);
                    vehicleData.saveSensorsValues(altitude);
                }
            }

            @Override
            public void onBinaryReceived(byte[] data) {
            }

            @Override
            public void onPingReceived(byte[] data) {
            }

            @Override
            public void onPongReceived(byte[] data) {
            }

            @Override
            public void onException(Exception e) {
                Log.e("WebSocket", e.getMessage());
            }

            @Override
            public void onCloseReceived() {
                Log.i("WebSocket", "Connection closed");
            }
        };

        try {
            webSocketClient.setConnectTimeout(10000);
            webSocketClient.setReadTimeout(120000);
            webSocketClient.enableAutomaticReconnection(1000);
            webSocketClient.connect();
        } catch (Exception e) {
            System.out.println("Cannot connect to host");
        }
    }

    public void sendMessage(String message) {
        try {
            webSocketClient.send(message);
        } catch (Exception e) {
            Log.i("WebSocket", "Cannot send message");
        }
    }
}