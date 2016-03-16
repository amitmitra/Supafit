package com.android.supafit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.supafit.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private ImageView logout_button;
    private TextView contact_us;
    private TextView terms_conditions;
    private AppCompatCheckBox mobile_notifications_radio;
    private AppCompatCheckBox email_notification_radio;
    private Toolbar mToolbar;
    private AppCompatImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
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
                Toast.makeText(this,"Logout",Toast.LENGTH_SHORT).show();
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
