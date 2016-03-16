package com.android.supafit.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.supafit.fragment.SleepFragment;
import com.android.supafit.fragment.AnalyticsTaskFragment;
import com.android.supafit.fragment.WaterConsumptionFragment;

/**
 * Created by Aky on 3/16/2016.
 */
public class ViewPagerAnalyticsAdapter extends FragmentPagerAdapter {

    public int num_fragments = 3;
    private Context mContext;
    public ViewPagerAnalyticsAdapter(FragmentManager fm, Context c) {
        super(fm);
        mContext = c;
    }

    Fragment mSleepFragment, mWaterFragment, mTaskFragment;

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                if(mSleepFragment == null)
                    mSleepFragment = new SleepFragment();
                return mSleepFragment;
            // break;
            case 1:
                if(mWaterFragment == null)
                    mWaterFragment = new WaterConsumptionFragment();
                return  mWaterFragment;
            //break;
            case 2:
                if(mTaskFragment == null)
                    mTaskFragment = new AnalyticsTaskFragment();
                return  mTaskFragment;

        }
        return null;
    }

    @Override
    public int getCount() {
        return num_fragments;
    }
}
