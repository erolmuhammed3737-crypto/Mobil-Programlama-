package com.example.araliksayisi;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class GeriSayimActivity extends AppCompatActivity {

    private TextView tvZaman;
    private ConstraintLayout arka;
    private Random rastgele = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geri_sayim);

        tvZaman = findViewById(R.id.tvZaman);
        arka    = findViewById(R.id.arkaLayout);

        int uretilenSayi = getIntent().getIntExtra("URETILEN_SAYI", 0);
        tvZaman.setText(String.valueOf(uretilenSayi));

        if (uretilenSayi > 0) {
            new CountDownTimer(uretilenSayi * 1000L, 1000) {
                @Override
                public void onTick(long kalanMs) {
                    int kalanSn = (int) (kalanMs / 1000);
                    tvZaman.setText(String.valueOf(kalanSn));
                    arkaPlaniDegistir();
                }

                @Override
                public void onFinish() {
                    tvZaman.setText("0");
                    arkaPlaniDegistir();
                }
            }.start();
        } else {
            tvZaman.setText("0");
            arkaPlaniDegistir();
        }
    }

    private void arkaPlaniDegistir() {
        int renk = Color.argb(255, rastgele.nextInt(256), rastgele.nextInt(256), rastgele.nextInt(256));
        arka.setBackgroundColor(renk);
    }
}
