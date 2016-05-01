package com.android.supafit.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.utils.MyYAxisValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaterConsumptionFragment extends Fragment implements OnChartValueSelectedListener, SeekBar.OnSeekBarChangeListener {

    private AppCompatTextView average_water_consumed;
    private AppCompatTextView recomended_average_water;


    protected BarChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;
    private AppCompatCheckBox choose_days_checkbox_water,seven_days_checkbox_water;
    String[] mMonths = new String[] {
            "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
    };
    public WaterConsumptionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_water_consumption, container, false);

        initializeViews(view);
        barGraphData(view);
        return view;
    }
    private void initializeViews(View rootView) {
        average_water_consumed = (AppCompatTextView) rootView.findViewById(R.id.average_water_consumed);
        average_water_consumed.setText("5 Litres");

        recomended_average_water = (AppCompatTextView) rootView.findViewById(R.id.recomended_average_water);
        recomended_average_water.setText("8 Litres");

        choose_days_checkbox_water = (AppCompatCheckBox) rootView.findViewById(R.id.choose_days_checkbox_water);
        seven_days_checkbox_water = (AppCompatCheckBox) rootView.findViewById(R.id.seven_days_checkbox_water);

        seven_days_checkbox_water.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getActivity(),"isChecked  "+isChecked,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),"isChecked "+isChecked,Toast.LENGTH_SHORT).show();
                }

            }
        });
        choose_days_checkbox_water.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getActivity(),"Seven days isChecked "+isChecked,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),"Seven days isChecked "+isChecked,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void barGraphData(View rootView) {

        tvX = (TextView) rootView.findViewById(R.id.tvXMax);

        mSeekBarX = (SeekBar) rootView.findViewById(R.id.seekBar1);
        mSeekBarY = (SeekBar) rootView.findViewById(R.id.seekBar2);

        mChart = (BarChart) rootView.findViewById(R.id.chart_seep);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(false);
        mChart.setTouchEnabled(false);
        mChart.setDrawValueAboveBar(true);
        mChart.animateY(5000);

        mChart.setDescription("");
        mChart.setMaxVisibleValueCount(10); //pass no of x coordinates values

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setSpaceBetweenLabels(2);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLimitLinesBehindData(true);
        // xAxis.setAxisLineColor(android.R.color.white);

        YAxisValueFormatter custom = new MyYAxisValueFormatter();

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinValue(0f);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawZeroLine(false);


        mChart.getAxisRight().setShowOnlyMinMax(false);
        mChart.getAxisRight().setDrawAxisLine(false);
        mChart.getAxisRight().setAxisMaxValue(0f);

        setData(7, 20);

        mSeekBarY.setProgress(20);
        mSeekBarX.setProgress(7);

        mSeekBarY.setOnSeekBarChangeListener(this);
        mSeekBarX.setOnSeekBarChangeListener(this);
    }
    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        setData(mSeekBarX.getProgress() + 1, mSeekBarY.getProgress());
        mChart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    private void setData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add(mMonths[i % 7]);
        }

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();

        for (int i = 0; i < count; i++) {
            float mult = (range + 1);
            float val = (float) (Math.random() * mult);
            yVals1.add(new BarEntry(val, i));
        }

        BarDataSet set1 = new BarDataSet(yVals1, "Water Consumption");
        set1.setHighLightAlpha(0);
        set1.setColors(new int[] { R.color.waterBlue }, getContext());

        set1.setBarSpacePercent(35f);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);

        BarData data = new BarData(xVals, dataSets);
        data.setValueTextSize(10f);

        mChart.setData(data);
    }

}
