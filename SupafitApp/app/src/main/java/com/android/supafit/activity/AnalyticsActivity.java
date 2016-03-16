package com.android.supafit.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.MenuItem;
import android.view.View;

import com.android.supafit.R;
import com.android.supafit.adapter.ViewPagerAnalyticsAdapter;

public class AnalyticsActivity extends AppCompatActivity {

    ViewPager viewPager;
    private AppCompatTextView property_text;
    private AppCompatImageView left_arrow, right_arrow, property_image;
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        initializeViews();
    }

    public void initializeViews() {

        property_text = (AppCompatTextView) findViewById(R.id.property_text);
        left_arrow = (AppCompatImageView) findViewById(R.id.left_arrow);
        right_arrow = (AppCompatImageView) findViewById(R.id.right_arrow);
        property_image = (AppCompatImageView) findViewById(R.id.property_image);


        viewPager = (ViewPager) findViewById(R.id.Pager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        currentPosition = 0;
                        setPropertyText("Sleep");
                        property_text.setTextColor(getResources().getColor(R.color.sleepingYellow));
                        setPropertyImage(R.drawable.ic_sleep);
                        break;
                    case 1:
                        currentPosition = 1;
                        setPropertyText("Water");
                        property_text.setTextColor(getResources().getColor(R.color.waterBlue));
                        setPropertyImage(R.drawable.ic_water);

                        break;
                    case 2:
                        currentPosition = 2;
                        setPropertyText("Task");
                        property_text.setTextColor(getResources().getColor(R.color.colorPrimary));
                        setPropertyImage(R.drawable.ic_sleep);
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        final ViewPagerAnalyticsAdapter adapter = new ViewPagerAnalyticsAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);


        left_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {

                if(currentPosition > 0){
                    currentPosition--;
                    //adapter.getItem(currentPosition);
                    viewPager.setCurrentItem(currentPosition);
                }
            }
        });

        right_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPosition < adapter.num_fragments){
                    currentPosition++;
                    //adapter.getItem(currentPosition);
                    viewPager.setCurrentItem(currentPosition);
                }
            }
        });
    }

    public void setPropertyText(String text){
        property_text.setText(text);
        //  property_text.setTextColor(color);
    }

    public void setPropertyImage(int image){
        property_image.setImageResource(image);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);

    }
}
