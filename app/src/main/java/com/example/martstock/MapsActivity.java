package com.example.martstock;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.Layer;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle;

import org.json.JSONException;

import java.io.IOException;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng Ireland =new LatLng( 53.1424, -7.6921);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Ireland, 5));
        try {
            GeoJsonLayer layer = new GeoJsonLayer(mMap, R.raw.locations, this);
            GeoJsonPolygonStyle polygonStyle = layer.getDefaultPolygonStyle();
            polygonStyle.setStrokeColor(Color.RED);
            polygonStyle.setStrokeWidth(2);
            layer.addLayerToMap();

            layer.setOnFeatureClickListener(new Layer.OnFeatureClickListener() {
                @Override
                public void onFeatureClick(Feature feature) {
                    Toast.makeText(MapsActivity.this, "Location: "+ feature
                    .getProperty("Name"), Toast.LENGTH_SHORT).show();

                }
            });

            layer.setOnFeatureClickListener(new Layer.OnFeatureClickListener() {
                @Override
                public void onFeatureClick(Feature feature) {
                     final String mart = feature.getProperty("Name");

                    AlertDialog dialog = new AlertDialog.Builder(MapsActivity.this).
                            setTitle(feature.getProperty("Name") + " selected")
                            .setMessage("Do you wish to continue?")
                            .setPositiveButton("Yes", null)
                            .setNegativeButton("Cancel", null)
                            .show();

                    Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(MapsActivity.this,PlaceAd.class);
                            intent.putExtra("martName",mart);
                            startActivity(intent);
                            Toast.makeText(MapsActivity.this, "Location: "+ mart, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
}