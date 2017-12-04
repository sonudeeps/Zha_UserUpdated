package com.velmurugan.nadus.politician.LoginSignup;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.velmurugan.nadus.politician.Adapters.SignUpAdapter;
import com.velmurugan.nadus.politician.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import me.anwarshahriar.calligrapher.Calligrapher;

public class Signup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Calligrapher calligrapher;
    EditText input_name,input_password,input_dob,input_mail,input_profession,input_phone;
    TextView input_MP,input_MLA,wall,textView2;
    Button button_signup;
    TextView already_account;
    Spinner s1,s2;
    EditText input_layout_name,input_layout_password,input_layout_mail,input_layout_MP,input_layout_MLA,input_layout_profession,input_layout_dob,input_layout_phone;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String dob;
    private String location;
    private String profession;
    private String post;
    private String lang;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    ArrayList<String> ar1 = new ArrayList<>(Arrays.asList("THIRUVALLUR","CHENNAI","KANCHEEPURAM",
            "VELLORE","KRISHNAGIRI","DHARMAPURI","TIRUVANNAMALAI","VILUPPURAM","SALEM",
            "NAMAKKAL","ERODE","THE NILGIRIS","COIMBATORE","DINDIGUL","KARUR",
                    "TIRUCHIRAPPALLI","PERAMBALUR","CUDDALORE","NAGAPATTINAM",
                    "THIRUVARUR","THIRUVARUR","THANJAVUR","PUDUKKOTTAI","SIVAGANGA",
                    "MADURAI","THENI","VIRUDHUNAGAR","RAMANATHAPURAM","THOOTHUKKUDI","TIRUNELVELI","TIRUNELVELI"));
    ArrayList<String> ar2 = new ArrayList<>(Arrays.asList("திருவள்ளூர்","சென்னை","காஞ்சிபுரம்",
            "வேலூர்","கிருஷ்ணகிரி","தர்மபுரி","திருவண்ணாமலை","விழுப்புரம்","சேலம்","நாமக்கல்",
            "ஈரோடு","நிழலிஸ்","கோவை","திண்டுக்கல்","கரூர்","திருச்சிராப்பள்ளி","பெரம்பலூர்","கடலூர்",
            "நாகப்பட்டினம்","திருவாரூர்","தஞ்சாவூர்","புதுக்கோட்டை","சிவகங்கை","மதுரை",
            "தேனி","விருதுநகர்","ராமநாதபுரம்","தூத்துக்குடி","திருநெல்வேலி","கன்னியாகுமரி"));
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseUser = firebaseAuth.getCurrentUser();
         sharedPreferences = getSharedPreferences("MyPref", 0); // 0 - for private mode
         editor = sharedPreferences.edit();
        lang = sharedPreferences.getString("lang","");


        calligrapher = new Calligrapher(this);
        calligrapher.setFont(Signup.this,"Ubuntu_R.ttf",true);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


        already_account = (TextView) findViewById(R.id.textView3);
        input_name = (EditText) findViewById(R.id.input_name);
        input_dob = (EditText) findViewById(R.id.input_dob);
        //input_MP = (TextView) findViewById(R.id.input_MP);
        input_password = (EditText) findViewById(R.id.input_password);
        input_mail = (EditText) findViewById(R.id.input_mail);
        //input_MLA = (TextView) findViewById(R.id.input_MLA);
        input_profession = (EditText) findViewById(R.id.input_profession);
        input_phone = (EditText) findViewById(R.id.input_phone);
        button_signup = (Button) findViewById(R.id.button_signup);

        input_layout_name = (EditText) findViewById(R.id.input_name);
        input_layout_mail = (EditText) findViewById(R.id.input_mail);
        //input_layout_password = (EditText) findViewById(R.id.input_password);
        input_layout_phone = (EditText) findViewById(R.id.input_phone);
       // input_layout_MP = (EditText) findViewById(R.id.input_MP);
        //input_layout_MLA = (EditText) findViewById(R.id.input_layout_MLA);
        input_layout_profession = (EditText) findViewById(R.id.input_layout_profession);
        input_layout_dob = (EditText) findViewById(R.id.input_dob);
        wall = (TextView) findViewById(R.id.wall);
        textView2 = (TextView)findViewById(R.id.textView2);
        s1 = (Spinner)findViewById(R.id.spinner1);
        s2 = (Spinner)findViewById(R.id.spinner2);
        s1.setOnItemSelectedListener(this);
        if(lang.equals("tamil"))
        {
            ArrayAdapter <String> ad1 = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,ar2);
            s1.setAdapter(ad1);
        }
        else
        {
            ArrayAdapter<String> ad1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ar1);
            s1.setAdapter(ad1);
        }
