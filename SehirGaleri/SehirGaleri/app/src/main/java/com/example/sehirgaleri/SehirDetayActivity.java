package com.example.sehirgaleri;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SehirDetayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sehir_detay);

        TextView tvSehirAdi = findViewById(R.id.tvSehirAdi);
        ImageView foto1     = findViewById(R.id.foto1);
        ImageView foto2     = findViewById(R.id.foto2);
        ImageView foto3     = findViewById(R.id.foto3);
        TextView tvBilgi1   = findViewById(R.id.tvBilgi1);
        TextView tvBilgi2   = findViewById(R.id.tvBilgi2);
        TextView tvBilgi3   = findViewById(R.id.tvBilgi3);
        Button btnGeri      = findViewById(R.id.btnGeri);

        String sehir = getIntent().getStringExtra("SECILEN_SEHIR");
        tvSehirAdi.setText(sehir);

        if ("İstanbul".equals(sehir)) {
            foto1.setImageResource(getResources().getIdentifier("istanbul1", "drawable", getPackageName()));
            foto2.setImageResource(getResources().getIdentifier("istanbul2", "drawable", getPackageName()));
            foto3.setImageResource(getResources().getIdentifier("istanbul3", "drawable", getPackageName()));
            tvBilgi1.setText("İstanbul, Türkiye'nin en kalabalık şehri olup iki kıtayı birbirine bağlayan eşsiz coğrafi konumuyla dünyada benzersiz bir yere sahiptir.");
            tvBilgi2.setText("Boğaziçi Köprüsü ile Fatih Sultan Mehmet Köprüsü, Avrupa yakasını Anadolu yakasına bağlayan İstanbul'un simgeleri arasındadır.");
            tvBilgi3.setText("Topkapı Sarayı, Ayasofya ve Kapalıçarşı gibi tarihi yapılarıyla İstanbul, her yıl milyonlarca turisti ağırlamaktadır.");
        } else if ("Ankara".equals(sehir)) {
            foto1.setImageResource(getResources().getIdentifier("ankara1", "drawable", getPackageName()));
            foto2.setImageResource(getResources().getIdentifier("ankara2", "drawable", getPackageName()));
            foto3.setImageResource(getResources().getIdentifier("ankara3", "drawable", getPackageName()));
            tvBilgi1.setText("Ankara, 1923'te Türkiye Cumhuriyeti'nin başkenti ilan edilen ve Orta Anadolu'nun kalbinde yer alan modern bir şehirdir.");
            tvBilgi2.setText("Atatürk'ün huzurunda yükselen Anıtkabir, Ankara'nın en önemli anıtı olup her yıl milyonlarca ziyaretçi tarafından gezilmektedir.");
            tvBilgi3.setText("Ankara Kalesi, şehrin tarihsel geçmişine ışık tutan ve Hititlerden bu yana kesintisiz iskân edilen önemli bir yapıdır.");
        } else if ("İzmir".equals(sehir)) {
            foto1.setImageResource(getResources().getIdentifier("izmir1", "drawable", getPackageName()));
            foto2.setImageResource(getResources().getIdentifier("izmir2", "drawable", getPackageName()));
            foto3.setImageResource(getResources().getIdentifier("izmir3", "drawable", getPackageName()));
            tvBilgi1.setText("İzmir, Ege kıyısında kurulu, canlı ve kozmopolit yapısıyla Türkiye'nin üçüncü büyük şehri olma özelliğini korumaktadır.");
            tvBilgi2.setText("Tarihi Saat Kulesi, İzmir'in Konak Meydanı'nda yükselen ve şehrin sembolü haline gelmiş 1901 yılına ait bir yapıdır.");
            tvBilgi3.setText("Efes Antik Kenti, İzmir'e yakın konumuyla dünyanın en iyi korunmuş antik kentlerinden biri sayılmakta ve UNESCO listesinde yer almaktadır.");
        }

        btnGeri.setOnClickListener(v -> finish());
    }
}
