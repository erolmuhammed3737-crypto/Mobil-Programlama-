package com.example.konumuygulamasi;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class BelirliKonumActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap harita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_belirli_konum);

        SupportMapFragment haritaFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.haritaAlani);
        haritaFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        harita = googleMap;

        LatLng ankaraGari = new LatLng(39.9052, 32.8595);
        harita.addMarker(new MarkerOptions().position(ankaraGari).title("Ankara Garı"));
        harita.moveCamera(CameraUpdateFactory.newLatLngZoom(ankaraGari, 15));
    }
}
