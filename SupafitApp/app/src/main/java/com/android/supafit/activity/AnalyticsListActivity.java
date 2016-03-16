package com.android.supafit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.view.View;

import com.android.supafit.R;

public class AnalyticsListActivity extends AppCompatActivity implements View.OnClickListener{

    private AppCompatTextView water_consumption_text;
    private AppCompatTextView sleep_intake_text;
    private AppCompatTextView tasks_done_text;
    private CardView water_card;
    private CardView sleep_card;
    private CardView task_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics_list);

        initViews();
    }
    private void initViews() {
        water_card = (CardView) findViewById(R.id.water_card);
        sleep_card = (CardView) findViewById(R.id.sleep_card);
        task_card = (CardView) findViewById(R.id.task_card);
        water_consumption_text = (AppCompatTextView) findViewById(R.id.water_consumption_text);
        sleep_intake_text = (AppCompatTextView) findViewById(R.id.sleep_intake_text);
        tasks_done_text = (AppCompatTextView) findViewById(R.id.tasks_done_text);

        water_consumption_text.setText("23 Litres");
        sleep_intake_text.setText("67 Hours");
        tasks_done_text.setText("51 Tasks");

        water_card.setOnClickListener(this);
        sleep_card.setOnClickListener(this);
        task_card.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.water_card:

                movetoAnalyticsActivity();
                break;
            case R.id.sleep_card:
                movetoAnalyticsActivity();

                break;
            case R.id.task_card:
                movetoAnalyticsActivity();
                break;
        }
    }


    private void movetoAnalyticsActivity() {
        Intent i = new Intent(AnalyticsListActivity.this,AnalyticsActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