//        input_name.addTextChangedListener(new MyTextWatcher(input_name));
//        input_mail.addTextChangedListener(new MyTextWatcher(input_mail));
//        input_password.addTextChangedListener(new MyTextWatcher(input_password));
//        input_dob.addTextChangedListener(new MyTextWatcher(input_dob));
//        input_phone.addTextChangedListener(new MyTextWatcher(input_phone));
////        input_MP.addTextChangedListener(new MyTextWatcher(input_MP));
//        input_profession.addTextChangedListener(new MyTextWatcher(input_profession));
//        input_MLA.addTextChangedListener(new MyTextWatcher(input_MLA));
        if(lang.equals("tamil"))
        {
            input_layout_name.setHint(R.string.tamil_name);
            input_layout_mail.setHint(R.string.tamil_email);
            input_password.setHint(R.string.tamil_password);
            input_layout_phone.setHint(R.string.tamil_phone);
            input_layout_dob.setHint(R.string.tamil_dob);
            input_profession.setHint("தொழிலை");
            button_signup.setText(R.string.tamil_signup);
            already_account.setText(R.string.tamil_login);
            wall.setText("வரவேற்பு");
            textView2.setText("படிவத்தை நிரப்புக");
        }

        input_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickdob();
            }
        });
        already_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this,Login.class));
                finish();
            }
        });

        button_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateComplete();
            }
        });

    }

    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub
        if(lang.equals("tamil"))
        {
            String sp1= String.valueOf(s1.getSelectedItem());
            if(sp1.contentEquals("திருவள்ளூர்")) {
                List<String> list = new ArrayList<String>();
                list.add("கும்மிடிப்பூண்டி");
                list.add("பொன்னேரி");
                list.add("திருத்தணி");
                list.add("திருவள்ளூர்");
                list.add("பூந்தமல்லி");
                list.add("ஆவடி");
                list.add("மதுரவாயல்");
                list.add("அம்பத்தூர்");
                list.add("மாதவரம்");
                list.add("திருவொற்றியூர்");
                ArrayAdapter<String> dataAdapter1a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter1a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter1a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter1a);
            }
            if(sp1.contentEquals("சென்னை")) {
                List<String> list = new ArrayList<String>();
                list.add("ராதாகிருஷ்ணன்நகர்");
                list.add("பெரம்பூர்");
                list.add("வில்லிவாக்கம்");
                list.add("திருவிகநகர்");
                list.add("எழும்பூர்");
                list.add("இராயபுரம்");
                list.add("துறைமுகம்");
                list.add("சேப்பாக்கம்");
                list.add("ஆயிரம்விளக்கு");
                list.add("அண்ணாநகர்");
                list.add("விருகம்பாக்கம்");
                list.add("சைதாப்பேட்டை");
                list.add("சதியாகராயநகர்");
                list.add("மயிலாப்பூர்");
                list.add("வேளச்சேரி");
                ArrayAdapter<String> dataAdapter2a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter2a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter2a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter2a);
            }
            if(sp1.contentEquals("காஞ்சிபுரம்")) {
                List<String> list = new ArrayList<String>();
                list.add("சோழிங்கநல்லூர்");
                list.add("ஆலந்தூர்");
                list.add("திருப்பெரும்புதூர்");
                list.add("பல்லாவரம்");
                list.add("தாம்பரம்");
                list.add("செங்கல்பட்டு");
                list.add("திருப்போரூர்");
                list.add("செய்யூர்");
                list.add("மதுராந்தகம்");
                list.add("உத்திரமேரூர்");
                list.add("காஞ்சிபுரம்");
                ArrayAdapter<String> dataAdapter3a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter3a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter3a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter3a);
            }
            if(sp1.contentEquals("வேலூர்")) {
                List<String> list = new ArrayList<String>();
                list.add("அரக்கோணம்");
                list.add("சோளிங்கர்");
                list.add("காட்பாடி");
                list.add("ராணிப்பேட்டை");
                list.add("ஆற்காடு");
                list.add("வேலூர்");
                list.add("அணைக்கட்டு");
                list.add("கீழ்வைத்தியனான்குப்பம்");
                list.add("குடியாத்தம்");
                list.add("வாணியம்பாடி");
                list.add("ஆம்பூர்");
                list.add("ஜோலார்பேட்டை");
                list.add("திருப்பத்தூர்");
                ArrayAdapter<String> dataAdapter4a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter4a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter4a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter4a);
            }
            if(sp1.contentEquals("கிருஷ்ணகிரி"))
            {
                List<String> list = new ArrayList<String>();
                list.add("ஊத்தங்கரை");
                list.add("பர்கூர்");
                list.add("கிருஷ்ணகிரி");
                list.add("வேப்பனஹள்ளி");
                list.add("ஓசூர்");
                list.add("தளி");
                ArrayAdapter<String> dataAdapter5a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter5a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter5a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter5a);
            }
            if(sp1.contentEquals("தர்மபுரி")) {
                List<String> list = new ArrayList<String>();
                list.add("பாலக்கோடு");
                list.add("பென்னாகரம்");
                list.add("தர்மபுரி");
                list.add("பாப்பிரெட்டிப்பட்டி");
                list.add("அரூர்");
                ArrayAdapter<String> dataAdapter6a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter6a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter6a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter6a);
            }
            if(sp1.contentEquals("திருவண்ணாமலை")) {
                List<String> list = new ArrayList<String>();
                list.add("செங்கம்");
                list.add("திருவண்ணாமலை");
                list.add("கீழ்பெண்ணாத்தூர்");
                list.add("கலசப்பாக்கம்");
                list.add("போளூர்");
                list.add("ஆரணி");
                list.add("செய்யாறு");
                list.add("வந்தவாசி");
                ArrayAdapter<String> dataAdapter7a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter7a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter7a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter7a);
            }
            if(sp1.contentEquals("விழுப்புரம்")) {
                List<String> list = new ArrayList<String>();
                list.add("செஞ்சி");
                list.add("திண்டிவனம்");
                list.add("மயிலம்");
                list.add("வானூர்");
                list.add("விழுப்புரம்");
                list.add("விக்கிரவாண்டி");
                list.add("திருக்கோயிலூர்");
                list.add("உளுந்தூர்ப்பேட்டை");
                list.add("இரிஷிவந்தியம்");
                list.add("சங்கராபுரம்");
                list.add("கள்ளக்குறிச்சி");
                ArrayAdapter<String> dataAdapter8a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter8a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter8a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter8a);
            }
            if(sp1.contentEquals("சேலம்")) {
            List<String> list = new ArrayList<String>();
            list.add("கங்கவள்ளி");
            list.add("ஆத்தூர் - சேலம்");
            list.add("ஏற்காடு");
            list.add("ஓமலூர்");
            list.add("மேட்டூர்");
            list.add("விக்கிரவாண்டி");
            list.add("எடப்பாடி");
            list.add("சங்ககிரி");
            list.add("சேலம்-மேற்கு");
            list.add("சேலம்-வடக்கு");
            list.add("சேலம்-தெற்கு");
            list.add("வீரபாண்டி");
            ArrayAdapter<String> dataAdapter9a = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter9a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter9a.notifyDataSetChanged();
            s2.setAdapter(dataAdapter9a);
        }
            if(sp1.contentEquals("நாமக்கல்")) {
                List<String> list = new ArrayList<String>();
                list.add("இராசிபுரம்");
                list.add("சேந்தமங்கலம்");
                list.add("நாமக்கல்");
                list.add("பரமத்தி-வேலூர்");
                list.add("திருச்செங்கோடு");
                list.add("குமாரபாளையம்");
                ArrayAdapter<String> dataAdapter10a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter10a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter10a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter10a);
            }
            if(sp1.contentEquals("ஈரோடு")) {
                List<String> list = new ArrayList<String>();
                list.add("ஈரோடு (கிழக்கு)");
                list.add("Modakurichi");
                list.add("தாராபுரம்");
                list.add("கங்காமை");
                list.add("பெருந்துறை");
                list.add("பவானி");
                list.add("ஆந்தியூர்");
                list.add("கோபிசெட்டிபாளையம்");
                list.add("பவானிசாகர்");
                ArrayAdapter<String> dataAdapter11a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter11a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter11a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter11a);
            }
            if(sp1.contentEquals("நிழலிஸ்")) {
                List<String> list = new ArrayList<String>();
                list.add("உதகமண்டலம்");
                list.add("கூடலூர்");
                list.add("குன்னூர்");
                ArrayAdapter<String> dataAdapter12a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter12a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter12a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter12a);
            }
            if(sp1.contentEquals("கோவை")) {
            List<String> list = new ArrayList<String>();
            list.add("மேட்டுப்பாளையம்");
            list.add("சூலூர்");
            list.add("கவுண்டம்பாளையம்");
            list.add("கோயம்புத்தூர்வடக்கு");
            list.add("பதொண்டாமுத்தூர்");
            list.add("கோயம்புத்தூர்தெற்கு");
            list.add("சிங்காநல்லூர்");
            list.add("கோபிசெட்டிபாளையம்");
            list.add("கிணத்துக்கடவு");
            list.add("பொள்ளாச்சி");
            list.add("வால்பாறை");
            list.add("சிங்கநல்லூர்");
            list.add("கிணத்துக்கடவு");
            list.add("பொள்ளாச்சி");
            list.add("வால்பாறை");
            list.add("உடுமலைப்பேட்டை");
            list.add("மடத்துக்குளம்");
            ArrayAdapter<String> dataAdapter13a = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, list);
            dataAdapter13a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter13a.notifyDataSetChanged();
            s2.setAdapter(dataAdapter13a);
        }
            if(sp1.contentEquals("திண்டுக்கல்")) {
                List<String> list = new ArrayList<String>();
                list.add("பழநி");
                list.add("ஒட்டன்சத்திரம்");
                list.add("ஆத்தூர் - திண்டுக்கல்");
                list.add("நிலக்கோட்டை");
                list.add("நத்தம்");
                list.add("திண்டுக்கல்");
                list.add("வேடசந்தூர்");
                ArrayAdapter<String> dataAdapter14a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter14a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter14a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter14a);
            }
            if(sp1.contentEquals("கரூர்")) {
                List<String> list = new ArrayList<String>();
                list.add("அரவக்குறிச்சி");
                list.add("கரூர்");
                list.add("கிருஷ்ணராயபுரம்");
                list.add("குளித்தலை");
                ArrayAdapter<String> dataAdapter15a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter15a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter15a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter15a);
            }
            if(sp1.contentEquals("திருச்சிராப்பள்ளி")) {
                List<String> list = new ArrayList<String>();
                list.add("மணப்பாறைி");
                list.add("ஸ்ரீரங்கம்");
                list.add("திருச்சிராப்பள்ளிமேற்கு");
                list.add("திருச்சிராப்பள்ளிகிழக்கு");
                list.add("திருவெறும்பூர்");
                list.add("முசிறி");
                list.add("துறையூர்");

                ArrayAdapter<String> dataAdapter16a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter16a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter16a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter16a);
            }
            if(sp1.contentEquals("பெரம்பலூர்")) {
                List<String> list = new ArrayList<String>();
                list.add("பெரம்பலூர்");
                list.add("குன்னம்");
                list.add("அரியலூர்");
                list.add("ஜெயங்கொண்டம்");
                ArrayAdapter<String> dataAdapter17a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter17a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter17a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter17a);
            }
            if(sp1.contentEquals("கடலூர்")) {
                List<String> list = new ArrayList<String>();
                list.add("திட்டக்குடி");
                list.add("விருத்தாச்சலம்");
                list.add("நெய்வேலி");
                list.add("பண்ருட்டி");
                list.add("கடலூர்");
                list.add("குறிஞ்சிப்பாடி");
                list.add("புவனகிரி");
                list.add("சிதம்பரம்");
                list.add("காட்டுமன்னார்கோயில்");
                ArrayAdapter<String> dataAdapter18a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter18a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter18a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter18a);
            }
            if(sp1.contentEquals("நாகப்பட்டினம்")) {
                List<String> list = new ArrayList<String>();
                list.add("சீர்காழி");
                list.add("மயிலாடுதுறை");
                list.add("பூம்புகார்");
                list.add("நாகப்பட்டினம்");
                list.add("கீழ்வேளூர்");
                list.add("வேதாரண்யம்");
                ArrayAdapter<String> dataAdapter19a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter19a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter19a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter19a);
            }
            if(sp1.contentEquals("திருவாரூர்")) {
                List<String> list = new ArrayList<String>();
                list.add("திருத்துறைப்பூண்டி");
                list.add("மன்னார்குடி");
                list.add("திருவாரூர்");
                list.add("நன்னிலம்");
                ArrayAdapter<String> dataAdapter20a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter20a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter20a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter20a);
            }
            if(sp1.contentEquals("தஞ்சாவூர்")) {
                List<String> list = new ArrayList<String>();
                list.add("திருவிடைமருதூர்");
                list.add("கும்பகோணம்");
                list.add("பாபநாசம்");
                list.add("திருவையாறு");
                list.add("தஞ்சாவூர்");
                list.add("ஒரத்தநாடு");
                list.add("பட்டுக்கோட்டை");
                list.add("பேராவூரணி");
                ArrayAdapter<String> dataAdapter21a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter21a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter21a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter21a);
            }
            if(sp1.contentEquals("புதுக்கோட்டை")) {
                List<String> list = new ArrayList<String>();
                list.add("கந்தர்வக்கோட்டை");
                list.add("விராலிமலை");
                list.add("புதுக்கோட்டை");
                list.add("திருமயம்");
                list.add("ஆலங்குடி");
                list.add("அறந்தாங்கி");
                ArrayAdapter<String> dataAdapter22a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter22a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter22a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter22a);
            }
            if(sp1.contentEquals("சிவகங்கை")) {
                List<String> list = new ArrayList<String>();
                list.add("காரைக்குடி");
                list.add("திருப்பத்தூர்");
                list.add("சிவகங்கை");
                list.add("மானாமதுரை");
                ArrayAdapter<String> dataAdapter23a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter23a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter23a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter23a);
            }
            if(sp1.contentEquals("மதுரை")) {
                List<String> list = new ArrayList<String>();
                list.add("மேலூர்");
                list.add("மதுரைகிழக்கு");
                list.add("சோழவந்தான்");
                list.add("மதுரைவடக்கு");
                list.add("மதுரைதெற்கு");
                list.add("மதுரைமத்தி");
                list.add("மதுரைமேற்கு");
                list.add("உசிலம்பட்டி");
                list.add("திருப்பரங்குன்றம்");
                list.add("உசிலம்பட்டி");
                ArrayAdapter<String> dataAdapter24a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter24a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter24a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter24a);
            }
            if(sp1.contentEquals("தேனி")) {
                List<String> list = new ArrayList<String>();
                list.add("ஆண்டிப்பட்டி");
                list.add("பெரியகுளம்");
                list.add("போடிநாயக்கனூர்");
                list.add("கம்பம்");
                ArrayAdapter<String> dataAdapter25a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter25a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter25a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter25a);
            }
            if(sp1.contentEquals("விருதுநகர்")) {
                List<String> list = new ArrayList<String>();
                list.add("இராஜபாளையம்");
                list.add("திருவில்லிபுத்தூர்");
                list.add("சாத்தூர்");
                list.add("சிவகாசி");
                list.add("விருதுநகர்");
                list.add("திருச்சுழி");
                list.add("அருப்புக்கோட்டை");
                ArrayAdapter<String> dataAdapter26a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter26a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter26a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter26a);
            }
            if(sp1.contentEquals("ராமநாதபுரம்")) {
                List<String> list = new ArrayList<String>();
                list.add("திருவாடாணை");
                list.add("பரமக்குடி");
                list.add("இராமநாதபுரம்");
                list.add("முதுகுளத்தூர்");
                ArrayAdapter<String> dataAdapter27a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter27a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter27a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter27a);
            }
            if(sp1.contentEquals("தூத்துக்குடி")) {
                List<String> list = new ArrayList<String>();
                list.add("விளாத்திகுளம்");
                list.add("தூத்துக்குடி");
                list.add("திருச்செந்தூர்");
                list.add("ஸ்ரீவைகுண்டம்");
                list.add("விருதுநகர்");
                list.add("ஓட்டப்பிடாரம்");
                list.add("கோவில்பட்டி");
                ArrayAdapter<String> dataAdapter28a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter28a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter28a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter28a);
            }
            if(sp1.contentEquals("திருநெல்வேலி")) {
                List<String> list = new ArrayList<String>();
                list.add("சங்கரன்கோவில்");
                list.add("வாசுதேவநல்லூர்");
                list.add("கடையநல்லூர்");
                list.add("தென்காசி");
                list.add("ஆலங்குளம்");
                list.add("அம்பாசமுத்திரம்");
                list.add("திருநெல்வேலி");
                list.add("பாளையங்கோட்டை");
                list.add("நாங்குநேரி");
                list.add("ராதாபுரம்");
                ArrayAdapter<String> dataAdapter29a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter29a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter29a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter29a);
            }
            if(sp1.contentEquals("கன்னியாகுமரி")) {
                List<String> list = new ArrayList<String>();
                list.add("கன்னியாகுமரி");
                list.add("நாகர்கோவில்");
                list.add("குளச்சல்");
                list.add("பத்மனாபபுரம்");
                list.add("விளவங்கோடு");
                list.add("கிள்ளியூர்");
                ArrayAdapter<String> dataAdapter30a = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter30a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter30a.notifyDataSetChanged();
                s2.setAdapter(dataAdapter30a);
            }
        }
        else
        {
            String sp1 = String.valueOf(s1.getSelectedItem());
            if (sp1.contentEquals("THIRUVALLUR")) {
                List<String> list = new ArrayList<String>();
                list.add("Gummidipoondi");
                list.add("Ponneri");
                list.add("Tiruttani");
                list.add("Thiruvallur");
                list.add("Poonamallee");
                list.add("Avadi");
                list.add("Ambattur");
                list.add("Madavaram");
                list.add("Maduravoyal");
                list.add("Tiruvottiyur");
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter.notifyDataSetChanged();
                s2.setAdapter(dataAdapter);
            }
            if(sp1.contentEquals("CHENNAI")) {
                List<String> list = new ArrayList<String>();
                list.add("DrRadhakrishnan Nagar");
                list.add("Perambur");
                list.add("Kolathur");
                list.add("Thiru -Vi -Ka -Nagar");
                list.add("Egmore");
                list.add("Royapuram");
                list.add("Harbour");
                list.add("Chepauk-Thiruvallikeni");
                list.add("Thousand Lights");
                list.add("Anna Nagar");
                list.add("Virugampakkam");
                list.add("Saidapet");
                list.add("Thiyagarayanagar");
                list.add("Mylapore");
                list.add("Velachery");
                ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter2.notifyDataSetChanged();
                s2.setAdapter(dataAdapter2);
            }
            if(sp1.contentEquals("KANCHEEPURAM")) {
                List<String> list = new ArrayList<String>();
                list.add("Sholinganallur");
                list.add("Alandur");
                list.add("Sriperumbudur");
                list.add("Pallavaram");
                list.add("Tambaram");
                list.add("Chengalpattu");
                list.add("Thiruporur");
                list.add("Cheyyur");
                list.add("Madurantakam");
                list.add("Uthiramerur");
                list.add("Kancheepuram");
                ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter3.notifyDataSetChanged();
                s2.setAdapter(dataAdapter3);
            }
            if(sp1.contentEquals("VELLORE")) {
                List<String> list = new ArrayList<String>();
                list.add("Arakkonam");
                list.add("Sholingur");
                list.add("Katpadi");
                list.add("Ranipet");
                list.add("Arcot");
                list.add("Vellore");
                list.add("Anaikattu");
                list.add("Kilvaithinankuppam");
                list.add("Gudiyattam");
                list.add("Vaniyambadi");
                list.add("Ambur");
                list.add("Jolarpet");
                list.add("Tiruppattur");
                ArrayAdapter<String> dataAdapter4 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter4.notifyDataSetChanged();
                s2.setAdapter(dataAdapter4);
            }
            if(sp1.contentEquals("KRISHNAGIRI")) {
                List<String> list = new ArrayList<String>();
                list.add("Uthangarai");
                list.add("Bargur");
                list.add("Krishnagiri");
                list.add("Veppanahalli");
                list.add("Hosur");
                list.add("Thalli");
                ArrayAdapter<String> dataAdapter5 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter5.notifyDataSetChanged();
                s2.setAdapter(dataAdapter5);
            }
            if(sp1.contentEquals("DHARMAPURI")) {
                List<String> list = new ArrayList<String>();
                list.add("Palacodu");
                list.add("Pennagaram");
                list.add("Dharmapuri");
                list.add("Pappireddippatti");
                list.add("Harur");
                ArrayAdapter<String> dataAdapter6 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter6.notifyDataSetChanged();
                s2.setAdapter(dataAdapter6);
            }
            if(sp1.contentEquals("TIRUVANNAMALAI")) {
                List<String> list = new ArrayList<String>();
                list.add("Chengam");
                list.add("Tiruvannamalai");
                list.add("Kilpennathur");
                list.add("Kalasapakkam");
                list.add("Polur");
                list.add("Arani");
                list.add("Cheyyar");
                list.add("Vandavasi");
                ArrayAdapter<String> dataAdapter7 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter7.notifyDataSetChanged();
                s2.setAdapter(dataAdapter7);
            }
            if(sp1.contentEquals("VILLUPURAM")) {
                List<String> list = new ArrayList<String>();
                list.add("Gingee");
                list.add("Mailam");
                list.add("Tindivanam");
                list.add("Vanur");
                list.add("Viluppuram");
                list.add("Vikravandi");
                list.add("Thirukoilur");
                list.add("Ulundurpettai");
                list.add("Rishivandiyam");
                list.add("Sankarapuram");
                list.add("Kallakurichi");
                ArrayAdapter<String> dataAdapter8 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter8.notifyDataSetChanged();
                s2.setAdapter(dataAdapter8);
            }
            if(sp1.contentEquals("NAMAKKAL")) {
                List<String> list = new ArrayList<String>();
                list.add("Rasipuram");
                list.add("Senthamangalam");
                list.add(" Namakkal");
                list.add("Paramathi-Velur");
                list.add("Tiruchengodu");
                list.add("Kumarapalayam");
                ArrayAdapter<String> dataAdapter10 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter10.notifyDataSetChanged();
                s2.setAdapter(dataAdapter10);
            }
            if(sp1.contentEquals("SALEM")) {
                List<String> list = new ArrayList<String>();
                list.add("Gangavalli");
                list.add("Attur ");
                list.add("Yercaud");
                list.add("Omalur");
                list.add("Mettur");
                list.add("Edappadi");
                list.add("Sankari");
                list.add("Salem (West)");
                list.add("Salem (North)");
                list.add("Salem (South) ");
                ArrayAdapter<String> dataAdapter9 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter9.notifyDataSetChanged();
                s2.setAdapter(dataAdapter9);
            }
            if(sp1.contentEquals("ERODE")) {
                List<String> list = new ArrayList<String>();
                list.add("Erode (East)");
                list.add("Erode (West)");
                list.add("Modakurichi");
                list.add("Dharapuram");
                list.add("Kangayam");
                list.add("Perundurai");
                list.add("Bhavani");
                list.add("Anthiyur");
                list.add("Gobichettipalayam");
                list.add("Bhavanisagar");

                ArrayAdapter<String> dataAdapter11 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter11.notifyDataSetChanged();
                s2.setAdapter(dataAdapter11);
            }
            if(sp1.contentEquals("THE NILGIRIS")) {
                List<String> list = new ArrayList<String>();
                list.add("Udhagamandalam");
                list.add("Gudalur");
                list.add("Coonoor");
                ArrayAdapter<String> dataAdapter12 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter12.notifyDataSetChanged();
                s2.setAdapter(dataAdapter12);
            }
            if(sp1.contentEquals("COIMBATORE")) {
                List<String> list = new ArrayList<String>();
                list.add("Mettuppalayam");
                list.add("Avanashi");
                list.add("Tiruppur (North)");
                list.add("Tiruppur (South)");
                list.add("Palladam");
                list.add("Sulur");
                list.add("Kavundampalayam");
                list.add("Coimbatore (North)");
                list.add("Thondamuthur");
                list.add("Coimbatore (South)");
                list.add("Singanallur");
                list.add("Kinathukadavu");
                list.add("Pollachi");
                list.add("Valparai");
                list.add("Udumalaipettai");
                list.add("Madathukulam");
                ArrayAdapter<String> dataAdapter13 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter13.notifyDataSetChanged();
                s2.setAdapter(dataAdapter13);
            }
            if(sp1.contentEquals("DINDIGUL")) {
                List<String> list = new ArrayList<String>();
                list.add("Palani");
                list.add("Oddanchatram");
                list.add("Athoor");
                list.add("Nilakkottai");
                list.add("Natham");
                list.add("Dindigul");
                list.add("Vedasandur");
                ArrayAdapter<String> dataAdapter14 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter14.notifyDataSetChanged();
                s2.setAdapter(dataAdapter14);
            }
            if(sp1.contentEquals("KARUR")) {
                List<String> list = new ArrayList<String>();
                list.add("Aravakurichi");
                list.add("Karur");
                list.add("Krishnarayapuram");
                list.add("Kulithalai");
                ArrayAdapter<String> dataAdapter14 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter14.notifyDataSetChanged();
                s2.setAdapter(dataAdapter14);
            }
            if(sp1.contentEquals("TIRUCHIRAPPALLI")) {
                List<String> list = new ArrayList<String>();
                list.add("Manapparai");
                list.add("Srirangam");
                list.add("Tiruchirappalli (West)");
                list.add("Tiruchirappalli (East)");
                list.add("Thiruverumbur");
                list.add("Lalgudi");
                list.add("Manachanallur");
                list.add("Musiri");
                list.add("Thuraiyur");
                ArrayAdapter<String> dataAdapter15 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter15.notifyDataSetChanged();
                s2.setAdapter(dataAdapter15);
            }
            if(sp1.contentEquals("PERAMBALUR")) {
                List<String> list = new ArrayList<String>();
                list.add("Perambalur ");
                list.add("Kunnam");
                list.add("Ariyalur");
                list.add("Jayankondam");
                ArrayAdapter<String> dataAdapter16 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter16.notifyDataSetChanged();
                s2.setAdapter(dataAdapter16);
            }
            if(sp1.contentEquals("CUDDALORE")) {
                List<String> list = new ArrayList<String>();
                list.add("Tittakudi");
                list.add("Vriddhachalam");
                list.add("Neyveli");
                list.add("Panruti");
                list.add("Cuddalore");
                list.add("Kurinjipadi");
                list.add("Bhuvanagiri");
                list.add("Chidambaram");
                list.add("Kattumannarkoil ");
                ArrayAdapter<String> dataAdapter17 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter17.notifyDataSetChanged();
                s2.setAdapter(dataAdapter17);
            }
            if(sp1.contentEquals("NAGAPATTINAM")) {
                List<String> list = new ArrayList<String>();
                list.add("Sirkazhi ");
                list.add("Mayiladuthurai");
                list.add("Poompuhar");
                list.add("Nagapattinam");
                list.add("Kilvelur ");
                list.add("Vedaranyam");
                ArrayAdapter<String> dataAdapter18 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter18.notifyDataSetChanged();
                s2.setAdapter(dataAdapter18);
            }
            if(sp1.contentEquals("THIRUVARUR")) {
                List<String> list = new ArrayList<String>();
                list.add("Thiruthuraipoondi");
                list.add("Mannargudi");
                list.add("Thiruvarur");
                list.add("Nannilam");
                ArrayAdapter<String> dataAdapter19 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter19.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter19.notifyDataSetChanged();
                s2.setAdapter(dataAdapter19);
            }
            if(sp1.contentEquals("THANJAVUR")) {
                List<String> list = new ArrayList<String>();
                list.add("Thiruvidaimarudur");
                list.add("Kumbakonam");
                list.add("Papanasam");
                list.add("Thiruvaiyaru");
                list.add("Thanjavur");
                list.add("Orattanadu");
                list.add("Pattukkottai");
                list.add("Peravurani");
                ArrayAdapter<String> dataAdapter20 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter20.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter20.notifyDataSetChanged();
                s2.setAdapter(dataAdapter20);
            }
            if(sp1.contentEquals("PUDUKKOTTAI")) {
                List<String> list = new ArrayList<String>();
                list.add("Gandarvakottai ");
                list.add("Viralimalai");
                list.add("Pudukkottai");
                list.add("Thirumayam");
                list.add("Aranthangi");
                list.add("Alangudi");
                ArrayAdapter<String> dataAdapter21 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter21.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter21.notifyDataSetChanged();
                s2.setAdapter(dataAdapter21);
            }
            if(sp1.contentEquals("SIVAGANGA")) {
                List<String> list = new ArrayList<String>();
                list.add("Karaikudi");
                list.add("Tiruppattur");
                list.add("Sivaganga");
                list.add("Manamadurai ");
                ArrayAdapter<String> dataAdapter22 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter22.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter22.notifyDataSetChanged();
                s2.setAdapter(dataAdapter22);
            }
            if(sp1.contentEquals("MADURAI")) {
                List<String> list = new ArrayList<String>();
                list.add("Melur");
                list.add("Madurai East");
                list.add("Sholavandan");
                list.add("Madurai North");
                list.add("Madurai South");
                list.add("Madurai Wast");
                list.add("Madurai Central");
                list.add("Thiruparankundram");
                list.add("Thirumangalam");
                list.add("Usilampatti");
                ArrayAdapter<String> dataAdapter23 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter23.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter23.notifyDataSetChanged();
                s2.setAdapter(dataAdapter23);
            }
            if(sp1.contentEquals("THENI")) {
                List<String> list = new ArrayList<String>();
                list.add("Andipatti");
                list.add("Periyakulam");
                list.add("Bodinayackanur");
                list.add("Cumbum");
                ArrayAdapter<String> dataAdapter24 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter24.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter24.notifyDataSetChanged();
                s2.setAdapter(dataAdapter24);
            }
            if(sp1.contentEquals("VIRUDHUNAGAR")) {
                List<String> list = new ArrayList<String>();
                list.add("Rajapalayam");
                list.add("Srivilliputhur");
                list.add("Sattur");
                list.add("Sivakasi");
                list.add("Virudhunagar");
                list.add("Aruppukkottai");
                list.add("Tiruchuli");
                ArrayAdapter<String> dataAdapter25 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter25.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter25.notifyDataSetChanged();
                s2.setAdapter(dataAdapter25);
            }
            if(sp1.contentEquals("RAMANATHAPURAM")) {
                List<String> list = new ArrayList<String>();
                list.add("Paramakudi");
                list.add("Tiruvadanai");
                list.add("Ramanathapuram");
                list.add("Mudhukulathur");
                ArrayAdapter<String> dataAdapter26 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter26.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter26.notifyDataSetChanged();
                s2.setAdapter(dataAdapter26);
            }
            if(sp1.contentEquals("THOOTHUKKUDI")) {
                List<String> list = new ArrayList<String>();
                list.add("Vilathikulam");
                list.add("Thoothukkudi");
                list.add("Tiruchendur");
                list.add("Srivaikuntam");
                list.add("Ottapidaram ");
                list.add("Kovilpatti");
                ArrayAdapter<String> dataAdapter27 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter27.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter27.notifyDataSetChanged();
                s2.setAdapter(dataAdapter27);
            }
            if(sp1.contentEquals("TIRUNELVELI")) {
                List<String> list = new ArrayList<String>();
                list.add("Sankarankovil");
                list.add("Vasudevanallur");
                list.add("Kadayanallur");
                list.add("Tenkasi");
                list.add("Alangulam");
                list.add("Tirunelveli");
                list.add("Ambasamudram");
                list.add("Palayamkottai");
                list.add("Nanguneri");
                list.add("Radhapuram");
                ArrayAdapter<String> dataAdapter28 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter28.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter28.notifyDataSetChanged();
                s2.setAdapter(dataAdapter28);
            }
            if(sp1.contentEquals("KANNIYAKUMARI")) {
                List<String> list = new ArrayList<String>();
                list.add("Kanniyakumari");
                list.add("Nagercoil");
                list.add("Colachel");
                list.add("Padmanabhapuram");
                list.add("Vilavancode");
                list.add("Killiyoor");
                ArrayAdapter<String> dataAdapter29 = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, list);
                dataAdapter29.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dataAdapter29.notifyDataSetChanged();
                s2.setAdapter(dataAdapter29);
            }
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    private void pickdob() {
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                input_dob.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    private void validateComplete() {
         if(input_mail.getText().toString().isEmpty()&&input_password.getText().toString().isEmpty()&&input_dob.getText().toString().isEmpty()&&input_phone.getText().toString().isEmpty()&&input_profession.getText().toString().isEmpty()){
             Toast.makeText(this, "Enter all the credentials....", Toast.LENGTH_SHORT).show();
         }
        else{
             email = input_mail.getText().toString().trim();
             password = input_password.getText().toString().trim();
             System.out.println("test ---> Entering validate");
             firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful())
                     {
                         System.out.println("test ---> Calling upload");

                         uploadFirebaseData();
                     }
                     else
                     {
                         Toast.makeText(Signup.this,"Registration Failed"+"\n"+"Please enter the password above 8 characters and check other credentials",Toast.LENGTH_SHORT).show();
                     }
                 }
             });
         }
    }

    private void uploadFirebaseData() {
        name = input_name.getText().toString().trim();
        phone = input_phone.getText().toString().trim();
        dob = input_dob.getText().toString().trim();
        location = s1.getSelectedItem().toString();
        profession = input_profession.getText().toString().trim();
        post = s2.getSelectedItem().toString();

       // System.out.println("test ---> "+name+phone+dob+location+profession+post);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        SignUpAdapter signUpAdapter = new SignUpAdapter();
        signUpAdapter.setName(name);
        signUpAdapter.setMail(email);
        signUpAdapter.setPassword(password);
        signUpAdapter.setPhone(phone);
        signUpAdapter.setLocation(location);
        signUpAdapter.setDob(dob);
        signUpAdapter.setProfession(profession);
        signUpAdapter.setPost(post);

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(name).build();

        user.updateProfile(profileUpdates);

        databaseReference.child("Users").child(location).child(post).child(user.getUid()).setValue(signUpAdapter);

        this.finish();
        startActivity(new Intent(this,Login.class));
    }



}
