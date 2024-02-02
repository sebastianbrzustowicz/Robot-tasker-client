package com.sebastianbrzustowicz.robottaskerclient.global;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.sebastianbrzustowicz.robottaskerclient.R;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.util.Arrays;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class MyApplication extends Application {


    private String userId;
    private String email;
    private String password;
    private String vehicleId;
    private String authorizedVehicleId;

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

    public String getAuthorizedVehicleId() {
        return authorizedVehicleId;
    }

    public void setAuthorizedVehicleId(String authorizedVehicleId) {
        this.authorizedVehicleId = authorizedVehicleId;
    }

    //@Override
    //public void onCreate() {
    //    super.onCreate();
    //    configureSSL();
    //}

    private void configureSSL() {
        try {
            InputStream certInputStream = getResources().openRawResource(R.raw.keystore);
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(certInputStream, "Sebastian".toCharArray());
            String keyAlias = "mycertificate";

            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

            HurlStack hurlStack = new HurlStack(null, sslContext.getSocketFactory());
            RequestQueue requestQueue = Volley.newRequestQueue(this, hurlStack);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}