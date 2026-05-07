package com.example.sayacrenk;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class RenkEkraniActivity extends AppCompatActivity {

    TextView tvGeriSayim;
    ConstraintLayout zemin;
    Random rastgele;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renk_ekrani);

        tvGeriSayim = findViewById(R.id.tvGeriSayim);
        zemin       = findViewById(R.id.zeminLayout);
        rastgele    = new Random();

        int gelenSure = getIntent().getIntExtra("SURE_DEGERI", 10);

        tvGeriSayim.setText(String.valueOf(gelenSure));

        new CountDownTimer(gelenSure * 1000L, 1000) {

            @Override
            public void onTick(long kalanMs) {
                int kalanSn = (int) (kalanMs / 1000);
                tvGeriSayim.setText(String.valueOf(kalanSn));

                int kirmizi = rastgele.nextInt(256);
                int yesil   = rastgele.nextInt(256);
                int mavi    = rastgele.nextInt(256);
                zemin.setBackgroundColor(Color.rgb(kirmizi, yesil, mavi));
            }

            @Override
            public void onFinish() {
                tvGeriSayim.setText("Tamamlandı!");
                zemin.setBackgroundColor(Color.WHITE);
            }

        }.start();
    }
}
