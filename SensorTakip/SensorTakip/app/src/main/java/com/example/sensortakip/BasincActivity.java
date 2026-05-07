package com.example.sensortakip;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BasincActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager yonetici;
    private Sensor basincSensoru;
    private TextView tvVeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_ekrani);
        tvVeri       = findViewById(R.id.tvSensorVeri);
        yonetici     = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        basincSensoru = yonetici.getDefaultSensor(Sensor.TYPE_PRESSURE);
        if (basincSensoru == null) tvVeri.setText("Bu cihazda basınç sensörü bulunmamaktadır.");
    }

    @Override protected void onResume() { super.onResume(); if (basincSensoru != null) yonetici.registerListener(this, basincSensoru, SensorManager.SENSOR_DELAY_NORMAL); }
    @Override protected void onPause()  { super.onPause();  if (basincSensoru != null) yonetici.unregisterListener(this); }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            tvVeri.setText(String.format("Hava Basıncı: %.2f hPa", event.values[0]));
        }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
