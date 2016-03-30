package com.android.supafit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.model.networkmodel.FitnessGoal;
import com.android.supafit.netoperations.VolleyRequest;
import com.android.supafit.netoperations.handler.NetworkHandler;
import com.android.supafit.ui.planslist.PlanListActivity;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by harsh on 3/31/16.
 */
public class MyGoalsActivity extends AppCompatActivity {

  @Bind(R.id.eatbetter) CheckBox eatbetter;
  @Bind(R.id.livehealthier) CheckBox livehealthier;
  @Bind(R.id.weightloss) CheckBox weightloss;
  @Bind(R.id.bearunner) CheckBox bearunner;
  @Bind(R.id.muscelesbuilder) CheckBox muscelesbuilder;
  @Bind(R.id.weightgain) CheckBox weightgain;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.mygoals_layout);
    ButterKnife.bind(this);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    initViews();
  }

  private void initViews() {

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.basic_information_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_ok:
        return submitItems();

    }
    return super.onOptionsItemSelected(item);
  }

  private boolean submitItems() {
    ArrayList<FitnessGoal> medicalConditionArrayList = new ArrayList<>();
    if (eatbetter.isChecked()) {
      FitnessGoal medicalCondition = new FitnessGoal();
      medicalCondition.setId(2);
      medicalCondition.setGoal("Eat Better");
      medicalCondition.setDescription("Eat better");
      medicalConditionArrayList.add(medicalCondition);
    }
    if (livehealthier.isChecked()) {
      FitnessGoal medicalCondition = new FitnessGoal();
      medicalCondition.setId(1);
      medicalCondition.setGoal("Be Fitter");
      medicalCondition.setDescription("Overall fitness");
      medicalConditionArrayList.add(medicalCondition);
    }
    if (weightloss.isChecked()) {
      FitnessGoal medicalCondition = new FitnessGoal();
      medicalCondition.setId(2);
      medicalCondition.setGoal("Lose Weight");
      medicalCondition.setDescription("Lose Weight");
      medicalConditionArrayList.add(medicalCondition);
    }
    if (bearunner.isChecked()) {
      FitnessGoal medicalCondition = new FitnessGoal();
      medicalCondition.setId(3);
      medicalCondition.setGoal("Be A Runner");
      medicalCondition.setDescription("Be A Runner");
      medicalConditionArrayList.add(medicalCondition);
    }
    if (muscelesbuilder.isChecked()) {
      FitnessGoal medicalCondition = new FitnessGoal();
      medicalCondition.setId(3);
      medicalCondition.setGoal("Gain Muscles");
      medicalCondition.setDescription("Gain Muscles");
      medicalConditionArrayList.add(medicalCondition);
    }
    if (weightgain.isChecked()) {
      FitnessGoal medicalCondition = new FitnessGoal();
      medicalCondition.setId(4);
      medicalCondition.setGoal("Gain Weight");
      medicalCondition.setDescription("Gain Weight");
      medicalConditionArrayList.add(medicalCondition);
    }
    NetworkHandler signInHandler = new NetworkHandler() {
      @Override
      public void success(Object response) {
        Intent intent = new Intent(MyGoalsActivity.this, PlanListActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
      }

      @Override
      public void failure(Exception e) {
        Toast.makeText(MyGoalsActivity.this, "network error", Toast.LENGTH_SHORT).show();
      }
    };

    try {
      VolleyRequest.updateMyGoals(MyGoalsActivity.this, signInHandler,
          medicalConditionArrayList);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return true;
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ButterKnife.unbind(this);
  }
}
