package com.android.supafit.ui.planslist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.activity.MainActivity;
import com.android.supafit.model.networkmodel.PlanPackage;
import com.viewpagerindicator.CirclePageIndicator;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by harsh on 2/13/16.
 */
public class PlanListActivity extends AppCompatActivity implements PlanListMvpView {

  @Bind(R.id.toolbar)
  Toolbar toolbar;
  @Bind(R.id.appbar_layout)
  AppBarLayout appbarLayout;
  @Bind(R.id.plan_list_loader) DilatingDotsProgressBar planListLoader;
  @Bind(R.id.plan_list_view_pager)
  ViewPager planListViewPager;
  @Bind(R.id.indicator) CirclePageIndicator indicator;
  @Bind(R.id.plan_list_banner) RelativeLayout planListBanner;
  @Bind(R.id.plan_title1) TextView planTitle1;
  @Bind(R.id.plan_description1) TextView planDescription1;
  @Bind(R.id.plan_result1) TextView planResult1;
  @Bind(R.id.plan_cost1) TextView planCost1;
  @Bind(R.id.plan_layout_1) RelativeLayout planLayout1;
  @Bind(R.id.plan_title2) TextView planTitle2;
  @Bind(R.id.plan_description2) TextView planDescription2;
  @Bind(R.id.plan_result2) TextView planResult2;
  @Bind(R.id.plan_cost2) TextView planCost2;
  @Bind(R.id.plan_layout_2) RelativeLayout planLayout2;
  @Bind(R.id.plan_layout12) LinearLayout planLayout12;
  @Bind(R.id.plan_title3) TextView planTitle3;
  @Bind(R.id.plan_description3) TextView planDescription3;
  @Bind(R.id.plan_result3) TextView planResult3;
  @Bind(R.id.plan_cost3) TextView planCost3;
  @Bind(R.id.plan_layout3) LinearLayout planLayout3;
  @Bind(R.id.next_icon) ImageView nextIcon;
  @Bind(R.id.plan_next_layout) LinearLayout planNextLayout;
  @Bind(R.id.total_plan_cost) TextView totalPlanCost;

  private int currentPage = 0;
  private int NUM_PAGES = 0;
  private static final Integer[] IMAGES = { R.drawable.andaz_apana_apana_movie_review1, R.drawable.aaa, R.drawable.hqdefault };
  private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
  private int totalCost = 0;
  private boolean isPlan1selected = false;
  private boolean isPlan2selected = false;
  private boolean isPlan3selected = false;

