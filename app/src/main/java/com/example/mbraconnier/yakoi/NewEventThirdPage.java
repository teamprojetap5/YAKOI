package com.example.mbraconnier.yakoi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * Created by mbraconnier on 21/01/2018.
 */
//TODO NUMERO ACTIVITE XML + FLECHES DEROULANTES
public class NewEventThirdPage extends AppCompatActivity{

    private RadioGroup radioGroupTypeActivite;
    private RadioButton radioButtonActivitePonctuelle;
    private RadioButton radioButtonActiviteReguliere;
    private boolean isChecked;

    private LinearLayout linearTypeActivite;
    private LinearLayout linearTypeActiviteVisibility ;
    private LinearLayout linearContact ;
    private LinearLayout linearContactVisibility ;
    private LinearLayout linearDate ;
    private LinearLayout linearDateVisibility ;
    private LinearLayout linearHoraires;
    private LinearLayout linearHorairesVisibility;
    private TimePicker timePickerDeb;
    private TimePicker timePickerFin;
    private EditText siteWeb;
    private EditText telephone;
    private EditText mail;
    private EditText numero;
    private EditText rue;
    private EditText cp;
    private EditText ville;
    private CalendarView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event_3);

        final String titre = getIntent().getStringExtra("Titre");
        final String description = getIntent().getStringExtra("Description");

        final ImageView imageViewClick = (ImageView)findViewById(R.id.buttonThirdAddUserPageValidate);

        linearTypeActivite = (LinearLayout) findViewById(R.id.LinearTypeActivite);
        linearTypeActiviteVisibility = (LinearLayout) findViewById(R.id.LinearTypeActiviteVisibility);
        linearContact = (LinearLayout) findViewById(R.id.LinearContact);
        linearContactVisibility = (LinearLayout) findViewById(R.id.LinearContactVisibility);
        linearDate = (LinearLayout) findViewById(R.id.LinearDate);
        linearDateVisibility = (LinearLayout) findViewById(R.id.LinearDateVisibility);
        linearHoraires = (LinearLayout) findViewById(R.id.LinearHoraires);
        linearHorairesVisibility = (LinearLayout) findViewById(R.id.LinearHorairesVisibility);
        timePickerDeb = (TimePicker) findViewById(R.id.timePickerHoraireDeb);
        timePickerFin = (TimePicker) findViewById(R.id.timePickerHoraireFin);
        siteWeb = (EditText) findViewById(R.id.ContactSiteWeb);
        telephone = (EditText) findViewById(R.id.ContactTelephone);
        mail = (EditText) findViewById(R.id.ContactMail);
        numero = (EditText) findViewById(R.id.ContactNumero);
        rue = (EditText) findViewById(R.id.ContactRue);
        cp = (EditText) findViewById(R.id.ContactCP);
        ville = (EditText) findViewById(R.id.ContactVille);
        date = (CalendarView) findViewById(R.id.DateActivite);


        timePickerDeb.setIs24HourView(true);
        timePickerFin.setIs24HourView(true);


        linearTypeActivite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearTypeActiviteVisibility.setVisibility(View.VISIBLE);
                linearContactVisibility.setVisibility(View.GONE);
                linearDateVisibility.setVisibility(View.GONE);
                linearHorairesVisibility.setVisibility(View.GONE);

            }
        });

        linearContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearContactVisibility.setVisibility(View.VISIBLE);
                linearTypeActiviteVisibility.setVisibility(View.GONE);
                linearDateVisibility.setVisibility(View.GONE);
                linearHorairesVisibility.setVisibility(View.GONE);
            }
        });

        linearDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearDateVisibility.setVisibility(View.VISIBLE);
                linearContactVisibility.setVisibility(View.GONE);
                linearTypeActiviteVisibility.setVisibility(View.GONE);
                linearHorairesVisibility.setVisibility(View.GONE);
            }
        });

        linearHoraires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearHorairesVisibility.setVisibility(View.VISIBLE);
                linearDateVisibility.setVisibility(View.GONE);
                linearContactVisibility.setVisibility(View.GONE);
                linearTypeActiviteVisibility.setVisibility(View.GONE);
            }
        });

//TODO vérifier que les champs sont remplis

        imageViewClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewEventThirdPage.this, NewEventFourthPage.class);
                intent.putExtra("Titre",titre);
                intent.putExtra("Descriptionn",description);
                radioButtonActivitePonctuelle = (RadioButton) findViewById(R.id.radio_ponctuelle);
                radioButtonActiviteReguliere = (RadioButton) findViewById(R.id.radio_reguliere);
                if(radioButtonActiviteReguliere.isChecked()){
                    intent.putExtra("TypeActivite","régulière");
                }else{
                    intent.putExtra("TypeActivite","ponctuelle");
                }
                intent.putExtra("SiteWeb",siteWeb.getText().toString());
                intent.putExtra("Telephone",telephone.getText().toString());
                intent.putExtra("Mail",mail.getText().toString());
                intent.putExtra("Numero",numero.getText().toString());
                intent.putExtra("Rue",rue.getText().toString());
                intent.putExtra("CP",cp.getText().toString());
                intent.putExtra("Ville",ville.getText().toString());
                intent.putExtra("Date",date.getDate());
                //TODO test timepickerdeb.getBaseLine() et timepickerfin.getBaseLine()
                intent.putExtra("HeureDeb",timePickerDeb.getBaseline());
                intent.putExtra("HeureFin",timePickerFin.getBaseline());
                startActivity(intent);

            }
        });

    }

}
