package com.android.supafit.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.supafit.R;

public class InviteActivity extends AppCompatActivity {

    private TextView referaal_code_value;
    private AppCompatButton share_button;
    private Toolbar mToolbar;
    private AppCompatImageView back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);

        setUpToolbar();
        initViews();

    }

    private void initViews() {
        referaal_code_value = (TextView) findViewById(R.id.referaal_code_value);
        referaal_code_value.setText("SUPAFIT1");

        share_button = (AppCompatButton) findViewById(R.id.share_button);
        share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Its Supafit Baby! \n" +
                        " https://play.google.com/store/apps/details?id=com.project.chefensaapp");
                shareIntent.setType("text/plain");
                startActivity(shareIntent);

            }
        });
    }

    private void setUpToolbar() {
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
}