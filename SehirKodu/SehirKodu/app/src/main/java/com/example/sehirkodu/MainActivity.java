package com.example.sehirkodu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String[] ilListesi = {
            "Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa",
            "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan",
            "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta",
            "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir",
            "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla",
            "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt",
            "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak",
            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman",
            "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"
    };

    private List<String> karisikKodlar;
    private int secilenSatir = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvDogru   = findViewById(R.id.lvDogru);
        ListView lvKarisik = findViewById(R.id.lvKarisik);
        Button   btnSorgula = findViewById(R.id.btnSorgula);

        List<String> dogruVeriler  = new ArrayList<>();
        karisikKodlar = new ArrayList<>();

        for (int i = 0; i < ilListesi.length; i++) {
            String kod = String.format("%02d", i + 1);
            dogruVeriler.add(ilListesi[i] + " → " + kod);
            karisikKodlar.add(kod);
        }

        lvDogru.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dogruVeriler));

        List<String> karistirilmis = new ArrayList<>(karisikKodlar);
        Collections.shuffle(karistirilmis);

        List<String> karisikVeriler = new ArrayList<>();
        for (int i = 0; i < ilListesi.length; i++) {
            karisikVeriler.add(ilListesi[i] + " → " + karistirilmis.get(i));
        }

        lvKarisik.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, karisikVeriler));

        lvKarisik.setOnItemClickListener((parent, view, position, id) -> {
            secilenSatir = position;
        });

        btnSorgula.setOnClickListener(v -> {
            if (secilenSatir == -1) {
                Toast.makeText(this, "Önce bir il seçmelisiniz!", Toast.LENGTH_SHORT).show();
                return;
            }

            String secilen = karisikVeriler.get(secilenSatir);
            String ilAdi   = secilen.split(" → ")[0];
            String secilenKod = secilen.split(" → ")[1];

            int ilIndex = -1;
            for (int i = 0; i < ilListesi.length; i++) {
                if (ilListesi[i].equals(ilAdi)) {
                    ilIndex = i;
                    break;
                }
            }
            String dogruKod = String.format("%02d", ilIndex + 1);
            boolean sonuc = secilenKod.equals(dogruKod);

            Intent gecis = new Intent(MainActivity.this, SonucEkraniActivity.class);
            gecis.putExtra("sonuc", sonuc);
            gecis.putExtra("ilAdi", ilAdi);
            gecis.putExtra("dogruKod", dogruKod);
            gecis.putExtra("secilenKod", secilenKod);
            startActivity(gecis);
        });
    }
}
