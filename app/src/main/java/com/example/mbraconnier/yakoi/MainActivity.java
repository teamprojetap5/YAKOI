package com.example.mbraconnier.yakoi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View activity1Layout = findViewById(R.id.layoutActivity1);
        final View activity2Layout = findViewById(R.id.layoutActivity2);
        final View activity3Layout = findViewById(R.id.layoutActivity3);
        final View activity4Layout = findViewById(R.id.layoutActivity4);
        final View activity5Layout = findViewById(R.id.layoutActivity5);

        activity1Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Click activité 1",Toast.LENGTH_SHORT).show();
            }
        });
        activity2Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Click activité 2",Toast.LENGTH_SHORT).show();
            }
        });
        activity3Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Click activité 3",Toast.LENGTH_SHORT).show();
            }
        });
        activity4Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Click activité 4",Toast.LENGTH_SHORT).show();
            }
        });
        activity5Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Click activité 5",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
