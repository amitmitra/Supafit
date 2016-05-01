package com.android.supafit.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.android.supafit.R;
import com.android.supafit.netoperations.VolleyRequest;
import com.android.supafit.netoperations.handler.NetworkHandler;
import com.android.supafit.netoperations.networkmodel.user.User;
import com.android.supafit.ui.login.LoginActivity;
import com.android.supafit.utils.AppConstants;
import com.android.supafit.utils.AppPreferences;

import org.json.JSONException;


public class SplashScreenActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int status = new AppPreferences(SplashScreenActivity.this).getUserStatus();
                if(status == AppConstants.USER_STATUS_NEW || status == AppConstants.USER_SIGNED_UP_AND_NOT_SIGNED_IN
                        || status == AppConstants.USER_SIGNED_OUT){
                    Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
                    finish();
                } else {
                    NetworkHandler handler = new NetworkHandler() {
                        @Override
                        public void success(Object response) {
                            if(response != null) {
                                User user = (User)response;
                                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                                i.putExtra("user_data", user);
                                startActivity(i);
                                overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
                                finish();
                            } else {
                                AlertDialog.Builder buildr = new AlertDialog.Builder(SplashScreenActivity.this);
                                buildr.setTitle("Network Problem");
                                buildr.setMessage("There is some problem in your network! Check and open app again!");
                                buildr.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        SplashScreenActivity.this.finishAffinity();
                                    }
                                });
                                buildr.show();
                            }
                        }

                        @Override
                        public void failure(Exception e) {
                            AlertDialog.Builder buildr = new AlertDialog.Builder(SplashScreenActivity.this);
                            buildr.setTitle("Network Problem");
                            buildr.setMessage("There is some problem in your network! Check and open app again!");
                            buildr.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SplashScreenActivity.this.finishAffinity();
                                }
                            });
                        }
                    };

                    try {
                        VolleyRequest.signinUser(new AppPreferences(SplashScreenActivity.this).getUserEmail(),
                                new AppPreferences(SplashScreenActivity.this).getUserPassword(), handler);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        AlertDialog.Builder buildr = new AlertDialog.Builder(SplashScreenActivity.this);
                        buildr.setTitle("Network Problem");
                        buildr.setMessage("There is some problem in your network! Check and open app again!");
                        buildr.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SplashScreenActivity.this.finishAffinity();
                            }
                        });
                        buildr.show();
                    }
                }
            }
        }, SPLASH_TIME_OUT);

    }
}
