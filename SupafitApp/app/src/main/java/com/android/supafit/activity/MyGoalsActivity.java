package com.android.supafit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.netoperations.VolleyRequest;
import com.android.supafit.netoperations.handler.NetworkHandler;
import com.android.supafit.netoperations.networkmodel.goal.FitnessGoal;
import com.android.supafit.netoperations.networkmodel.user.User;
import com.android.supafit.ui.RegularFontTextViewBold;
import com.android.supafit.ui.planslist.PlanListActivity;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by harsh on 3/31/16.
 */
public class MyGoalsActivity extends AppCompatActivity implements View.OnClickListener {

  @Bind(R.id.eatbetter) CheckBox eatbetter;
  @Bind(R.id.livehealthier) CheckBox livehealthier;
  @Bind(R.id.weightloss) CheckBox weightloss;
  @Bind(R.id.bearunner) CheckBox bearunner;
  @Bind(R.id.muscelesbuilder) CheckBox muscelesbuilder;
  @Bind(R.id.weightgain) CheckBox weightgain;
  private RegularFontTextViewBold title_text;
  private AppCompatImageView back_button;
  private AppCompatImageView done_button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.mygoals_layout);
    ButterKnife.bind(this);
    initViews();
    setUpGoalsToolbar();
  }

  private void setUpGoalsToolbar() {

    title_text = (RegularFontTextViewBold) findViewById(R.id.title_text);
    back_button = (AppCompatImageView) findViewById(R.id.back_button);
    done_button = (AppCompatImageView) findViewById(R.id.done_button);
    done_button.setOnClickListener(this);
    back_button.setOnClickListener(this);
    title_text.setText("Goals");
  }

  private void initViews() {

  }

  private boolean submitItems() {
    User user = (User)getIntent().getSerializableExtra("user_data");
    ArrayList<FitnessGoal> fitnessGoalArrayList = new ArrayList<>();
    if (eatbetter.isChecked()) {
      FitnessGoal medicalCondition = new FitnessGoal();
      medicalCondition.setId(2);
      medicalCondition.setGoal("Eat Better");
      medicalCondition.setDescription("Eat better");
      fitnessGoalArrayList.add(medicalCondition);
    }
    if (livehealthier.isChecked()) {
      FitnessGoal medicalCondition = new FitnessGoal();
      medicalCondition.setId(1);
      medicalCondition.setGoal("Be Fitter");
      medicalCondition.setDescription("Overall fitness");
      fitnessGoalArrayList.add(medicalCondition);
    }
    if (weightloss.isChecked()) {
      FitnessGoal medicalCondition = new FitnessGoal();
      medicalCondition.setId(2);
      medicalCondition.setGoal("Lose Weight");
      medicalCondition.setDescription("Lose Weight");
      fitnessGoalArrayList.add(medicalCondition);
    }
    if (bearunner.isChecked()) {
      FitnessGoal medicalCondition = new FitnessGoal();
      medicalCondition.setId(3);
      medicalCondition.setGoal("Be A Runner");
      medicalCondition.setDescription("Be A Runner");
      fitnessGoalArrayList.add(medicalCondition);
    }
    if (muscelesbuilder.isChecked()) {
      FitnessGoal medicalCondition = new FitnessGoal();
      medicalCondition.setId(3);
      medicalCondition.setGoal("Gain Muscles");
      medicalCondition.setDescription("Gain Muscles");
      fitnessGoalArrayList.add(medicalCondition);
    }
    if (weightgain.isChecked()) {
      FitnessGoal medicalCondition = new FitnessGoal();
      medicalCondition.setId(4);
      medicalCondition.setGoal("Gain Weight");
      medicalCondition.setDescription("Gain Weight");
      fitnessGoalArrayList.add(medicalCondition);
    }
   user.setGoals(fitnessGoalArrayList);
    NetworkHandler signInHandler = new NetworkHandler() {
      @Override
      public void success(Object response) {
        Intent intent = new Intent(MyGoalsActivity.this, PlanListActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
      }

      @Override
      public void failure(Exception e) {
        Toast.makeText(MyGoalsActivity.this,"network error",Toast.LENGTH_SHORT).show();
      }
    };

    try {
      VolleyRequest.updateUserInformation(MyGoalsActivity.this,signInHandler,user);
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

  @Override
  public void onClick(View v) {
    switch(v.getId()){
      case R.id.done_button:
        submitItems();
        Toast.makeText(this,"clicked done",Toast.LENGTH_SHORT).show();
        break;
      case R.id.back_button:
        finish();
        break;
    }
  }
}
