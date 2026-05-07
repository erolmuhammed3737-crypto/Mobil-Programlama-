package com.example.sensortakip;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class YakinlikActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager yonetici;
    private Sensor yakinlikSensoru;
    private TextView tvVeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_ekrani);
        tvVeri         = findViewById(R.id.tvSensorVeri);
        yonetici       = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        yakinlikSensoru = yonetici.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if (yakinlikSensoru == null) tvVeri.setText("Bu cihazda yakınlık sensörü bulunmamaktadır.");
    }

    @Override protected void onResume() { super.onResume(); if (yakinlikSensoru != null) yonetici.registerListener(this, yakinlikSensoru, SensorManager.SENSOR_DELAY_NORMAL); }
    @Override protected void onPause()  { super.onPause();  if (yakinlikSensoru != null) yonetici.unregisterListener(this); }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            tvVeri.setText(String.format("Yakınlık: %.2f cm", event.values[0]));
        }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
