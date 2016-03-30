package com.android.supafit.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.adapter.ViewPagerAdapter;
import com.android.supafit.fragment.SummaryFragment;
import com.android.supafit.fragment.TasksFragment;
import com.android.supafit.gcm.GcmRegistrationIntentService;
import com.android.supafit.gcm.QuickstartPreferences;
import com.android.supafit.ui.planslist.PlanListActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private ImageView drawer_button;
    private TextView title_text;
    private boolean isDrawerOpened;
    private Toolbar mToolbar, mToolbar2;
    private LinearLayout settings_layout;

    private SummaryFragment mSummaryFragment;
    private TasksFragment mTaskFragment;


    private  LinearLayout settings_linear_layout;
    private LinearLayout invite_linear_layout;
    private LinearLayout analytics_linear_layout;
    private LinearLayout calendar_layout;
    private LinearLayout plans_and_pricing_layout;
    private LinearLayout profile_linear_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (checkPlayServices()) {
            Intent intent = new Intent(this, GcmRegistrationIntentService.class);
            startService(intent);
        }

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                boolean sentToken = sharedPreferences.getBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false);
                if (sentToken) {
                    //TODO : GCM Registration SUCCESS
                    //mInformationTextView.setText(getString(R.string.gcm_send_message));
                } else {
                    //TODO : GCM Registration FAILIURE
                    //mInformationTextView.setText(getString(R.string.token_error_message));
                }
            }
        };

        setUpToolbar();
        setUpViewPager();
        initializeViews();
    }

    private void initializeViews() {
        settings_linear_layout = (LinearLayout) findViewById(R.id.settings_linear_layout);
        invite_linear_layout = (LinearLayout) findViewById(R.id.invite_linear_layout);
        analytics_linear_layout = (LinearLayout) findViewById(R.id.analytics_linear_layout);
        plans_and_pricing_layout = (LinearLayout)findViewById(R.id.plans_and_pricing_layout);
        profile_linear_layout = (LinearLayout)findViewById(R.id.profile_linear_layout);
        settings_linear_layout.setOnClickListener(this);
        invite_linear_layout.setOnClickListener(this);
        analytics_linear_layout.setOnClickListener(this);
        plans_and_pricing_layout.setOnClickListener(this);
        profile_linear_layout.setOnClickListener(this);

        /*calendar_layout = (LinearLayout) findViewById(R.id.calendar_layout);
        calendarView = (CalendarView) findViewById(R.id.calendar_view);
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);
        calendarView.setIsOverflowDateVisible(true);
        calendarView.setCurrentDay(new Date(System.currentTimeMillis()));
        calendarView.refreshCalendar(Calendar.getInstance(Locale.getDefault()));
        calendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull Date date) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                Toast.makeText(MainActivity.this,""+df,Toast.LENGTH_SHORT).show();
            }
        });
        calendar_layout.bringToFront();*/
    }

    private void setUpToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar1);
        mToolbar2 = (Toolbar) findViewById(R.id.toolbar2);
        mToolbar.bringToFront();
        setSupportActionBar(mToolbar);
        setSupportActionBar(mToolbar2);
        drawer_button = (ImageView)findViewById(R.id.drawer_button) ;
        title_text = (TextView)findViewById(R.id.title_text);
        settings_layout = (LinearLayout)findViewById(R.id.settings_layout);
    }

    private void setUpViewPager(){
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        if(mSummaryFragment == null){
            mSummaryFragment = new SummaryFragment();
        }
        if(mTaskFragment == null){
            mTaskFragment = new TasksFragment();
        }
        adapter.addFragment(mSummaryFragment, getResources().getString(R.string.summary_string));
        adapter.addFragment(mTaskFragment, getResources().getString(R.string.tasks_string));
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    public void onDrawerButtonClicked(View view){
        if(isDrawerOpened){
            isDrawerOpened = false;
            title_text.setText(getResources().getString(R.string.dashboard_string));
            drawer_button.setImageResource(R.drawable.ic_drawer);
            animateSettingsLayoutBottomToTop();
        } else {
            isDrawerOpened = true;
            title_text.setText(getResources().getString(R.string.menu_string));
            drawer_button.setImageResource(R.drawable.cross_icon);
            animateSettingsLayoutTopToBottom();
        }
    }

    public void onCalendarButtonClicked(View view){
        Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show();
    }

    public void onChatButtonClicked(View view){
        Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show();
    }

    public void onNotificationButtonClicked(View view){
        Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show();
    }

    private void animateSettingsLayoutTopToBottom(){
        Animation animation = new TranslateAnimation(0, 0, 0, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 290, getResources().getDisplayMetrics()));
        animation.setDuration(500);
        settings_layout.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                settings_layout.clearAnimation();
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics()),0,0);
                settings_layout.setLayoutParams(params);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void animateSettingsLayoutBottomToTop(){
        Animation animation = new TranslateAnimation(0, 0, 0, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -250, getResources().getDisplayMetrics()));
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                settings_layout.clearAnimation();
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -240, getResources().getDisplayMetrics()),0,0);
                settings_layout.setLayoutParams(params);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        settings_layout.setAnimation(animation);
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.plans_and_pricing_layout:
                Intent planPriceIntent = new Intent(MainActivity.this,PlanListActivity.class);
                startActivity(planPriceIntent);
                overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
                animateSettingsLayoutBottomToTop();
                break;
            case R.id.settings_linear_layout:
                Intent i = new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
                animateSettingsLayoutBottomToTop();
                break;

            case R.id.invite_linear_layout:
                Intent intent = new Intent(MainActivity.this,InviteActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
                animateSettingsLayoutBottomToTop();
                break;

            case R.id.analytics_linear_layout:
                Intent analyticsIntent = new Intent(MainActivity.this,AnalyticsListActivity.class);
                startActivity(analyticsIntent);
                overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
                animateSettingsLayoutBottomToTop();
                break;

            case R.id.profile_linear_layout:
                Intent profileIntent = new Intent(MainActivity.this,UserInformationActivity.class);
                startActivity(profileIntent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_half_left_to_right, R.anim.slide_left_to_right);
    }
}
