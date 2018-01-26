package com.example.mbraconnier.yakoi;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mbraconnier on 17/01/2018.
 */

public class AddUserFirstPage extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private EditText fromDateEtxt;
    private EditText toDateEtxt;

    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;

    private SimpleDateFormat dateFormatter;
    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";
    final String EXTRA_MAIL = "user_mail";
    final String EXTRA_BIRTHDAY = "user_birthday";
    final String EXTRA_SEXE = "user_sexe";

    private String loginText ="" ;
    private String dateDeNaissanceText ="" ;
    private String emailText ="" ;

    public int getIdSexeChecked() {
        return IdSexeChecked;
    }

    public void setIdSexeChecked(int idSexeChecked) {
        IdSexeChecked = idSexeChecked;
    }

    private int IdSexeChecked = 0;

    public String getLoginText() {
        return loginText;
    }

    public void setLoginText(String loginText) {
        this.loginText = loginText;
    }

    public String getDateDeNaissanceText() {
        return dateDeNaissanceText;
    }

    public void setDateDeNaissanceText(String dateDeNaissanceText) {
        this.dateDeNaissanceText = dateDeNaissanceText;
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }

    public String getEmailTextConfirmation() {
        return emailTextConfirmation;
    }

    public void setEmailTextConfirmation(String emailTextConfirmation) {
        this.emailTextConfirmation = emailTextConfirmation;
    }

    public String getPassewordConfirmationText() {
        return passewordConfirmationText;
    }

    public void setPassewordConfirmationText(String passewordConfirmationText) {
        this.passewordConfirmationText = passewordConfirmationText;
    }

    public String getPassewordText() {
        return passewordText;
    }

    public void setPassewordText(String passewordText) {
        this.passewordText = passewordText;
    }

    private String emailTextConfirmation ="" ;
    private String passewordConfirmationText ="";
    private String passewordText ="";
    private String SexeText ="";


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setLoginText(savedInstanceState.getString(EXTRA_LOGIN));
        setDateDeNaissanceText(savedInstanceState.getString(EXTRA_BIRTHDAY));
        setEmailText(savedInstanceState.getString(EXTRA_MAIL));
        setEmailTextConfirmation(savedInstanceState.getString(EXTRA_MAIL));
        setPassewordConfirmationText(savedInstanceState.getString(EXTRA_PASSWORD));
        setPassewordText(savedInstanceState.getString(EXTRA_PASSWORD));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.adduserfirstpage);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.FRANCE);

        findViewsById();

        setDateTimeField();

        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
        layout.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideKeyboard(view);
                return false;
            }



        });

        final EditText login = (EditText)findViewById(R.id.LoginAddUser);
        final EditText email = (EditText)findViewById(R.id.email);
        final EditText emailConfirmation = (EditText)findViewById(R.id.emailConfirmation);
        final EditText dateDeNaissance = (EditText)findViewById(R.id.birthday);
        final EditText passeword = (EditText)findViewById(R.id.passwordAddUser);
        final EditText passewordConfiratmion = (EditText)findViewById(R.id.passwordAddUserConfirmation);
        final RadioGroup radioGroup =  (RadioGroup) findViewById(R.id.radioGroup);
        final TextView TextSexe = (TextView)findViewById(R.id.TextSexe);

        if(getLoginText() !=null){
            login.setText(getLoginText());
        }

        if(getDateDeNaissanceText() !=null){
            dateDeNaissance.setText(getDateDeNaissanceText());
        }
        if(getEmailText() !=null){
            email.setText(getEmailText());
        }
        if(getEmailTextConfirmation() !=null){
            emailConfirmation.setText(getEmailText());
        }
        if(getPassewordText() !=null){
            passeword.setText(getPassewordText());
            passewordConfiratmion.setText(getPassewordText());
        }
        if(getIdSexeChecked() != 0){
            radioGroup.check(getIdSexeChecked());
        }


        ImageView imgClick = (ImageView)findViewById(R.id.buttonSecondAddUserPage);

        imgClick.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                setLoginText(login.getText().toString());
                setDateDeNaissanceText(dateDeNaissance.getText().toString());
                setEmailText(email.getText().toString());
                setEmailTextConfirmation(emailConfirmation.getText().toString());
                setPassewordConfirmationText(passewordConfiratmion.getText().toString());
                setPassewordText(passeword.getText().toString());
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();

                String sexeText  ="";

                boolean allvalid = true ;
                // On déclare le pattern que l’on doit vérifier
                Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
                // On déclare un matcher, qui comparera le pattern avec la
                // string passée en argument
                Matcher m = p.matcher(emailText);
                // Si l’adresse mail saisie ne correspond au format d’une
                // adresse mail on un affiche un message à l'utilisateur
                if (checkedRadioButtonId == -1) {
                    allvalid = false ;
                    TextSexe.setTextColor(getResources().getColor(R.color.error));
                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            TextSexe.setTextColor(getResources().getColor(R.color.black));

                        }
                    });
                }
                else{
                    if (checkedRadioButtonId == R.id.radio_femme) {
                        setIdSexeChecked(R.id.radio_femme);
                        SexeText = "F";
                    }
                    if (checkedRadioButtonId == R.id.radio_homme) {
                        setIdSexeChecked(R.id.radio_homme);
                        SexeText = "H";
                    }
                    if (checkedRadioButtonId == R.id.radio_neutre) {
                        setIdSexeChecked(R.id.radio_neutre);
                        SexeText = "N";
                    }
                }


                if(getLoginText().equals("")){
                    login.setHintTextColor(getResources().getColor(R.color.error));
                    allvalid = false ;
                }

                if(getEmailText().equals("")){
                    email.setHintTextColor(getResources().getColor(R.color.error));
                    allvalid = false ;



                } else if (!m.matches()) {
                    email.setTextColor(getResources().getColor(R.color.error));
                    email.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void afterTextChanged(Editable s) {}

                        @Override
                        public void beforeTextChanged(CharSequence s, int start,
                                                      int count, int after) {
                            email.setTextColor(getResources().getColor(R.color.black));
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start,
                                                  int before, int count) {

                        }
                    });


                }
                if(getEmailTextConfirmation().equals("")){
                    allvalid = false ;

                    emailConfirmation.setHintTextColor(getResources().getColor(R.color.error));

                }else if (!getEmailText().equals(getEmailTextConfirmation())){
                    allvalid = false ;
                    emailConfirmation.setHintTextColor(getResources().getColor(R.color.error));
                    email.setTextColor(getResources().getColor(R.color.error));
                    //TastyToast.makeText(getApplicationContext(), "EREUR : Les adresses mail ne corresponde pas !", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                    Toast.makeText(AddUserFirstPage.this, "Erreur: Les adresses mails ne correspondent pas !",Toast.LENGTH_SHORT);
                    emailConfirmation.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void afterTextChanged(Editable s) {}

                        @Override
                        public void beforeTextChanged(CharSequence s, int start,
                                                      int count, int after) {
                            email.setTextColor(getResources().getColor(R.color.black));
                            emailConfirmation.setTextColor(getResources().getColor(R.color.black));
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start,
                                                  int before, int count) {

                        }
                    });

                }
                if(getDateDeNaissanceText().equals("")){
                    allvalid = false ;
                    dateDeNaissance.setHintTextColor(getResources().getColor(R.color.error));

                }
                if(getPassewordText().equals("")){
                    allvalid = false ;
                    passeword.setHintTextColor(getResources().getColor(R.color.error));

                }
                if(getPassewordConfirmationText().equals("")){
                    allvalid = false ;
                    passewordConfiratmion.setHintTextColor(getResources().getColor(R.color.error));
                } else if (!getPassewordConfirmationText().equals(getPassewordText())) {
                    allvalid = false ;
                    //TastyToast.makeText(getApplicationContext(), "ERREUR : Les mots de passe ne correspondent pas !", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                    Toast.makeText(AddUserFirstPage.this, "Erreur: Les mots de passe ne correspondent pas !", Toast.LENGTH_SHORT);

                }


                if(allvalid){
                    Intent intent = new Intent(AddUserFirstPage.this, AddUserSecondPage.class);
                    String hashPassword = "";
                    try {
                        hashPassword = PasswordFormat.getPasswordFormat(getPassewordText(),getLoginText());
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }

                    int yearNow = Calendar.getInstance().get(Calendar.YEAR);
                    intent.putExtra(EXTRA_LOGIN, getLoginText());
                    intent.putExtra(EXTRA_PASSWORD, hashPassword);
                    intent.putExtra(EXTRA_SEXE, SexeText);
                    intent.putExtra(EXTRA_MAIL, getEmailText());
                    int Temp = 15;
                    intent.putExtra(EXTRA_BIRTHDAY, Temp);

                    startActivity(intent);

                }


            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Sauvegarde des données du contexte utilisateur
        outState.putString(EXTRA_LOGIN, getLoginText());
        outState.putString(EXTRA_PASSWORD, getPassewordText());
        outState.putString(EXTRA_MAIL, getEmailText());
        outState.putString(EXTRA_BIRTHDAY, getDateDeNaissanceText());
        outState.putInt(EXTRA_SEXE,getIdSexeChecked());


    }





    private void findViewsById() {
        fromDateEtxt = (EditText) findViewById(R.id.birthday);
        fromDateEtxt.setInputType(InputType.TYPE_NULL);


    }

    private void setDateTimeField() {
        fromDateEtxt.setOnClickListener(this);


        Calendar newCalendar = Calendar.getInstance();
        fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                fromDateEtxt.setText(dateFormatter.format(newDate.getTime()));

            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        toDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                toDateEtxt.setText(dateFormatter.format(newDate.getTime()));

            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    public void onClick(View view) {
        if(view == fromDateEtxt) {
            fromDatePickerDialog.show();
        } else if(view == toDateEtxt) {
            toDatePickerDialog.show();
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }

    protected void hideKeyboard(View view)
    {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