  public static Intent getUserListActivityIntent(Context context) {
    Intent planListActivityIntent = new Intent(context, PlanListActivity.class);
    return planListActivityIntent;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.plan_list_view);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    setTitle("Select Your Plan");
    initViewPager();
  }

  @Override
  public void loadPlan1View(PlanPackage planPackage) {
    planLayout1.setVisibility(View.VISIBLE);
    planTitle1.setText(planPackage.getTitle());
    planDescription1.setText(planPackage.getDescription());
    planResult1.setText(planPackage.getName());
    planCost1.setText("Rs. " + planPackage.getCost());
    planLayout1.setTag(planPackage);
  }

  @Override
  public void loadPlan2View(PlanPackage planPackage) {
    planLayout2.setVisibility(View.VISIBLE);
    planTitle2.setText(planPackage.getTitle());
    planDescription2.setText(planPackage.getDescription());
    planResult2.setText(planPackage.getName());
    planCost2.setText("Rs. " + planPackage.getCost());
    planLayout2.setTag(planPackage);
  }

  @Override
  public void loadPlan3View(PlanPackage planPackage) {
    planLayout3.setVisibility(View.VISIBLE);
    planTitle3.setText(planPackage.getTitle());
    planDescription3.setText(planPackage.getDescription());
    planResult3.setText(planPackage.getName());
    planCost3.setText("Rs. " + planPackage.getCost());
    planLayout3.setTag(planPackage);
  }

  @Override
  public void startProgressBar() {
    planListLoader.setVisibility(View.VISIBLE);
    planListLoader.show(500);
    planListLoader.bringToFront();
  }

  @Override
  public void stopProgressBar() {
    planListLoader.hide();
    planListLoader.setVisibility(View.GONE);
  }

  private void initViewPager() {
    for (int i = 0; i < IMAGES.length; i++) {
      ImagesArray.add(IMAGES[i]);
    }

    planListViewPager.setAdapter(new SlidingImageAdapter(this, ImagesArray));

    indicator.setViewPager(planListViewPager);

    final float density = getResources().getDisplayMetrics().density;

    indicator.setRadius(4 * density);

    NUM_PAGES = IMAGES.length;

    // Pager listener over indicator
    indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

      @Override
      public void onPageSelected(int position) {
        currentPage = position;

      }

      @Override
      public void onPageScrolled(int pos, float arg1, int arg2) {

      }

      @Override
      public void onPageScrollStateChanged(int pos) {

      }
    });

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  @OnClick({ R.id.plan_layout_1, R.id.plan_layout_2, R.id.plan_layout3 })
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.plan_layout_1:
        PlanPackage planPackage1 = (PlanPackage) planLayout1.getTag();
        if (!isPlan1selected) {
          planLayout1.setBackgroundColor(Color.parseColor("#9900AA00"));
          //addToPlanCost(planPackage1.getCost());
          setTotalCost(planPackage1.getCost());
          isPlan1selected = true;
          isPlan2selected = false;
          isPlan3selected = false;
          planLayout2.setBackgroundColor(Color.parseColor("#FFFFFF"));
          planLayout3.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
          planLayout1.setBackgroundColor(Color.parseColor("#FFFFFF"));
          isPlan1selected = false;
          //minusToPlanCost(planPackage1.getCost());
          setTotalCost(0);
        }
        break;
      case R.id.plan_layout_2:
        PlanPackage planPackage2 = (PlanPackage) planLayout2.getTag();
        if (!isPlan2selected) {
          planLayout2.setBackgroundColor(Color.parseColor("#9900AA00"));
          //addToPlanCost(planPackage2.getCost());
          setTotalCost(planPackage2.getCost());
          isPlan2selected = true;
          isPlan1selected = false;
          isPlan3selected = false;
          planLayout1.setBackgroundColor(Color.parseColor("#FFFFFF"));
          planLayout3.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
          planLayout2.setBackgroundColor(Color.parseColor("#FFFFFF"));
          isPlan2selected = false;
          //minusToPlanCost(planPackage2.getCost());
          setTotalCost(0);
        }
        break;
      case R.id.plan_layout3:
        PlanPackage planPackage3 = (PlanPackage) planLayout3.getTag();
        if (!isPlan3selected) {
          planLayout3.setBackgroundColor(Color.parseColor("#9900AA00"));
          //addToPlanCost(planPackage3.getCost());
          setTotalCost(planPackage3.getCost());
          isPlan3selected = true;
          isPlan2selected = false;
          isPlan1selected = false;
          planLayout1.setBackgroundColor(Color.parseColor("#FFFFFF"));
          planLayout2.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
          planLayout3.setBackgroundColor(Color.parseColor("#FFFFFF"));
          //minusToPlanCost(planPackage3.getCost());
          isPlan3selected = false;
          setTotalCost(0);
        }
        break;
    }
  }

  private void addToPlanCost(int cost) {
    totalCost = totalCost + cost;
    totalPlanCost.setText("Total Cost Rs. " + totalCost);
  }

  public void minusToPlanCost(int cost) {
    totalCost = totalCost - cost;
    if (totalCost < 0) {
      totalCost = 0;
    } else {
      totalPlanCost.setText("Total Cost Rs. " + totalCost);
    }
  }

  public void setTotalCost(int cost) {
    totalCost = cost;
    totalPlanCost.setText("Total Cost Rs. " + totalCost);
  }

  @OnClick(R.id.next_icon)
  public void onClick() {
    if(isPlan1selected) {
    }else if(isPlan2selected){
    }else if(isPlan3selected){
    }else{

    }
  }

  @Override
  public void goToNextScreen() {
    Toast.makeText(this,"Plan creation success",Toast.LENGTH_SHORT).show();
    startActivity(new Intent(this, MainActivity.class));
  }

  @Override
  public void showError() {
    Toast.makeText(this,"Plan creation error",Toast.LENGTH_SHORT).show();
  }
}
