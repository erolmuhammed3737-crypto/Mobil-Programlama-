package com.example.sensortakip;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PusulaActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager yonetici;
    private Sensor pusulaSensoru;
    private TextView tvVeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_ekrani);

        tvVeri       = findViewById(R.id.tvSensorVeri);
        yonetici     = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        pusulaSensoru = yonetici.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if (pusulaSensoru == null) {
            tvVeri.setText("Bu cihazda pusula sensörü bulunmamaktadır.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pusulaSensoru != null) {
            yonetici.registerListener(this, pusulaSensoru, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (pusulaSensoru != null) {
            yonetici.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            double siddet = Math.sqrt(x * x + y * y + z * z);
            tvVeri.setText(String.format("Manyetik Alan Şiddeti: %.2f µT", siddet));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
