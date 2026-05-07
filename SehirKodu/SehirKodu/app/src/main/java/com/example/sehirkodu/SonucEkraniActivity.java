package com.example.sehirkodu;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SonucEkraniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc_ekrani);

        TextView tvMesaj   = findViewById(R.id.tvMesaj);
        Button   btnGeri   = findViewById(R.id.btnGeri);

        boolean sonuc     = getIntent().getBooleanExtra("sonuc", false);
        String  ilAdi     = getIntent().getStringExtra("ilAdi");
        String  dogruKod  = getIntent().getStringExtra("dogruKod");
        String  secilenKod = getIntent().getStringExtra("secilenKod");

        String mesaj;
        if (sonuc) {
            mesaj = "DOĞRU!\n\n" + ilAdi + " ilinin kodu " + secilenKod + ". Tebrikler!";
            tvMesaj.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            mesaj = "YANLIŞ!\n\n" + ilAdi + " ilinin kodu " + secilenKod + " değil.\nDoğru kod: " + dogruKod;
            tvMesaj.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }

        tvMesaj.setText(mesaj);
        btnGeri.setOnClickListener(v -> finish());
    }
}
