package com.android.supafit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.ui.login.LoginActivity;
import com.android.supafit.utils.AppConstants;
import com.android.supafit.utils.AppPreferences;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private final String TAG = SettingsActivity.class.getSimpleName();

    private ImageView logout_button;
    private TextView contact_us;
    private TextView terms_conditions;
    private AppCompatCheckBox mobile_notifications_radio;
    private AppCompatCheckBox email_notification_radio;
    private Toolbar mToolbar;
    private AppCompatImageView back_button;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                        Log.i(TAG, "google api error");
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        initViews();
        setUpSettingsToolbar();
    }

    private void setUpSettingsToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        back_button = (AppCompatImageView) findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void initViews() {
        contact_us = (TextView) findViewById(R.id.contact_us);
        contact_us.setOnClickListener(this);

        logout_button = (ImageView) findViewById(R.id.logout_button);
        logout_button.setOnClickListener(this);

        terms_conditions = (TextView) findViewById(R.id.terms_conditions);
        terms_conditions.setOnClickListener(this);
        mobile_notifications_radio = (AppCompatCheckBox) findViewById(R.id.mobile_notifications_radio);
        email_notification_radio = (AppCompatCheckBox) findViewById(R.id.email_notification_radio);
        mobile_notifications_radio.setOnCheckedChangeListener(this );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logout_button:
                String loginType = new AppPreferences(this).getLoginType();
                if(AppConstants.LOGIN_TYPE_FACEBOOK.equals(loginType)){
                    LoginManager.getInstance().logOut();
                } else if(AppConstants.LOGIN_TYPE_GOOGLE.equals(loginType)){
                    if(mGoogleApiClient.isConnected()){
                        mGoogleApiClient.clearDefaultAccountAndReconnect();
                        mGoogleApiClient.disconnect();
                    }
                }
                new AppPreferences(this).setUserStatus(AppConstants.USER_SIGNED_OUT);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_half_left_to_right, R.anim.slide_left_to_right);
                finishAffinity();
                break;

            case R.id.contact_us:
                break;

            case R.id.rate_us:
                break;

            case R.id.terms_conditions:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()){
            case R.id.mobile_notifications_radio:
                if (isChecked) {
                    showTextNotification("checked success");
                } else {
                    showTextNotification("checked failure");
                }
                break;
            case R.id.email_notification_radio:
                if (isChecked) {
                    showTextNotification("email checked success");
                } else {
                    showTextNotification("email checked failure");
                }
                break;
        }
    }
    public void showTextNotification(String msgToDisplay) {
        Toast.makeText(this, msgToDisplay, Toast.LENGTH_SHORT).show();
    }
}
