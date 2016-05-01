package com.android.supafit.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
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
import com.android.supafit.netoperations.networkmodel.user.User;
import com.android.supafit.ui.planslist.PlanListActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private ImageView drawer_button;
    private TextView title_text, clickable_text;
    private boolean isDrawerOpened;
    private boolean isCalendarDown;
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
    private MaterialCalendarView calendarView;
    private LinearLayout not_assigned_layout;

    private User user;

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

        user = (User)getIntent().getSerializableExtra("user_data");

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
        calendar_layout = (LinearLayout)findViewById(R.id.calendar_layout);
        settings_linear_layout.setOnClickListener(this);
        invite_linear_layout.setOnClickListener(this);
        analytics_linear_layout.setOnClickListener(this);
        plans_and_pricing_layout.setOnClickListener(this);
        profile_linear_layout.setOnClickListener(this);
        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        clickable_text = (TextView)findViewById(R.id.clickable_text);
        not_assigned_layout = (LinearLayout)findViewById(R.id.not_assigned_layout);
        conditionForNonAssignment();
        setClickableSpan();
    }

    private void conditionForNonAssignment(){
        //if(user.getTrainer() != null && user.getDietitan() != null){
            not_assigned_layout.setVisibility(View.GONE);
        /*} else {
            not_assigned_layout.setVisibility(View.VISIBLE);
        }*/
    }

    private void setClickableSpan(){
        SpannableString ss
                = new SpannableString("Meanwhile you can help us accelerate the process by telling us more about yourself and completing your Profile");

        ClickableSpan span1 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent intent = new Intent(MainActivity.this, UserInformationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
            }
        };
        ss.setSpan(span1, 103, 110, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.text_colour_black)), 103, 110, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),103,110,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        clickable_text.setText(ss);
        clickable_text.setMovementMethod(LinkMovementMethod.getInstance());
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
        viewPager.bringToFront();
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
        //Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show();
        showCalendarView();

    }

    private void showCalendarView() {
        if(isCalendarDown){
            animateCalendarLayoutBottomToTop();
            isCalendarDown = false;
        } else {
            animateCalendarLayoutTopToBottom();
            isCalendarDown = true;
        }

    }

    private void animateCalendarLayoutTopToBottom() {
        Animation animation = new TranslateAnimation(0, 0, 0, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 250,
                getResources().getDisplayMetrics()));
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 110,
                        getResources().getDisplayMetrics()),0,0);
                calendar_layout.setLayoutParams(params);
                calendar_layout.getLayoutParams().height = 950;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        calendar_layout.setAnimation(animation);
    }

    private void animateCalendarLayoutBottomToTop() {
        Animation animation = new TranslateAnimation(0, 0, 0, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -250,
                getResources().getDisplayMetrics()));
        animation.setDuration(500);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(0, (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, -240, getResources().getDisplayMetrics()),0,0);
                calendar_layout.setLayoutParams(params);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        calendar_layout.setAnimation(animation);
    }


    public void onChatButtonClicked(View view){
        Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show();
    }

    public void onNotificationButtonClicked(View view){
        Toast.makeText(this, "clicked", Toast.LENGTH_LONG).show();
    }

    private void animateSettingsLayoutTopToBottom(){
        Animation animation = new TranslateAnimation(0, 0, 0, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 290,
                getResources().getDisplayMetrics()));
        animation.setDuration(500);
        settings_layout.setAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                settings_layout.clearAnimation();
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
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
                animateSettingsLayoutBottomToTop();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_half_left_to_right, R.anim.slide_left_to_right);
    }
}
