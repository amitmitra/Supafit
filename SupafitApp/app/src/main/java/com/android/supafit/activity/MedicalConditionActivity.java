package com.android.supafit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.netoperations.networkmodel.medicalcondition.MedicalCondition;
import com.android.supafit.netoperations.networkmodel.user.User;
import com.android.supafit.ui.RegularFontEditText;
import com.android.supafit.ui.RegularFontTextViewBold;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by harsh on 3/31/16.
 */
public class MedicalConditionActivity extends AppCompatActivity implements View.OnClickListener {

  @Bind(R.id.hypertension) CheckBox hypertension;
  @Bind(R.id.diabetes) CheckBox diabetes;
  @Bind(R.id.hypothyroid) CheckBox hypothyroid;
  @Bind(R.id.asthama) CheckBox asthama;
  @Bind(R.id.highbp) CheckBox highbp;
  @Bind(R.id.physicalinjury) CheckBox physicalinjury;
  @Bind(R.id.allergy_edittext) RegularFontEditText allergyEdittext;

  private RegularFontTextViewBold title_text;
  private AppCompatImageView back_button;
  private AppCompatImageView done_button;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.medical_condition_layout);
    ButterKnife.bind(this);
    initViews();
    setUpMedicaltoolbar();
  }

  private void setUpMedicaltoolbar() {
    title_text = (RegularFontTextViewBold) findViewById(R.id.title_text);
    back_button = (AppCompatImageView) findViewById(R.id.back_button);
    done_button = (AppCompatImageView) findViewById(R.id.done_button);
    done_button.setOnClickListener(this);
    back_button.setOnClickListener(this);
  }

  private void initViews() {

  }

  private boolean submitItems() {
    User user = (User)getIntent().getSerializableExtra("user_data");
    ArrayList<MedicalCondition> medicalConditionArrayList = new ArrayList<>();
    if(hypothyroid.isChecked()){
      MedicalCondition medicalCondition = new MedicalCondition();
      medicalCondition.setId(2);
      medicalCondition.setCondition("Hypothyroid");
      medicalCondition.setDescription("Hypothyroid discr");
      medicalConditionArrayList.add(medicalCondition);
    }
    if(asthama.isChecked()){
      MedicalCondition medicalCondition = new MedicalCondition();
      medicalCondition.setId(1);
      medicalCondition.setCondition("Asthama");
      medicalCondition.setDescription("Asthama");
      medicalConditionArrayList.add(medicalCondition);
    }
    if(hypertension.isChecked()){
      MedicalCondition medicalCondition = new MedicalCondition();
      medicalCondition.setId(1);
      medicalCondition.setCondition("Hypertension");
      medicalCondition.setDescription("Hypertension");
      medicalConditionArrayList.add(medicalCondition);
    }
    if(diabetes.isChecked()){
      MedicalCondition medicalCondition = new MedicalCondition();
      medicalCondition.setId(3);
      medicalCondition.setCondition("Diabetes");
      medicalCondition.setDescription("Diabetes");
      medicalConditionArrayList.add(medicalCondition);
    }
    if(highbp.isChecked()){
      MedicalCondition medicalCondition = new MedicalCondition();
      medicalCondition.setId(3);
      medicalCondition.setCondition("High Blood Pressure");
      medicalCondition.setDescription("High Blood Pressure");
      medicalConditionArrayList.add(medicalCondition);
    }
    if(physicalinjury.isChecked()){
      MedicalCondition medicalCondition = new MedicalCondition();
      medicalCondition.setId(1);
      medicalCondition.setCondition("Physical Injury");
      medicalCondition.setDescription("Physical Injury");
      medicalConditionArrayList.add(medicalCondition);
    }
    user.setMedicalConditions(medicalConditionArrayList);
    Intent intent = new Intent(MedicalConditionActivity.this, MyGoalsActivity.class);
    intent.putExtra("user_data",user);
    startActivity(intent);
    overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
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
