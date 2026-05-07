package com.example.cihizkontrol;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class WifiEkrani extends AppCompatActivity {

    private WifiManager agYonetici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_ekrani);

        agYonetici = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        final ToggleButton toggleWifi = (ToggleButton) findViewById(R.id.toggleWifi);

        toggleWifi.setChecked(agYonetici.isWifiEnabled());

        toggleWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Intent panelAc = new Intent(Settings.Panel.ACTION_WIFI);
                    startActivityForResult(panelAc, 1);
                    Toast.makeText(WifiEkrani.this, "Wi-Fi durumunu panelden değiştiriniz.", Toast.LENGTH_SHORT).show();
                    toggleWifi.setChecked(agYonetici.isWifiEnabled());
                } else {
                    if (toggleWifi.isChecked()) {
                        wifiBaslat();
                    } else {
                        wifiDurdur();
                    }
                }
            }
        });
    }

    private void wifiBaslat() {
        if (!agYonetici.isWifiEnabled()) {
            agYonetici.setWifiEnabled(true);
            Toast.makeText(WifiEkrani.this, "Wi-Fi Açık", Toast.LENGTH_SHORT).show();
        }
    }

    private void wifiDurdur() {
        if (agYonetici.isWifiEnabled()) {
            agYonetici.setWifiEnabled(false);
            Toast.makeText(WifiEkrani.this, "Wi-Fi Kapalı", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        final ToggleButton toggleWifi = (ToggleButton) findViewById(R.id.toggleWifi);
        if (toggleWifi != null && agYonetici != null) {
            toggleWifi.setChecked(agYonetici.isWifiEnabled());
        }
    }
}
