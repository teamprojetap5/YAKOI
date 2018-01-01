package com.example.mbraconnier.yakoi;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private String jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("Vos 5 choix d'activités");

        Gson gson = new GsonBuilder().create();
        jsonArray = loadJSONFromAsset();
        ActivityObject[] activityObject = gson.fromJson(jsonArray,ActivityObject[].class);

        final View activity1Layout = findViewById(R.id.layoutActivity1);
        final View activity2Layout = findViewById(R.id.layoutActivity2);
        final View activity3Layout = findViewById(R.id.layoutActivity3);
        final View activity4Layout = findViewById(R.id.layoutActivity4);
        final View activity5Layout = findViewById(R.id.layoutActivity5);

        final LinearLayout imageActivite1 = (LinearLayout) findViewById(R.id.layoutActivity1);
        final LinearLayout imageActivite2 = (LinearLayout) findViewById(R.id.layoutActivity2);
        final LinearLayout imageActivite3 = (LinearLayout) findViewById(R.id.layoutActivity3);
        final LinearLayout imageActivite4 = (LinearLayout) findViewById(R.id.layoutActivity4);
        final LinearLayout imageActivite5 = (LinearLayout) findViewById(R.id.layoutActivity5);

        final TextView nomActivite1 = (TextView)findViewById(R.id.activity1_name);
        final TextView nomActivite2 = (TextView)findViewById(R.id.activity2_name);
        final TextView nomActivite3 = (TextView)findViewById(R.id.activity3_name);
        final TextView nomActivite4 = (TextView)findViewById(R.id.activity4_name);
        final TextView nomActivite5 = (TextView)findViewById(R.id.activity5_name);

        final TextView prixActivite1 = (TextView)findViewById(R.id.eurosActivity1);
        final TextView prixActivite2 = (TextView)findViewById(R.id.eurosActivity2);
        final TextView prixActivite3 = (TextView)findViewById(R.id.eurosActivity3);
        final TextView prixActivite4 = (TextView)findViewById(R.id.eurosActivity4);
        final TextView prixActivite5 = (TextView)findViewById(R.id.eurosActivity5);

        final TextView localisationActivite1 = (TextView)findViewById(R.id.localisationActivity1);
        final TextView localisationActivite2 = (TextView)findViewById(R.id.localisationActivity2);
        final TextView localisationActivite3 = (TextView)findViewById(R.id.localisationActivity3);
        final TextView localisationActivite4 = (TextView)findViewById(R.id.localisationActivity4);
        final TextView localisationActivite5 = (TextView)findViewById(R.id.localisationActivity5);


        nomActivite1.setText(activityObject[0].getTitre());
        prixActivite1.setText(activityObject[0].getBudget());
        localisationActivite1.setText(activityObject[0].getVille());
        nomActivite2.setText(activityObject[1].getTitre());
        prixActivite2.setText(activityObject[1].getBudget());
        localisationActivite2.setText(activityObject[1].getVille());
        nomActivite3.setText(activityObject[2].getTitre());
        prixActivite3.setText(activityObject[2].getBudget());
        localisationActivite3.setText(activityObject[2].getVille());
        nomActivite4.setText(activityObject[3].getTitre());
        prixActivite4.setText(activityObject[3].getBudget());
        localisationActivite4.setText(activityObject[3].getVille());
        nomActivite5.setText(activityObject[4].getTitre());
        prixActivite5.setText(activityObject[4].getBudget());
        localisationActivite5.setText(activityObject[4].getVille());

        activity1Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Click activité 1",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DescribeActivity.class);
                intent.putExtra("Choice",0);
                startActivity(intent);
            }
        });
        activity2Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Click activité 2",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DescribeActivity.class);
                intent.putExtra("choice",1);
                startActivity(intent);
            }
        });
        activity3Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"Click activité 3",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DescribeActivity.class);
                intent.putExtra("choice",2);
                startActivity(intent);
            }
        });
        activity4Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DescribeActivity.class);
                intent.putExtra("choice",3);
                startActivity(intent);
            }
        });
        activity5Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DescribeActivity.class);
                intent.putExtra("choice",4);
                startActivity(intent);
            }
        });
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
}
