package com.android.supafit.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.netoperations.VolleyRequest;
import com.android.supafit.netoperations.handler.NetworkHandler;
import com.android.supafit.netoperations.networkmodel.summary.ActivitySummary;
import com.android.supafit.ui.RegularFontEditText;
import com.android.supafit.ui.RegularFontTextView;

import java.util.Calendar;


/**
 * Created by harsh on 2/23/16.
 */
public class SummaryFragment extends Fragment implements View.OnClickListener {
    private ActivitySummary mActivitySummary;
    private RegularFontTextView actual_task, actual_steps, actual_water, actual_sleep, actual_cal_gained, actual_cal_burnt;
    private RegularFontTextView total_task, total_steps, total_water, total_sleep, total_cal_gained, total_cal_burnt;
    private ProgressBar steps_walked_progress_bar, water_consumed_progress_bar, sleep_consumed_progress_bar;
    private NumberPicker number_hrs, number_mins;
    private RegularFontTextView done_steps_button;
    private RegularFontTextView done_sleep_button;
    private AppCompatSpinner water_spinner;
    private String waterunit;
    private EditText water_edittext, sleep_hrs, sleep_mins;
    private RelativeLayout summary_view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.summary_fragment_layout, null);

        initiViews(view);
        getSummaryDataFromServer();
        return view;
    }

  private void initiViews(View view) {
    actual_task = (RegularFontTextView) view.findViewById(R.id.actual_task);
    actual_steps = (RegularFontTextView) view.findViewById(R.id.actual_steps);
    actual_water = (RegularFontTextView) view.findViewById(R.id.actual_water);
    actual_sleep = (RegularFontTextView) view.findViewById(R.id.actual_sleep);
    actual_cal_gained = (RegularFontTextView) view.findViewById(R.id.actual_cal_gained);
    actual_cal_burnt = (RegularFontTextView) view.findViewById(R.id.actual_cal_burnt);

    total_task = (RegularFontTextView) view.findViewById(R.id.total_task);
    total_steps = (RegularFontTextView) view.findViewById(R.id.total_steps);
    total_water = (RegularFontTextView) view.findViewById(R.id.total_water);
    total_sleep = (RegularFontTextView) view.findViewById(R.id.total_sleep);
    total_cal_burnt = (RegularFontTextView) view.findViewById(R.id.total_cal_burnt);
    total_cal_gained = (RegularFontTextView) view.findViewById(R.id.total_cal_burnt);

        steps_walked_progress_bar = (ProgressBar) view.findViewById(R.id.steps_walked_progress_bar);
        steps_walked_progress_bar.setOnClickListener(this);

        water_consumed_progress_bar = (ProgressBar) view.findViewById(R.id.water_consumed_progress_bar);
        water_consumed_progress_bar.setOnClickListener(this);

        sleep_consumed_progress_bar = (ProgressBar) view.findViewById(R.id.sleep_consumed_progress_bar);
        sleep_consumed_progress_bar.setOnClickListener(this);

      summary_view = (RelativeLayout)view.findViewById(R.id.summary_view);

      Calendar cal = Calendar.getInstance();
      Toast.makeText(getContext(), cal.get(Calendar.HOUR_OF_DAY) + "", Toast.LENGTH_SHORT).show();
      if(cal.get(Calendar.HOUR_OF_DAY) <= 15){
          summary_view.setBackgroundColor(getResources().getColor(R.color.white));
      } else {
          summary_view.setBackgroundColor(getResources().getColor(R.color.black));
      }
    }

    private void getSummaryDataFromServer() {
        NetworkHandler handler = new NetworkHandler() {
            @Override
            public void success(Object response) {
                mActivitySummary = (ActivitySummary) response;
                actual_sleep.setText(String.valueOf(mActivitySummary.getSleepConsumed()));
                actual_steps.setText(String.valueOf(mActivitySummary.getStepsWalked()));
                actual_water.setText(String.valueOf(mActivitySummary.getWaterConsumed()));
                actual_cal_gained.setText(String.valueOf(mActivitySummary.getCaloriesGained()));
                actual_cal_burnt.setText(String.valueOf(mActivitySummary.getCaloriesBurned()));
            }

            @Override
            public void failure(Exception e) {
                Toast.makeText(getActivity().getApplicationContext(), "Unable to fetch", Toast.LENGTH_SHORT).show();
            }
        };
        VolleyRequest.getSummaryData(getActivity(), handler, "13-03-2016");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.steps_walked_progress_bar:
                viewStepsDialog();

                break;

            case R.id.water_consumed_progress_bar:
                viewWaterDialog();

                break;

            case R.id.sleep_consumed_progress_bar:
                viewSleepDialog();
                break;
        }
    }

    private void viewStepsDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.steps_walked_dialog);

        RegularFontEditText text_in_steps = (RegularFontEditText) dialog.findViewById(R.id.text_in_steps);
        String steps = text_in_steps.getText().toString();
        int intSteps = 0;

        if (steps !=null  && !steps.isEmpty()) {
            intSteps = Integer.parseInt(steps);
            steps_walked_progress_bar.setProgress(intSteps);
        }

        steps_walked_progress_bar.setProgress(intSteps);
        number_hrs = (NumberPicker) dialog.findViewById(R.id.number_hrs);
        number_hrs.setMinValue(0);
        number_hrs.setMaxValue(12);
        number_hrs.setWrapSelectorWheel(false);
        number_hrs.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                steps_walked_progress_bar.setProgress(newVal);
            }
        });
        number_mins = (NumberPicker) dialog.findViewById(R.id.number_mins);
        number_mins.setMinValue(00);
        number_mins.setMaxValue(60);
        dialog.show();

        done_steps_button = (RegularFontTextView) dialog.findViewById(R.id.done_steps_button);
        done_steps_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void viewWaterDialog() {
        final Dialog dialog_water = new Dialog(getActivity());
        dialog_water.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_water.setContentView(R.layout.water_consumed_dialog);
        water_edittext = (EditText) dialog_water.findViewById(R.id.water_edittext);

        final String waterQty = water_edittext.getText().toString();
        int intWater = 0;

        if (waterQty !=null && !waterQty.isEmpty()) {
            intWater = Integer.parseInt(waterQty);
            water_consumed_progress_bar.setProgress(intWater);
        }
        water_spinner = (AppCompatSpinner) dialog_water.findViewById(R.id.water_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.water_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        water_spinner.setAdapter(adapter);
        water_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CharSequence selectedItem = (CharSequence) parent.getItemAtPosition(position);
                waterunit = selectedItem.toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        dialog_water.show();

        RegularFontTextView done_steps_button = (RegularFontTextView) dialog_water.findViewById(R.id.done_steps_button);
        done_steps_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),""+waterQty,Toast.LENGTH_SHORT).show();
                dialog_water.dismiss();
            }
        });
    }

    private void viewSleepDialog() {
        final Dialog dialog_sleep = new Dialog(getActivity());
        dialog_sleep.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_sleep.setContentView(R.layout.sleep_consumed_dialog);

        sleep_hrs = (EditText) dialog_sleep.findViewById(R.id.sleep_hrs);
        String sleepHrs = sleep_hrs.getText().toString();
        int intSleepHrs = 0;

        if (sleepHrs !=null && !sleepHrs.isEmpty()) {
            intSleepHrs = Integer.parseInt(sleepHrs);
            Toast.makeText(getContext(),""+sleepHrs,Toast.LENGTH_SHORT).show();
            sleep_consumed_progress_bar.setProgress(intSleepHrs);
        }


        sleep_mins = (EditText) dialog_sleep.findViewById(R.id.sleep_min);
        String sleepMins = sleep_mins.getText().toString();
        int intSleepMin = 0;

        if (sleepMins !=null && !sleepMins.isEmpty()) {
            intSleepMin = Integer.parseInt(sleepMins);
            Toast.makeText(getContext(),""+sleepMins,Toast.LENGTH_SHORT).show();
           // water_consumed_progress_bar.setProgress(intSleepMin);
        }

        dialog_sleep.show();

        done_sleep_button = (RegularFontTextView) dialog_sleep.findViewById(R.id.done_sleep_button);
        done_sleep_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_sleep.dismiss();
            }
        });


    }
}
