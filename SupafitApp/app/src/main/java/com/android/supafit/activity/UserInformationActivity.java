package com.android.supafit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.supafit.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by harsh on 3/17/16.
 */
public class UserInformationActivity  extends AppCompatActivity {

  @Bind(R.id.name_edittext) EditText nameEditText;
  @Bind(R.id.age__edittext) EditText ageEditText;
  @Bind(R.id.weight_edittext) EditText weightEditText;
  @Bind(R.id.height_ft__edittext) EditText heightFeetEditText;
  @Bind(R.id.height_inch__edittext) EditText heighInchEditText;
  @Bind(R.id.phone_no__edittext) EditText phoneNumberEditText;
  @Bind(R.id.gender_radio_group) RadioGroup genderRadioGroup;
  @Bind(R.id.lifestyle_spinner) AppCompatSpinner lifeStyleSpinner;

  private static final String[] lifestyles= {"Sedentary","Lightly Active","Moderatly Active","Very Active"};

  private String name,age,phoneNumber,height,weight,lifestyle,gender;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.user_basic_information);
    ButterKnife.bind(this);
    initViews();
  }

  private void initViews() {
    heighInchEditText.setText("0");
    genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == R.id.male_radio_button){
          gender = "Male";
        }else if(checkedId == R.id.female_radio_button){
          gender = "Female";
        }
      }
    });
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.lifestyle_array, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    lifeStyleSpinner.setAdapter(adapter);
    lifeStyleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CharSequence selectedItem = (CharSequence)parent.getItemAtPosition(position);
        lifestyle = selectedItem.toString();
      }

      @Override
      public void onNothingSelected(AdapterView<?> parent) {

      }
    });
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
    if(!nameEditText.getText().toString().isEmpty()){
      name = nameEditText.getText().toString();
    }else{
      Toast.makeText(this,"Please enter name",Toast.LENGTH_SHORT).show();
      return false;
    }
    if(!phoneNumberEditText.getText().toString().isEmpty()){
      phoneNumber = phoneNumberEditText.getText().toString();
    }else{
      Toast.makeText(this,"Please enter Phone Number",Toast.LENGTH_SHORT).show();
      return false;
    }
    if(!ageEditText.getText().toString().isEmpty()){
      age = ageEditText.getText().toString();
    }else{
      Toast.makeText(this,"Please enter age",Toast.LENGTH_SHORT).show();
      return false;
    }
    if(!weightEditText.getText().toString().isEmpty()){
      weight = weightEditText.getText().toString();
    }else{
      Toast.makeText(this,"Please enter name",Toast.LENGTH_SHORT).show();
      return false;
    }
    if(!heightFeetEditText.getText().toString().isEmpty() && !heighInchEditText.getText().toString().isEmpty()){
      height = heightFeetEditText.getText().toString() + "ft," + heighInchEditText.getText().toString() + "in";
    }else{
      Toast.makeText(this,"Please enter Height",Toast.LENGTH_SHORT).show();
      return false;
    }
    if(gender!=null){

    }else {
      Toast.makeText(this,"Please select your gender",Toast.LENGTH_SHORT).show();
      return false;
    }
    if(lifestyle!=null){

    }else {
      Toast.makeText(this,"Please select your lifestyle",Toast.LENGTH_SHORT).show();
      return false;
    }
    return false;
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ButterKnife.unbind(this);
  }
}
