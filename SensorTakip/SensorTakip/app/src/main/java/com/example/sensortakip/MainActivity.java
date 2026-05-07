package com.example.sensortakip;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butonlariTanimla();
    }

    private void butonlariTanimla() {
        Button btnIvme      = findViewById(R.id.btnIvme);
        Button btnPusula    = findViewById(R.id.btnPusula);
        Button btnJiroskop  = findViewById(R.id.btnJiroskop);
        Button btnNem       = findViewById(R.id.btnNem);
        Button btnIsik      = findViewById(R.id.btnIsik);
        Button btnManyetik  = findViewById(R.id.btnManyetik);
        Button btnBasinc    = findViewById(R.id.btnBasinc);
        Button btnYakinlik  = findViewById(R.id.btnYakinlik);
        Button btnSicaklik  = findViewById(R.id.btnSicaklik);

        btnIvme.setOnClickListener(v     -> startActivity(new Intent(this, IvmeActivity.class)));
        btnPusula.setOnClickListener(v   -> startActivity(new Intent(this, PusulaActivity.class)));
        btnJiroskop.setOnClickListener(v -> startActivity(new Intent(this, JiroskopActivity.class)));
        btnNem.setOnClickListener(v      -> startActivity(new Intent(this, NemActivity.class)));
        btnIsik.setOnClickListener(v     -> startActivity(new Intent(this, IsikActivity.class)));
        btnManyetik.setOnClickListener(v -> startActivity(new Intent(this, ManyetikActivity.class)));
        btnBasinc.setOnClickListener(v   -> startActivity(new Intent(this, BasincActivity.class)));
        btnYakinlik.setOnClickListener(v -> startActivity(new Intent(this, YakinlikActivity.class)));
        btnSicaklik.setOnClickListener(v -> startActivity(new Intent(this, SicaklikActivity.class)));
    }
}
