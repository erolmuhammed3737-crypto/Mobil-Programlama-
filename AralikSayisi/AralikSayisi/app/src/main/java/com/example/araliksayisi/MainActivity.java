package com.example.araliksayisi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SeekBar kaydiriciA, kaydiriciB;
    private TextView etiketA, etiketB;
    private Button btnUret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kaydiriciA = findViewById(R.id.kaydiriciA);
        kaydiriciB = findViewById(R.id.kaydiriciB);
        etiketA    = findViewById(R.id.etiketA);
        etiketB    = findViewById(R.id.etiketB);
        btnUret    = findViewById(R.id.btnUret);

        kaydiriciA.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                etiketA.setText("A: " + progress);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        kaydiriciB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                etiketB.setText("B: " + progress);
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnUret.setOnClickListener(v -> {
            int degerA = kaydiriciA.getProgress();
            int degerB = kaydiriciB.getProgress();

            int kucuk = Math.min(degerA, degerB);
            int buyuk = Math.max(degerA, degerB);

            int uretilen;
            if (kucuk == buyuk) {
                uretilen = kucuk;
            } else {
                uretilen = new Random().nextInt((buyuk - kucuk) + 1) + kucuk;
            }

            Intent gecis = new Intent(MainActivity.this, GeriSayimActivity.class);
            gecis.putExtra("URETILEN_SAYI", uretilen);
            startActivity(gecis);
        });
    }
}
