package com.example.mbraconnier.yakoi;

/**
 * Created by mbraconnier on 17/01/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;


public class SecondMain extends AppCompatActivity {
    RequestQueue queue;
    int MyId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_main);
        //Button boutonConnect = findViewById(R.id.connectionButton);
        Button boutonConnect = (Button) findViewById(R.id.connectionButton);
        Button boutonAddUser = (Button) findViewById(R.id.buttonNouveauCompte);
        final EditText login = (EditText) findViewById(R.id.Login);
        final EditText password = (EditText) findViewById(R.id.Password);

        queue = Volley.newRequestQueue(this);

        SharedPreferences preferences = getSharedPreferences("user_saveBool", 0);
        preferences.edit().clear().commit();
        ;


        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyboard(view);
                return false;
            }


        });


        boutonAddUser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecondMain.this, AddUserFirstPage.class);
                startActivity(intent);


            }
        });


        boutonConnect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(SecondMain.this, EcranSelection.class);
                //startActivity(intent);
                String passewordText = password.getText().toString();
                String loginText = login.getText().toString();
                String hashPassword = "";
                if (passewordText.equals("") && loginText.equals("")) {
                    //TastyToast.makeText(getApplicationContext(), "EREUR : Login et mot de passe vide ! ", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                    Log.d("Test", "Erreur1");
                    Toast.makeText(SecondMain.this, "Erreur: Login et mot de passe vides!", Toast.LENGTH_SHORT).show();
                } else if (loginText.equals("")) {
                    //TastyToast.makeText(getApplicationContext(), "EREUR : Login vide ! ", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                    Toast.makeText(SecondMain.this, "Erreur: Login vide !", Toast.LENGTH_SHORT).show();
                    Log.d("Test", "Erreur2");

                } else if (passewordText.equals("")) {
                    //TastyToast.makeText(getApplicationContext(), "EREUR : mot de passe vide ! ", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                    Toast.makeText(SecondMain.this, "Erreur: Mot de passe vide!", Toast.LENGTH_SHORT).show();
                    Log.d("Test", "Erreur3");

                } else {
                    try {
                        hashPassword = PasswordFormat.getPasswordFormat(passewordText, loginText);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }

                    String urlContact = "http://ec2-52-15-157-231.us-east-2.compute.amazonaws.com/Api_exec.php";


                    JSONArray requestParam2 = new JSONArray();
                    try {
                        JSONObject requestParam3 = new JSONObject();
                        requestParam3.put("Request", "connection");
                        requestParam3.put("Login", loginText);
                        requestParam3.put("Password", hashPassword);
                        //TODO log
                        requestParam3.put("logs", "true");
                        requestParam2.put(requestParam3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("Erreur",requestParam2.toString());


                    JsonArrayRequest jsonObjReq;
                    jsonObjReq = new JsonArrayRequest(
                            Request.Method.POST,
                            urlContact,
                            requestParam2,
                            new Response.Listener<JSONArray>() {

                                @Override
                                public void onResponse(JSONArray response) {

                                    try {
                                        JSONObject o = response.getJSONObject(0);
                                        MyId = o.getInt("Id");
                                    } catch (JSONException e) {
                                        Log.d("ERREUR", "ERREUR DE CONNEXION 2");
                                        e.printStackTrace();
                                    }


                                    //int test = response.getInt(0);


                                    if (MyId != 0) {
                                        //TastyToast.makeText(getApplicationContext(), "Connecté", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
                                        Intent intent = new Intent(SecondMain.this, EcranSelection.class);
                                        startActivity(intent);
                                    } else {
                                        //TastyToast.makeText(getApplicationContext(), "EREUR : identifiant incorect ! ", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                                        Toast.makeText(SecondMain.this, "Erreur: Identifiants incorrects !", Toast.LENGTH_SHORT).show();

                                    }


                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(SecondMain.this, "Erreur de connexion, vérifiez votre connexion internet", Toast.LENGTH_LONG).show();
                                    Log.d("ERREUR",error.toString());
                                }
                            }
                    );

                    queue.add(jsonObjReq);


                }

            }
        });
    }

    protected void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
