package com.example.sensortakip;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SicaklikActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager yonetici;
    private Sensor sicaklikSensoru;
    private TextView tvVeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_ekrani);
        tvVeri          = findViewById(R.id.tvSensorVeri);
        yonetici        = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sicaklikSensoru  = yonetici.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        if (sicaklikSensoru == null) tvVeri.setText("Bu cihazda sıcaklık sensörü bulunmamaktadır.");
    }

    @Override protected void onResume() { super.onResume(); if (sicaklikSensoru != null) yonetici.registerListener(this, sicaklikSensoru, SensorManager.SENSOR_DELAY_NORMAL); }
    @Override protected void onPause()  { super.onPause();  if (sicaklikSensoru != null) yonetici.unregisterListener(this); }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            tvVeri.setText(String.format("Ortam Sıcaklığı: %.2f °C", event.values[0]));
        }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
