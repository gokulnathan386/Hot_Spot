package com.fusionpos.hotspot.activity;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fusionpos.hotspot.R;
import com.fusionpos.hotspot.check_internet.Internet_connection_checking;

public class Splash_Screen extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 4000;
    String versionName, playstoreurl, title, data, url;
    TextView app_version;
    boolean isShown = false, Connection;
    Internet_connection_checking int_chk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //   versionName = BuildConfig.VERSION_NAME;

        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        versionName = pInfo.versionName;//Version Name


        app_version = findViewById(R.id.app_version);
        app_version.setText("Version " + versionName);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                int_chk = new Internet_connection_checking(Splash_Screen.this);
                Connection = int_chk.checkInternetConnection();
                if (!Connection) {
                    Intent i = new Intent(Splash_Screen.this, Nointernet.class);
                    //Intent is used to switch from one activity to another.
                    startActivity(i);
                    //invoke the SecondActivity.
                    finish();

                } else {
                    Intent i = new Intent(Splash_Screen.this, Home.class);
                    //Intent is used to switch from one activity to another.
                    startActivity(i);
                    //invoke the SecondActivity.
                    finish();
                }


            }
        }, SPLASH_SCREEN_TIME_OUT);

    }


}
