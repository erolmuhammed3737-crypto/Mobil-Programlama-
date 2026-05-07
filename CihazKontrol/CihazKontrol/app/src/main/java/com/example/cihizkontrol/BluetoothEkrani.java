package com.example.cihizkontrol;

import androidx.appcompat.app.AppCompatActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import java.util.Set;

public class BluetoothEkrani extends AppCompatActivity {

    private static final int BT_IZIN_KODU = 10;
    Button btnAc, btnKapat, btnListele, btnGorunur;
    private BluetoothAdapter btAdaptor;
    private Set<BluetoothDevice> eslestirilmisler;
    ListView cihazListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_ekrani);

        btnAc      = (Button) findViewById(R.id.btnAc);
        btnKapat   = (Button) findViewById(R.id.btnKapat);
        btnListele = (Button) findViewById(R.id.btnListele);
        btnGorunur = (Button) findViewById(R.id.btnGorunur);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            BluetoothManager btYonetici = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            btAdaptor = btYonetici.getAdapter();
        } else {
            btAdaptor = BluetoothAdapter.getDefaultAdapter();
        }

        cihazListesi = (ListView) findViewById(R.id.cihazListesi);

        if (btAdaptor == null) {
            Toast.makeText(getApplicationContext(), "Bu cihaz Bluetooth desteklemiyor", Toast.LENGTH_LONG).show();
            finish();
        } else {
            izinleriKontrolEt();
            butonlariTanimla();
        }
    }

    private void izinleriKontrolEt() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_ADVERTISE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{
                        android.Manifest.permission.BLUETOOTH_CONNECT,
                        android.Manifest.permission.BLUETOOTH_SCAN,
                        android.Manifest.permission.BLUETOOTH_ADVERTISE
                }, BT_IZIN_KODU);
            }
        }
    }

    private void butonlariTanimla() {
        btnAc.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { bluetoothAc(v); }
        });
        btnKapat.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { bluetoothKapat(v); }
        });
        btnListele.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { cihazlariGoster(v); }
        });
        btnGorunur.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { gorunurYap(v); }
        });
    }

    public void bluetoothAc(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                izinleriKontrolEt(); return;
            }
        }
        if (!btAdaptor.isEnabled()) {
            Intent acIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(acIntent, 0);
            Toast.makeText(getApplicationContext(), "Bluetooth açılıyor...", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth zaten açık", Toast.LENGTH_LONG).show();
        }
    }

    public void bluetoothKapat(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                izinleriKontrolEt(); return;
            }
        }
        if (btAdaptor.isEnabled()) {
            Toast.makeText(getApplicationContext(), "Kapatmak için ayarlara gidiniz.", Toast.LENGTH_LONG).show();
            Intent ayarlar = new Intent();
            ayarlar.setAction(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS);
            startActivity(ayarlar);
        } else {
            Toast.makeText(getApplicationContext(), "Bluetooth zaten kapalı", Toast.LENGTH_LONG).show();
        }
    }

    public void cihazlariGoster(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                izinleriKontrolEt(); return;
            }
        }
        if (btAdaptor.isEnabled()) {
            eslestirilmisler = btAdaptor.getBondedDevices();
            ArrayList<String> liste = new ArrayList<>();
            for (BluetoothDevice cihaz : eslestirilmisler) {
                liste.add(cihaz.getName() + "\n" + cihaz.getAddress());
            }
            if (liste.size() > 0) {
                final ArrayAdapter<String> adaptor = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, liste);
                cihazListesi.setAdapter(adaptor);
            } else {
                Toast.makeText(getApplicationContext(), "Eşleşmiş cihaz bulunamadı", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Önce Bluetooth'u açınız.", Toast.LENGTH_LONG).show();
        }
    }

    public void gorunurYap(View v) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_ADVERTISE) != PackageManager.PERMISSION_GRANTED) {
                izinleriKontrolEt(); return;
            }
        }
        Intent gorunurIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(gorunurIntent, 0);
        Toast.makeText(getApplicationContext(), "Görünürlük isteği gönderildi", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == BT_IZIN_KODU) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Bluetooth izinleri verildi.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Bluetooth izinleri reddedildi.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
