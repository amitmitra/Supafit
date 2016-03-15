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
import android.widget.TextView;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.adapter.ViewPagerAdapter;
import com.android.supafit.fragment.SummaryFragment;
import com.android.supafit.fragment.TasksFragment;
import com.android.supafit.gcm.GcmRegistrationIntentService;
import com.android.supafit.gcm.QuickstartPreferences;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;


public class MainActivity extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private ImageView drawer_button;
    private TextView title_text;
    private boolean isDrawerOpened;
    private Toolbar mToolbar;
    private LinearLayout settings_layout;

    private SummaryFragment mSummaryFragment;
    private TasksFragment mTaskFragment;

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

    }

    private void setUpToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
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
            settings_layout.bringToFront();
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
        settings_layout.bringToFront();
        Animation animation = new TranslateAnimation(0, 0, 0, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 290, getResources().getDisplayMetrics()));
        animation.setDuration(500);
        animation.setFillAfter(true);
        settings_layout.setAnimation(animation);
    }

    private void animateSettingsLayoutBottomToTop(){
        Animation animation = new TranslateAnimation(0, 0, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 290, getResources().getDisplayMetrics()), 0);
        animation.setDuration(500);
        animation.setFillAfter(true);
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

}
