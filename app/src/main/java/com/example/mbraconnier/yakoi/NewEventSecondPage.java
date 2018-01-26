package com.example.mbraconnier.yakoi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by mbraconnier on 21/01/2018.
 */

public class NewEventSecondPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event_2);

        final String titre = getIntent().getStringExtra("Titre");
        final String description = getIntent().getStringExtra("Description");



        final ImageView imageViewClick = (ImageView)findViewById(R.id.buttonSecondAddUserPageValidate);

        imageViewClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewEventSecondPage.this, NewEventThirdPage.class);
                intent.putExtra("Titre",titre);
                intent.putExtra("Description",description);
                startActivity(intent);
            }
        });
    }
}
