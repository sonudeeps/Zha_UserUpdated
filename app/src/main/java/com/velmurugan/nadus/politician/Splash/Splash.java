package com.velmurugan.nadus.politician.Splash;

/**
 * Created by nadus on 12-11-2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.velmurugan.nadus.politician.LoginSignup.Login;
import com.google.firebase.auth.FirebaseAuth;

public class Splash extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Start home activity
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Actions to do after 3 seconds
                startActivity(new Intent(Splash.this, Login.class));
                finish();
            }
        }, 1500);
        // close splash activity
    }
}