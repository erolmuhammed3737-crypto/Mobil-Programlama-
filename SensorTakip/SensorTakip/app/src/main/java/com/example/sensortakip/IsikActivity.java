package com.example.sensortakip;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IsikActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager yonetici;
    private Sensor isikSensoru;
    private TextView tvVeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_ekrani);
        tvVeri     = findViewById(R.id.tvSensorVeri);
        yonetici   = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        isikSensoru = yonetici.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (isikSensoru == null) tvVeri.setText("Bu cihazda ışık sensörü bulunmamaktadır.");
    }

    @Override protected void onResume() { super.onResume(); if (isikSensoru != null) yonetici.registerListener(this, isikSensoru, SensorManager.SENSOR_DELAY_NORMAL); }
    @Override protected void onPause()  { super.onPause();  if (isikSensoru != null) yonetici.unregisterListener(this); }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            tvVeri.setText(String.format("Işık Seviyesi: %.2f lx", event.values[0]));
        }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
