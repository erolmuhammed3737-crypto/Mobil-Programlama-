package com.example.konumuygulamasi;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class AnlikKonumActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap harita;
    private FusedLocationProviderClient konumSaglayici;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anlik_konum);

        konumSaglayici = LocationServices.getFusedLocationProviderClient(this);
        SupportMapFragment haritaFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.haritaAlani);
        haritaFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        harita = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            return;
        }

        harita.setMyLocationEnabled(true);

        konumSaglayici.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location konum) {
                if (konum != null) {
                    LatLng mevcutKonum = new LatLng(konum.getLatitude(), konum.getLongitude());
                    harita.addMarker(new MarkerOptions().position(mevcutKonum).title("Bulunduğunuz Konum"));
                    harita.moveCamera(CameraUpdateFactory.newLatLngZoom(mevcutKonum, 15));
                } else {
                    Toast.makeText(AnlikKonumActivity.this, "Konum tespit edilemedi", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onMapReady(harita);
            } else {
                Toast.makeText(this, "Konum izni verilmedi", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
