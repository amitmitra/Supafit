package com.android.supafit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.android.supafit.R;
import com.android.supafit.ui.login.LoginActivity;
import com.android.supafit.utils.AppConstants;
import com.android.supafit.utils.AppPreferences;


public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i;
                int status = new AppPreferences(SplashScreenActivity.this).getUserStatus();
                if(status == AppConstants.USER_STATUS_NEW || status == AppConstants.USER_SIGNED_UP_AND_NOT_SIGNED_IN){
                    i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                } else {
                    i = new Intent(SplashScreenActivity.this, MainActivity.class);
                }
                startActivity(i);
                overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
