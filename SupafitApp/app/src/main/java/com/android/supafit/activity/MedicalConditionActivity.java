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
import com.android.supafit.model.networkmodel.MedicalCondition;
import com.android.supafit.netoperations.VolleyRequest;
import com.android.supafit.netoperations.handler.NetworkHandler;
import com.android.supafit.ui.RegularFontEditText;
import com.android.supafit.ui.planslist.PlanListActivity;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by harsh on 3/31/16.
 */
public class MedicalConditionActivity extends AppCompatActivity {

  @Bind(R.id.hypertension) CheckBox hypertension;
  @Bind(R.id.diabetes) CheckBox diabetes;
  @Bind(R.id.hypothyroid) CheckBox hypothyroid;
  @Bind(R.id.asthama) CheckBox asthama;
  @Bind(R.id.highbp) CheckBox highbp;
  @Bind(R.id.physicalinjury) CheckBox physicalinjury;
  @Bind(R.id.allergy_edittext) RegularFontEditText allergyEdittext;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.medical_condition_layout);
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
    NetworkHandler signInHandler = new NetworkHandler() {
      @Override
      public void success(Object response) {
        Intent intent = new Intent(MedicalConditionActivity.this, MyGoalsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
      }

      @Override
      public void failure(Exception e) {
        Toast.makeText(MedicalConditionActivity.this,"network error",Toast.LENGTH_SHORT).show();
      }
    };

    try {
      VolleyRequest.updateMedicalConditionInformation(MedicalConditionActivity.this,signInHandler,medicalConditionArrayList);
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
