package com.example.ejem_googlemaps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final int REQUEST_LOCATION = 135;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mostrarUbicacion();

        // Añadir un marcador en una dirección concreta
        String direccion = "Avenida el Romeral 12, Villabalter, Spain";
        String nombre = "IES San Andres";
        LatLng pos = obtenerPosicion(this, direccion);
        if (pos != null) {
            mMap.addMarker(new MarkerOptions().position(pos).title(nombre));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(pos));
            mMap.setMinZoomPreference(12);
        }
    }

    public void mostrarUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            mMap.setMyLocationEnabled(true);
        }
    }

    public void onRequestPermissionsResult(int rc, @NonNull String[] per, @NonNull int[] res) {
        super.onRequestPermissionsResult(rc, per, res);
        if (rc == REQUEST_LOCATION) {
            if (per.length > 0 && res[0] == PackageManager.PERMISSION_GRANTED) {
                mostrarUbicacion();
            }
        }
    }

    public LatLng obtenerPosicion(Context context, String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng pos = null;
        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address != null) {
                Address location = address.get(0);
                location.getLatitude();
                location.getLongitude();
                pos = new LatLng(location.getLatitude(), location.getLongitude());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return pos;
    }
}