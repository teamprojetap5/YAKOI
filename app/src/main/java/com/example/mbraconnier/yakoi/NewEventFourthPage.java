package com.example.mbraconnier.yakoi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

/**
 * Created by mbraconnier on 21/01/2018.
 */
//TODO NUMERO ACTIVITE XML + RECUPERATION + ENVOIE IMAGES SERVEURS/APPLICATION
public class NewEventFourthPage extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event_4);

        final String titre = getIntent().getStringExtra("Titre");
        final String description = getIntent().getStringExtra("Descriptionn");
        final String TypeActivite = getIntent().getStringExtra("TypeActivite");
        final String SiteWeb = getIntent().getStringExtra("SiteWeb");
        final String Telephone = getIntent().getStringExtra("Telephone");
        final String Mail = getIntent().getStringExtra("Mail");
        final String Numero = getIntent().getStringExtra("Numero");
        final String Rue = getIntent().getStringExtra("Rue");
        final String CP = getIntent().getStringExtra("CP");
        final String Ville = getIntent().getStringExtra("Ville");
        final String Date = getIntent().getStringExtra("Date");
        final String HeureDeb = getIntent().getStringExtra("HeureDeb");
        final String HeureFin = getIntent().getStringExtra("HeureFin");

        Log.d("Reponses",titre + description + TypeActivite + SiteWeb + Telephone + Mail
                + Numero+ Rue + CP + Ville + Date + HeureDeb + HeureFin);
    }
}
