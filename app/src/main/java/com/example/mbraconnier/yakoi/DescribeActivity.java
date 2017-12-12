package com.example.mbraconnier.yakoi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by mbraconnier on 06/12/2017.
 */

public class DescribeActivity extends AppCompatActivity {

    private Intent callIntent;
    private Intent goIntent;
    private Intent webIntent;
    private final int REQUEST_PHONE_CALL = 1;
    private final int REQUEST_ACTION_VIEW = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        final Button call_Button = (Button) findViewById(R.id.button_contact);
        final View go_Layout = findViewById(R.id.layoutAdresseActivite);
        final Button website_Button = (Button) findViewById(R.id.button_go);

        /**
         * Déclaration des méthodes lors du clic sur le bouton d'appel
         */
        call_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0637752550"));

                if (ActivityCompat.checkSelfPermission(
                        DescribeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(DescribeActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
                } else {

                    startActivity(callIntent);
                }
                //Toast.makeText(DescribeActivity.this, "Appel bouton appel", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * Déclaration des méthodes lors du clic sur le linear_layout de l'adresse
         */
        go_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goIntent = new Intent(Intent.ACTION_VIEW);
                //goIntent.setData(Uri.parse("google.navigation:q="+Uri.encode("Apple Cupertino")));
                goIntent.setData(Uri.parse("geo:0,0?q="+Uri.encode("Stadium nord Lille Métropole, Lille")));
                goIntent.setPackage("com.google.android.apps.maps");

                if (ActivityCompat.checkSelfPermission(
                        DescribeActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(DescribeActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ACTION_VIEW);
                } else {

                    startActivity(goIntent);
                }
            }
        });

        /**
         * Déclaration méthodes lors du clic sur le bouton j'y vais
         */

        website_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.stadium-lillemetropole.fr/"));
                startActivity(webIntent);
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int [] grantResults){
        switch (requestCode){
            case REQUEST_PHONE_CALL:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startActivity(callIntent);
                }else{
                    Toast.makeText(DescribeActivity.this, "Vous n'avez pas donné les droits pour passer un appel ", Toast.LENGTH_SHORT).show();
                }
                return ;
            }
            case REQUEST_ACTION_VIEW:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startActivity(goIntent);
                }else{
                    Toast.makeText(DescribeActivity.this, "Vous n'avez pas donné les droits d'accès à la carte", Toast.LENGTH_SHORT).show();
                }
                return ;
            }
        }
    }





}
