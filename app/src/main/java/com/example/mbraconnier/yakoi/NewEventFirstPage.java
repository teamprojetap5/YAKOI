package com.example.mbraconnier.yakoi;

import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import javax.xml.datatype.Duration;

/**
 * Created by mbraconnier on 21/01/2018.
 */

public class NewEventFirstPage extends AppCompatActivity {

    private String titre = null;
    private String description = null;
    private Boolean valide = true;

    public String getTitre(){
        return this.titre;
    }

    public void setTitre(String titre){
        this.titre = titre;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event_1);

        final EditText titreEvent = (EditText)findViewById(R.id.TitreEvent);
        final TextView textTitreEvent = (TextView)findViewById(R.id.TextTitreEvent);
        final EditText descriptionEvent = (EditText)findViewById(R.id.descriptionEvent);
        final TextView textDescriptionEvent = (TextView)findViewById(R.id.TextDescriptionEvent);
        final ImageView imageViewClick = (ImageView)findViewById(R.id.buttonValidateNewEvent1);


        imageViewClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(titreEvent.getText() == null || titreEvent.length() <=0){
                    textTitreEvent.setTextColor(getResources().getColor(R.color.error));
                    valide = false;
                }else{
                    textTitreEvent.setTextColor(getResources().getColor(R.color.defaut));
                    setTitre(titreEvent.getText().toString());
                }
                if(descriptionEvent.getText() == null || descriptionEvent.length()<=0){
                    textDescriptionEvent.setTextColor(getResources().getColor(R.color.error));
                    valide = false;
                }else{
                    textDescriptionEvent.setTextColor(getResources().getColor(R.color.defaut));
                    setDescription(descriptionEvent.getText().toString());
                }

                if (valide == false){
                    Toast.makeText(NewEventFirstPage.this," Merci de remplir les champs rouges", Toast.LENGTH_SHORT).show();
                    valide=true;
                }else{
                    Intent intent = new Intent(NewEventFirstPage.this, NewEventSecondPage.class);
                    intent.putExtra("Titre",getTitre());
                    intent.putExtra("Description",getDescription());
                    startActivity(intent);
                }
            }
        });
    }

    public void onRadioButtonClicked(View view) {
    }
}
