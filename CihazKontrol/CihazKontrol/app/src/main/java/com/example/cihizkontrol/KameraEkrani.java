package com.example.cihizkontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class KameraEkrani extends AppCompatActivity {

    private static final int VIDEO_ISTEK  = 201;
    private static final int FOTOGRAF_ISTEK = 202;
    private static final int KAMERA_IZIN  = 200;

    private Button btnFotograf, btnVideo;
    private ImageView fotografGoster;
    private VideoView videoGoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamera_ekrani);

        btnFotograf   = findViewById(R.id.btnFotograf);
        btnVideo      = findViewById(R.id.btnVideo);
        fotografGoster = findViewById(R.id.fotografGoster);
        videoGoster   = findViewById(R.id.videoGoster);

        btnFotograf.setOnClickListener(v -> izinKontrolVeBaslat(FOTOGRAF_ISTEK));
        btnVideo.setOnClickListener(v -> izinKontrolVeBaslat(VIDEO_ISTEK));
    }

    private void izinKontrolVeBaslat(int istek) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, KAMERA_IZIN);
        } else {
            if (istek == FOTOGRAF_ISTEK) {
                fotografCek();
            } else {
                videoCek();
            }
        }
    }

    private void fotografCek() {
        Intent fotografIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(fotografIntent, FOTOGRAF_ISTEK);
    }

    private void videoCek() {
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(videoIntent, VIDEO_ISTEK);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == KAMERA_IZIN) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Kamera izni verildi.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Kamera izni gereklidir.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) return;

        switch (requestCode) {
            case VIDEO_ISTEK:
                videoGoster.setVisibility(View.VISIBLE);
                fotografGoster.setVisibility(View.GONE);
                videoGoster.setVideoURI(data.getData());
                videoGoster.setMediaController(new MediaController(this));
                videoGoster.requestFocus();
                videoGoster.start();
                break;
            case FOTOGRAF_ISTEK:
                fotografGoster.setVisibility(View.VISIBLE);
                videoGoster.setVisibility(View.GONE);
                Bitmap foto = (Bitmap) data.getExtras().get("data");
                fotografGoster.setImageBitmap(foto);
                break;
        }
    }
}
