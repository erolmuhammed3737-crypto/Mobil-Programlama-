package com.example.sensortakip;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class IvmeActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager yonetici;
    private Sensor ivmeSensoru;
    private TextView tvVeri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_ekrani);

        tvVeri     = findViewById(R.id.tvSensorVeri);
        yonetici   = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        ivmeSensoru = yonetici.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (ivmeSensoru == null) {
            tvVeri.setText("Bu cihazda ivmeölçer bulunmamaktadır.");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ivmeSensoru != null) {
            yonetici.registerListener(this, ivmeSensoru, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ivmeSensoru != null) {
            yonetici.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
            tvVeri.setText(String.format("İvmeölçer:\nX: %.2f m/s²\nY: %.2f m/s²\nZ: %.2f m/s²", x, y, z));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}
