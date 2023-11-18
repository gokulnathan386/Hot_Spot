package com.fusionpos.hotspot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.fusionpos.hotspot.R;
import com.fusionpos.hotspot.check_internet.Internet_connection_checking;

public class Nointernet extends AppCompatActivity {


    Button retry;

    boolean isShown = false, Connection;
    Internet_connection_checking int_chk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nointernet);


        retry = findViewById(R.id.retry);

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int_chk = new Internet_connection_checking(Nointernet.this);
                Connection = int_chk.checkInternetConnection();
                if (!Connection) {
                    finish();
                    startActivity(getIntent());

                } else {
                    Intent i = new Intent(Nointernet.this, Home.class);
                    startActivity(i);
                    finish();
                }

            }
        });

    }
}
