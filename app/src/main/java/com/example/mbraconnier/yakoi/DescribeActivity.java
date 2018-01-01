package com.example.mbraconnier.yakoi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
    private String jsonArray;
    private String accessibilites[];
    private String paiement[];
    private Button call_Button;
    private View go_Layout;
    private Button website_Button;

    private ImageView imageActivite1;
    private TextView themeActivite1;
    private TextView dateActivite1;
    private TextView horaireActivite1;
    private TextView prixActivite1;
    private TextView nomPlaceActivite1;
    private TextView adresseActivite1;
    private Button exterieurActivite;
    private Button interieurActivite;
    private Button pmrActivite;
    private Button malentendantActivite;
    private Button malvoyantActivite;
    private Button enfantActivite;
    private Button ageeActivite;
    private Button capaciteActivite;
    private Button cbActivite;
    private Button paypalActivite;
    private Button especesActivites;
    private Button chequeActivites;
    private TextView descriptionActivite1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        final int choice = getIntent().getIntExtra("choice",0);

        Gson gson = new GsonBuilder().create();
        jsonArray = loadJSONFromAsset();
        final ActivityObject[] activityObject = gson.fromJson(jsonArray, ActivityObject[].class);
        setMaj(choice, activityObject);
        /**
         * Déclaration des méthodes lors du clic sur le bouton d'appel
         */
        call_Button = (Button) findViewById(R.id.button_contact);
        call_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + activityObject[choice].getTelephone()));

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
        go_Layout = findViewById(R.id.layoutAdresseActivite);
        go_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goIntent = new Intent(Intent.ACTION_VIEW);
                //goIntent.setData(Uri.parse("google.navigation:q="+Uri.encode("Apple Cupertino")));
                goIntent.setData(Uri.parse("geo:0,0?q=" + Uri.encode(adresseActivite1.getText().toString())));
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
        website_Button = (Button) findViewById(R.id.button_go);
        website_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + activityObject[choice].getSite()));
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
            //TODO créer myCoordinatorLayout
            Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Impossible de récupérer les activités", Snackbar.LENGTH_LONG).show();
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    protected void setMaj(int number, ActivityObject[] activityObject){
        this.setTitle(activityObject[number].getTitre());
        LinearLayout buttonContainer = (LinearLayout) findViewById(R.id.buttonContainer);

        themeActivite1 = (TextView) findViewById(R.id.theme_activite1);
        imageActivite1 = (ImageView) findViewById(R.id.imageActivite1);
        dateActivite1 = (TextView) findViewById(R.id.dateActivite1);
        horaireActivite1 = (TextView) findViewById(R.id.horaireActivite1);
        prixActivite1 = (TextView) findViewById(R.id.prixActivite1);
        nomPlaceActivite1 = (TextView) findViewById(R.id.nomPlaceActivite1);
        adresseActivite1 = (TextView) findViewById(R.id.adresseActivite1);


        descriptionActivite1 = (TextView) findViewById(R.id.descriptionActivite1);

        themeActivite1.setText(activityObject[number].getTheme());
        dateActivite1.setText(activityObject[number].getDateAffichage());
        prixActivite1.setText(activityObject[number].getBudget());
        nomPlaceActivite1.setText(activityObject[number].getTitre());
        if(activityObject[number].getNumero() == -1){
            adresseActivite1.setText(activityObject[number].getRue() + ", " + activityObject[number].getCodePostal() + " " + activityObject[number].getVille());
        }else{
            adresseActivite1.setText(activityObject[number].getNumero() + " " + activityObject[number].getRue() + ", " + activityObject[number].getCodePostal() + " " + activityObject[number].getVille());
        }
        descriptionActivite1.setText(activityObject[number].getDescription());
        horaireActivite1.setText(activityObject[number].getHoraire());


        Button buttonCapacite = new Button(this);

        buttonCapacite.setText(activityObject[number].getCapaciteMax() + " Personnes");
        buttonContainer.addView(buttonCapacite);


        accessibilites = activityObject[number].getAccessibilite();
        for(int i = 0;i<accessibilites.length;i++){
            Button buttonAccessibilite = new Button(this);
            buttonAccessibilite.setText(accessibilites[i]);
            buttonContainer.addView(buttonAccessibilite);
        }

        paiement = activityObject[number].getPaiement();
        for(int i = 0; i<paiement.length;i++){
            Button buttonPaiement = new Button(this);
            buttonPaiement.setText(paiement[i]);
            buttonContainer.addView(buttonPaiement);
        }

        Button buttonEnvironnement = new Button(this);

        if(activityObject[number].getEnvironnement().equals("Les deux") ){
            buttonEnvironnement.setText("Intérieur & Extérieur");
        }else{
            buttonEnvironnement.setText(activityObject[number].getEnvironnement());
        }
        buttonContainer.addView(buttonEnvironnement);

        String imageName = "R.drawable." + activityObject[number].getImage();
    }

}
