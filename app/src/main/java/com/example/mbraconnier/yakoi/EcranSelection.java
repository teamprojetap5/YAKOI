package com.example.mbraconnier.yakoi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mbraconnier on 18/01/2018.
 */

public class EcranSelection extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecranselection);
        getSupportActionBar().setElevation(0);

        final View ActivitySearch = findViewById(R.id.layoutActivity1);

        final View ActivityCreate = findViewById(R.id.layoutActivity2);

        ActivitySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(EcranSelection.this,"Click détecté", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EcranSelection.this, MainActivity.class);
                intent.putExtra("Choice",0);
                startActivity(intent);
            }
        });

        ActivityCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(EcranSelection.this,"Click détecté", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EcranSelection.this, NewEventFirstPage.class);
                //intent.putExtra("Choice",0);
                startActivity(intent);
            }
        });
    }
}
