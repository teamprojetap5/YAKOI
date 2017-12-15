package com.example.mbraconnier.yakoi;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by mbraconnier on 11/12/2017.
 */

public class TestGeolocalisation extends AppCompatActivity{
    private FusedLocationProviderClient mFusedLocationClient;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static double latitude;
    private static double longitude;

    private String jsonObject;


    @Override
    protected void onCreate(Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.test_localisation);
       /* mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(TestGeolocalisation.this, "pas les droits de localisation", Toast.LENGTH_SHORT).show();
            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        }
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null){
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }else{
                    Toast.makeText(TestGeolocalisation.this, "Texte à afficher", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Toast.makeText(TestGeolocalisation.this, "Latitude =" + latitude + " et longitutude = " + longitude, Toast.LENGTH_SHORT).show();*/

        Gson gson = new GsonBuilder().create();
        Log.d("Etape", "Etape 1");
        jsonObject = loadJSONFromAsset();

        Log.d("Etape", "Etape 2");
        ActivityObject activityObject = gson.fromJson(jsonObject,ActivityObject.class);

        Log.d("Etape","Etape 3");
        Log.d("JSON",  activityObject.getTitre());
        Snackbar.make(findViewById(R.id.myCoordinatorLayout),activityObject.getTitre(),Snackbar.LENGTH_SHORT).show();
    }

    protected String loadJSONFromAsset(){
        String json = null;
        try {
            InputStream is = getAssets().open("activity.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer,"UTF-8");
        } catch (IOException ex) {
            Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Impossible de récupérer les activités", Snackbar.LENGTH_LONG).show();
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}