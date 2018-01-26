package com.example.mbraconnier.yakoi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * Created by mbraconnier on 18/01/2018.
 */

public class AddUserSecondPage extends AppCompatActivity {
    private boolean buttonArtBool;
    private boolean buttonSportBool;
    private boolean buttonNatureBool;
    private boolean buttonCultureBool;
    private boolean buttonSpectableBool;
    private boolean buttonDivertissementBool;
    private boolean buttonGastronomieBool;
    private boolean buttonMusiqueBool;
    private boolean buttonModeBool;
    private boolean buttonSelfLifeBool;

    final String SAVEBOOL = "user_saveBool";
    final String SAVEJSON = "user_saveJSONTheme";

    final String EXTRA_SELFLIFEBOOL = "user_seflifebool";
    String jsonString = "[{\"Id\":\"10\",\"Label\":\"Bien \\u00eatre et relaxation\"},{\"Id\":\"4\"," +
            "\"Label\":\"Culture\"},{\"Id\":\"8\",\"Label\":\"Divertissement\"},{\"Id\":\"11\"," +
            "\"Label\":\"Ev\\u00e9nement\"},{\"Id\":\"5\",\"Label\":\"Gastronomie\"},{\"Id\":\"9\"," +
            "\"Label\":\"Mode\"},{\"Id\":\"7\",\"Label\":\"Musique\"},{\"Id\":\"6\"," +
            "\"Label\":\"Nature\"},{\"Id\":\"3\",\"Label\":\"Spectacle\"},{\"Id\":\"1\"," +
            "\"Label\":\"Sport\"}]";
    List<theme> themesList;
    List<String> labelList;
    Hashtable booleanValue = new Hashtable();
    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";
    final String EXTRA_MAIL = "user_mail";
    final String EXTRA_BIRTHDAY = "user_birthday";
    final String EXTRA_SEXE = "user_sexe";
    final String EXTRA_JSON = "user_json";


    public String MonSet = "";


    String login = "";

    String password = "";
    String mail = "";
    int birthday = 15;
    String sexe = "";
    RequestQueue queue;
    int MyId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        queue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        login = intent.getStringExtra(EXTRA_LOGIN);
        password = intent.getStringExtra(EXTRA_PASSWORD);
        sexe = intent.getStringExtra(EXTRA_SEXE);
        mail = intent.getStringExtra(EXTRA_MAIL);
        birthday = intent.getIntExtra(EXTRA_BIRTHDAY, 15);

        System.out.print(jsonString);

        labelList = new LinkedList<String>();

        setTheme();

        Gson gson = new Gson();

        Type founderListType = new TypeToken<ArrayList<theme>>() {
        }.getType();

        themesList = gson.fromJson(jsonString, founderListType);


        setContentView(R.layout.addusersecondpage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewButton);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, this));


        //pour adapter en grille comme une RecyclerView, avec 3 cellules par ligne
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        //puis créer un MyAdapter, lui fournir notre liste de villes.
        //cet adapter servira à remplir notre recyclerview
        recyclerView.setAdapter(new MyAdapterTheme(themesList));
        SharedPreferences settings = getSharedPreferences(SAVEBOOL, 0);
        for (int i = 0; i < themesList.size(); i++) {
            theme MyTheme = themesList.get(i);
            String label = MyTheme.getLabel();
            labelList.add(label);
            boolean Myboolean = settings.getBoolean(label, false);
            booleanValue.put(label, Myboolean);
            if (Myboolean) {
                View myview = findViewById(R.id.myadapteurview);
                Button mybutton =(Button) myview.findViewById(MyTheme.getId());
            }
        }


        ImageView imgClickReturn = (ImageView) findViewById(R.id.previousButtonAddUserSecondPage);


        imgClickReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ImageView imgClickValid = (ImageView) findViewById(R.id.buttonSecondAddUserPage);

        imgClickValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlContact = "http://ec2-52-15-157-231.us-east-2.compute.amazonaws.com/Api_exec.php";

                JSONArray requestParam2 = new JSONArray();
                try {
                    JSONObject requestParam3 = new JSONObject();
                    requestParam3.put("Request", "userAccountCreation");
                    requestParam3.put("Login", login);
                    requestParam3.put("Password" ,password);
                    requestParam3.put("Email" ,mail);
                    requestParam3.put("Age" ,15);
                    requestParam3.put("Sex" ,sexe);
                    JSONArray requestParam4 = new JSONArray();


                    for (int i = 0; i < themesList.size(); i++) {
                        theme MyTheme = themesList.get(i);
                        int id = MyTheme.getId();
                        requestParam4.put(new JSONObject().put("Id", id));
                    }
                    requestParam3.put("Themes", requestParam4);
                    requestParam2.put(requestParam3);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("test",requestParam2.toString());
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
                                    e.printStackTrace();
                                }


                                if (MyId != 0) {
                                    //TastyToast.makeText(getApplicationContext(), "Ton compte est crée ! ", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);
                                    Toast.makeText(AddUserSecondPage.this, "Ton compte est crée !", Toast.LENGTH_SHORT);
                                    Intent intent = new Intent(AddUserSecondPage.this, MenuActivity.class);
                                    startActivity(intent);
                                } else {
                                    //TastyToast.makeText(getApplicationContext(), "Erreur ! ", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                                    Toast.makeText(AddUserSecondPage.this, "Erreur !", Toast.LENGTH_SHORT);

                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                );

                queue.add(jsonObjReq);


            }


        });


    }


    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences settings = getSharedPreferences(SAVEBOOL, 0);
        SharedPreferences.Editor editor = settings.edit();

        for (int i = 0; i < labelList.size(); i++) {
            String MyLabel = labelList.get(i);
            boolean MyBoolean = (boolean) booleanValue.get(MyLabel);
            editor.putBoolean(MyLabel, MyBoolean);
        }


        // Commit the edits!
        editor.apply();
    }

    public void setTheme() {
        String urlContact = "http://ec2-52-15-157-231.us-east-2.compute.amazonaws.com/Api_exec.php";


        JSONArray response = new JSONArray();
        JSONArray requestParam2 = new JSONArray();
        try {
            JSONObject requestParam3 = new JSONObject();
            requestParam3.put("Request", "getThemes");
            requestParam2.put(requestParam3);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {
            JsonArrayRequest jsonObjReq;
            jsonObjReq = new JsonArrayRequest(
                    Request.Method.POST,
                    urlContact,
                    requestParam2,
                    new Response.Listener<JSONArray>() {

                        @Override
                        public void onResponse(JSONArray response) {

                            System.out.println(response.toString());
                            MonSet = response.toString();

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }
            );

            queue.add(jsonObjReq);
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onItemClick(View childView, int childPosition) {

        theme MyTheme = themesList.get(childPosition);
        String label = MyTheme.getLabel();
        Button Mybutton = (Button)findViewById(MyTheme.getId());


        boolean MyBoolean = (boolean) booleanValue.get(label);


        if (!MyBoolean) {
            Mybutton.setBackground(getResources().getDrawable(R.drawable.roundbuttoncheked));
            booleanValue.put(label, true);
        } else {
            Mybutton.setBackground(getResources().getDrawable(R.drawable.roundbutton));
            booleanValue.put(label, false);

        }
    }
}
