package com.example.sayacrenk;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    SeekBar ustBar, altBar;
    Button btnBaslat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ustBar   = findViewById(R.id.ustBar);
        altBar   = findViewById(R.id.altBar);
        btnBaslat = findViewById(R.id.btnBaslat);

        ustBar.setMax(100);
        altBar.setMax(100);

        ustBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
                if (altBar.getProgress() > progress) {
                    altBar.setProgress(progress);
                }
            }
            @Override public void onStartTrackingTouch(SeekBar sb) {}
            @Override public void onStopTrackingTouch(SeekBar sb) {}
        });

        altBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
                if (progress > ustBar.getProgress()) {
                    altBar.setProgress(ustBar.getProgress());
                }
            }
            @Override public void onStartTrackingTouch(SeekBar sb) {}
            @Override public void onStopTrackingTouch(SeekBar sb) {}
        });

        btnBaslat.setOnClickListener(v -> {
            int ust = ustBar.getProgress();
            int alt = altBar.getProgress();

            int sureDegeri;
            if (ust == alt) {
                sureDegeri = ust;
            } else {
                sureDegeri = new Random().nextInt((ust - alt) + 1) + alt;
            }

            Intent gecis = new Intent(MainActivity.this, RenkEkraniActivity.class);
            gecis.putExtra("SURE_DEGERI", sureDegeri);
            startActivity(gecis);
        });
    }
}
