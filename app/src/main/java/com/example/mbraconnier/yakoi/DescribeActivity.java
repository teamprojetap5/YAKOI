package com.example.mbraconnier.yakoi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mbraconnier on 06/12/2017.
 */

public class DescribeActivity extends AppCompatActivity {

    private Intent callIntent;
    private Intent goIntent;
    private Intent webIntent;
    private final int REQUEST_PHONE_CALL = 1;
    private final int REQUEST_ACTION_VIEW = 2;
    private String jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Gson gson = new GsonBuilder().create();
        jsonObject = loadJSONFromAsset();
        ActivityObject activityObject = gson.fromJson(jsonObject,ActivityObject.class);

        final Button call_Button = (Button) findViewById(R.id.button_contact);
        final View go_Layout = findViewById(R.id.layoutAdresseActivite);
        final Button website_Button = (Button) findViewById(R.id.button_go);

        final ImageView imageActivite1 = (ImageView) findViewById(R.id.imageActivite1);
        final TextView dateActivite1 = (TextView) findViewById(R.id.dateActivite1);
        final TextView horaireActivite1 = (TextView) findViewById(R.id.horaireActivite1);
        final TextView prixActivite1 = (TextView) findViewById(R.id.prixActivite1);
        final TextView nomPlaceActivite1 = (TextView) findViewById(R.id.nomPlaceActivite1);
        final TextView adresseActivite1 = (TextView) findViewById(R.id.adresseActivite1);
        final TextView environnementActivite1 = (TextView) findViewById(R.id.environnementActivite1);
        final TextView descriptionActivite1 = (TextView) findViewById(R.id.descriptionActivite1);
        final TextView capaciteActivite1 = (TextView) findViewById(R.id.capaciteActivite1);





        final TextView themeActivite1 = (TextView) findViewById(R.id.theme_activite1);
        themeActivite1.setText(activityObject.getTheme());
        dateActivite1.setText(activityObject.getDateAffichage());
        prixActivite1.setText(activityObject.getBudget());
        nomPlaceActivite1.setText(activityObject.getTitre());
        adresseActivite1.setText(activityObject.getNumero() + activityObject.getRue() + "," + activityObject.getCodePostal() + activityObject.getVille());
        environnementActivite1.setText(activityObject.getEnvironnement());
        descriptionActivite1.setText(activityObject.getDescription());
        capaciteActivite1.setText(activityObject.getCapaciteMax());
        horaireActivite1.setText(activityObject.getHoraire());

        String imageName = "R.drawable." + activityObject.getImage();
        //imageActivite1.setImageResource(imageName);




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
        })

        ;


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
