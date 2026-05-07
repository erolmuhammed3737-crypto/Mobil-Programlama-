package com.example.sensortakip;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NemActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager yonetici;
    private Sensor nemSensoru;
    private TextView tvVeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_ekrani);
        tvVeri    = findViewById(R.id.tvSensorVeri);
        yonetici  = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        nemSensoru = yonetici.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        if (nemSensoru == null) tvVeri.setText("Bu cihazda nem sensörü bulunmamaktadır.");
    }

    @Override protected void onResume() { super.onResume(); if (nemSensoru != null) yonetici.registerListener(this, nemSensoru, SensorManager.SENSOR_DELAY_NORMAL); }
    @Override protected void onPause()  { super.onPause();  if (nemSensoru != null) yonetici.unregisterListener(this); }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            tvVeri.setText(String.format("Nem Oranı: %.2f%%", event.values[0]));
        }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
