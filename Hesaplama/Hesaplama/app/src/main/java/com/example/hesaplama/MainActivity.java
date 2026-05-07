package com.example.hesaplama;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvSonuc;
    private double solTaraf = Double.NaN;
    private double sagTaraf;
    private char aktifIslem = '0';
    private boolean yeniSayiBekle = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSonuc = findViewById(R.id.tvSonuc);

        // Rakam listener'ı
        View.OnClickListener rakamListener = v -> {
            if (yeniSayiBekle) {
                tvSonuc.setText("");
                yeniSayiBekle = false;
            }
            Button b = (Button) v;
            tvSonuc.append(b.getText().toString());
        };

        findViewById(R.id.rakam0).setOnClickListener(rakamListener);
        findViewById(R.id.rakam1).setOnClickListener(rakamListener);
        findViewById(R.id.rakam2).setOnClickListener(rakamListener);
        findViewById(R.id.rakam3).setOnClickListener(rakamListener);
        findViewById(R.id.rakam4).setOnClickListener(rakamListener);
        findViewById(R.id.rakam5).setOnClickListener(rakamListener);
        findViewById(R.id.rakam6).setOnClickListener(rakamListener);
        findViewById(R.id.rakam7).setOnClickListener(rakamListener);
        findViewById(R.id.rakam8).setOnClickListener(rakamListener);
        findViewById(R.id.rakam9).setOnClickListener(rakamListener);

        // Operatör listener'ı
        View.OnClickListener islemListener = v -> {
            Button b = (Button) v;
            String islem = b.getText().toString();
            String mevcutDeger = tvSonuc.getText().toString();

            if (mevcutDeger.isEmpty() && aktifIslem == '0') return;

            if (!Double.isNaN(solTaraf) && !yeniSayiBekle) {
                sagTaraf = Double.parseDouble(mevcutDeger);
                double sonuc = islemYap(solTaraf, sagTaraf, aktifIslem);
                solTaraf = sonuc;
                ekranaYaz(sonuc);
            } else if (!mevcutDeger.isEmpty()) {
                solTaraf = Double.parseDouble(mevcutDeger);
            }

            aktifIslem = islem.charAt(0);
            yeniSayiBekle = true;
        };

        findViewById(R.id.islemTopla).setOnClickListener(islemListener);
        findViewById(R.id.islemCikar).setOnClickListener(islemListener);
        findViewById(R.id.islemCarp).setOnClickListener(islemListener);
        findViewById(R.id.islemBol).setOnClickListener(islemListener);

        // Eşittir
        findViewById(R.id.btnEsit).setOnClickListener(v -> {
            if (!Double.isNaN(solTaraf) && !yeniSayiBekle) {
                sagTaraf = Double.parseDouble(tvSonuc.getText().toString());
                double sonuc = islemYap(solTaraf, sagTaraf, aktifIslem);
                solTaraf = Double.NaN;
                aktifIslem = '0';
                ekranaYaz(sonuc);
                yeniSayiBekle = true;
            }
        });

        // Temizle
        findViewById(R.id.btnTemizle).setOnClickListener(v -> {
            solTaraf = Double.NaN;
            sagTaraf = Double.NaN;
            aktifIslem = '0';
            tvSonuc.setText("");
            yeniSayiBekle = true;
        });
    }

    private double islemYap(double a, double b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case 'x': return a * b;
            case '/': return b == 0 ? 0 : a / b;
            default:  return a;
        }
    }

    private void ekranaYaz(double deger) {
        if (deger == (long) deger) {
            tvSonuc.setText(String.valueOf((long) deger));
        } else {
            tvSonuc.setText(String.valueOf(deger));
        }
    }
}
