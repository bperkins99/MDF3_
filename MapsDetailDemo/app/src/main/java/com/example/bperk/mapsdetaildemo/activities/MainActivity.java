package com.example.bperk.mapsdetaildemo.activities;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bperk.mapsdetaildemo.R;
import com.example.bperk.mapsdetaildemo.utilities.FileHelper;
import com.example.bperk.mapsdetaildemo.utilities.PhotoInfo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final int ERROR_DIALOG_REQUEST = 9001;

    GoogleMap mMap;
    PhotoInfo photoInfo;
    ArrayList<PhotoInfo> photoInfoList;
    FileHelper fileHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (servicesOk()){
            setContentView(R.layout.fragment_map_display);
            initMap();
        } else {
            setContentView(R.layout.activity_main);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.form_action) {
            Intent intent = new Intent(this, FormActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean initMap(){
        if (mMap == null){
            MapFragment mapFragment =
                    (MapFragment) getFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        return (mMap != null);
    }


    public boolean servicesOk() {
        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)){
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "Cant Connect to map", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    public void loadMapData(){
        for (int i=0; i<photoInfo.size(); i++){
            mMap.addMarker(new MarkerOptions().)
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(sydney, 10);
        mMap.moveCamera(update);
    }
}
