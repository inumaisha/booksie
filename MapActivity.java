package com.example.booksie;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap map;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng Waterstones = new LatLng(52.479941, -1.895063);
        map.addMarker(new MarkerOptions().position(Waterstones).title("Waterstones"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Waterstones));
        map.getMaxZoomLevel();

        LatLng bhamLib = new LatLng(52.479854821855554, -1.9085258577363438);
        map.addMarker(new MarkerOptions().position(bhamLib).title("Library of Birmingham"));
        map.moveCamera(CameraUpdateFactory.newLatLng(bhamLib));

        LatLng forbiddenPlanet = new LatLng(52.48185015586904, -1.8963888241711109);
        map.addMarker(new MarkerOptions().position(forbiddenPlanet).title("Forbidden Planet"));
        map.moveCamera(CameraUpdateFactory.newLatLng(forbiddenPlanet));
        LatLng worldApart = new LatLng(52.475726413067626, -1.8992002020820231);
        map.addMarker(new MarkerOptions().position(worldApart).title("Worlds Apart"));
        map.moveCamera(CameraUpdateFactory.newLatLng(worldApart));

        LatLng astonLib = new LatLng(52.48637130429127, -1.8880510928586756);
        map.addMarker(new MarkerOptions().position(astonLib).title("Aston University Library"));
        map.moveCamera(CameraUpdateFactory.newLatLng(astonLib));
    }



}