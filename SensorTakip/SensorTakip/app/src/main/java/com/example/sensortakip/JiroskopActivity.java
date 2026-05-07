package com.example.sensortakip;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JiroskopActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager yonetici;
    private Sensor jiroskopSensoru;
    private TextView tvVeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_ekrani);

        tvVeri         = findViewById(R.id.tvSensorVeri);
        yonetici       = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        jiroskopSensoru = yonetici.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        if (jiroskopSensoru == null) {
            tvVeri.setText("Bu cihazda jiroskop bulunmamaktadır.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (jiroskopSensoru != null) {
            yonetici.registerListener(this, jiroskopSensoru, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (jiroskopSensoru != null) {
            yonetici.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            tvVeri.setText(String.format("Jiroskop:\nX: %.2f rad/s\nY: %.2f rad/s\nZ: %.2f rad/s", x, y, z));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
