package com.example.sehirgaleri;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String[] tumResimler = {
            "istanbul1", "istanbul2", "istanbul3",
            "ankara1", "ankara2", "ankara3",
            "izmir1", "izmir2", "izmir3"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView onizleme     = findViewById(R.id.onizleme);
        RadioGroup sehirSecimi = findViewById(R.id.sehirSecimi);
        Button btnGoster       = findViewById(R.id.btnGoster);

        // Rastgele resim göster
        int rastgeleIndex = new Random().nextInt(tumResimler.length);
        String rastgeleResim = tumResimler[rastgeleIndex];
        int resId = getResources().getIdentifier(rastgeleResim, "drawable", getPackageName());
        if (resId != 0) {
            onizleme.setImageResource(resId);
        }

        btnGoster.setOnClickListener(v -> {
            int seciliId = sehirSecimi.getCheckedRadioButtonId();
            RadioButton seciliRadio = findViewById(seciliId);
            String seciliSehir = seciliRadio.getText().toString();

            Intent gecis = new Intent(MainActivity.this, SehirDetayActivity.class);
            gecis.putExtra("SECILEN_SEHIR", seciliSehir);
            startActivity(gecis);
        });
    }
}
