package com.example.sensortakip;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ManyetikActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager yonetici;
    private Sensor manyetikSensoru;
    private TextView tvVeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_ekrani);
        tvVeri         = findViewById(R.id.tvSensorVeri);
        yonetici       = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        manyetikSensoru = yonetici.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        if (manyetikSensoru == null) tvVeri.setText("Bu cihazda manyetometre bulunmamaktadır.");
    }

    @Override protected void onResume() { super.onResume(); if (manyetikSensoru != null) yonetici.registerListener(this, manyetikSensoru, SensorManager.SENSOR_DELAY_NORMAL); }
    @Override protected void onPause()  { super.onPause();  if (manyetikSensoru != null) yonetici.unregisterListener(this); }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            tvVeri.setText(String.format("Manyetometre:\nX: %.2f µT\nY: %.2f µT\nZ: %.2f µT", x, y, z));
        }
    }

    @Override public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
