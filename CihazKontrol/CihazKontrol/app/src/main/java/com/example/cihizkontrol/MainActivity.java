package com.example.cihizkontrol;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnBluetooth = (Button) findViewById(R.id.btnBluetooth);
        Button btnWifi      = (Button) findViewById(R.id.btnWifi);
        Button btnKamera    = (Button) findViewById(R.id.btnKamera);

        btnBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecis = new Intent(MainActivity.this, BluetoothEkrani.class);
                startActivity(gecis);
            }
        });

        btnWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecis = new Intent(MainActivity.this, WifiEkrani.class);
                startActivity(gecis);
            }
        });

        btnKamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gecis = new Intent(MainActivity.this, KameraEkrani.class);
                startActivity(gecis);
            }
        });
    }
}
