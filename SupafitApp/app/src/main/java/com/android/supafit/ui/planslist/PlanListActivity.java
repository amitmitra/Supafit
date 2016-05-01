package com.android.supafit.ui.planslist;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
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
import com.android.supafit.netoperations.VolleyRequest;
import com.android.supafit.netoperations.handler.NetworkHandler;
import com.android.supafit.netoperations.networkmodel.program.Program;
import com.android.supafit.utils.AppUtility;
import com.viewpagerindicator.CirclePageIndicator;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

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
  @Bind(R.id.coordinartor_layout)CoordinatorLayout mCoordinatorLayout;

  ProgressDialog mProgressDialog;
  List<Program> plans;

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

    fetchProgramDataFromServer();
  }

  private void fetchProgramDataFromServer(){
    mProgressDialog = new ProgressDialog(this);
    mProgressDialog.setMessage("Fetching Plan Details . . .");
    mProgressDialog.show();
    NetworkHandler handler = new NetworkHandler() {
      @Override
      public void success(Object response) {
        plans = (List<Program>)response;
        loadPlan1View(plans.get(0));
        loadPlan2View(plans.get(1));
        loadPlan3View(plans.get(2));
        mProgressDialog.dismiss();
      }

      @Override
      public void failure(Exception e) {
        AppUtility.showLongSnackBar(mCoordinatorLayout, "Problem fetching plans data from server . . .");
        mProgressDialog.dismiss();
      }
    };

    VolleyRequest.getPlansPackage(this, handler);
  }

  @Override
  public void loadPlan1View(Program planPackage) {
    planLayout1.setVisibility(View.VISIBLE);
    planTitle1.setText(planPackage.getTitle());
    planDescription1.setText(planPackage.getDescription());
    planResult1.setText(planPackage.getName());
    planCost1.setText("Rs. " + planPackage.getCost());
    planCost1.setTextColor(getResources().getColor(R.color.dark_green));
    planLayout1.setTag(planPackage);
  }

  @Override
  public void loadPlan2View(Program planPackage) {
    planLayout2.setVisibility(View.VISIBLE);
    planTitle2.setText(planPackage.getTitle());
    planDescription2.setText(planPackage.getDescription());
    planResult2.setText(planPackage.getName());
    planCost2.setText("Rs. " + planPackage.getCost());
    planCost2.setTextColor(getResources().getColor(R.color.sleepingYellow));
    planLayout2.setTag(planPackage);
  }

  @Override
  public void loadPlan3View(Program planPackage) {
    planLayout3.setVisibility(View.VISIBLE);
    planTitle3.setText(planPackage.getTitle());
    planDescription3.setText(planPackage.getDescription());
    planResult3.setText(planPackage.getName());
    planCost3.setText("Rs. " + planPackage.getCost());
    planCost3.setTextColor(getResources().getColor(R.color.text_blue));
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
        Program planPackage1 = (Program) planLayout1.getTag();
        if (!isPlan1selected) {
          planLayout1.setBackgroundColor(Color.parseColor("#9900AA00"));
          //addToPlanCost(planPackage1.getCost());
          setTotalCost(Integer.parseInt(planPackage1.getCost()));
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
        Program planPackage2 = (Program) planLayout2.getTag();
        if (!isPlan2selected) {
          planLayout2.setBackgroundColor(Color.parseColor("#9900AA00"));
          //addToPlanCost(planPackage2.getCost());
          setTotalCost(Integer.parseInt(planPackage2.getCost()));
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
        Program planPackage3 = (Program) planLayout3.getTag();
        if (!isPlan3selected) {
          planLayout3.setBackgroundColor(Color.parseColor("#9900AA00"));
          //addToPlanCost(planPackage3.getCost());
          setTotalCost(Integer.parseInt(planPackage3.getCost()));
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
      moveToConfirmPlan(plans.get(0));
    }else if(isPlan2selected){
      moveToConfirmPlan(plans.get(1));
    }else if(isPlan3selected){
      moveToConfirmPlan(plans.get(2));
    }else{
    Toast.makeText(this,"Select a plan",Toast.LENGTH_SHORT).show();
    }
  }

  private void moveToConfirmPlan(Program planPackage) {
    Intent confrimPlan = new Intent(PlanListActivity.this,PlanConfirmDialog.class);
    confrimPlan.putExtra("planPackage",planPackage);
    startActivity(confrimPlan);
    finish();
  }


  @Override
  public void goToNextScreen() {
    Toast.makeText(this,"Plan creation success",Toast.LENGTH_SHORT).show();
    onBackPressed();
  }

  @Override
  public void showError() {
    Toast.makeText(this,"Plan creation error",Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    overridePendingTransition(R.anim.slide_half_left_to_right, R.anim.slide_left_to_right);
  }
}
