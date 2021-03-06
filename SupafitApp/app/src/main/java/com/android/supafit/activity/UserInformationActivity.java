package com.android.supafit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.netoperations.networkmodel.user.PhoneNumber;
import com.android.supafit.netoperations.networkmodel.user.User;
import com.android.supafit.netoperations.networkmodel.user.UserPhysic;
import com.android.supafit.ui.RegularFontTextViewBold;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by harsh on 3/17/16.
 */
public class UserInformationActivity  extends AppCompatActivity implements View.OnClickListener {

  @Bind(R.id.name_edittext) EditText nameEditText;
  @Bind(R.id.age__edittext) EditText ageEditText;
  @Bind(R.id.weight_edittext) EditText weightEditText;
  @Bind(R.id.height_ft__edittext) EditText heightFeetEditText;
  @Bind(R.id.height_inch__edittext) EditText heighInchEditText;
  @Bind(R.id.phone_no__edittext) EditText phoneNumberEditText;
  @Bind(R.id.bmr_display_text) TextView bmiText;
  @Bind(R.id.bmi_display_text) TextView bmrText;
  @Bind(R.id.gender_radio_group) RadioGroup genderRadioGroup;
  @Bind(R.id.lifestyle_spinner) AppCompatSpinner lifeStyleSpinner;
  private RegularFontTextViewBold title_text;
  private AppCompatImageView back_button;
  private AppCompatImageView done_button;

  private static final String[] lifestyles= {"Sedentary","Lightly Active","Moderatly Active","Very Active"};

  private String name,age,phoneNumber,height,weight,lifestyle,gender;

  private double bmi,bmr;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.user_basic_information);
    ButterKnife.bind(this);
    initViews();
    setUpToolbar();
  }

  private void setUpToolbar() {
    title_text = (RegularFontTextViewBold) findViewById(R.id.title_text);
    back_button = (AppCompatImageView) findViewById(R.id.back_button);
    done_button = (AppCompatImageView) findViewById(R.id.done_button);
    done_button.setOnClickListener(this);
    back_button.setOnClickListener(this);
  }

  private void initViews() {
    heighInchEditText.setText("0");
    gender = "Male";
    genderRadioGroup.check(R.id.male_radio_button);
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
    heighInchEditText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if(s.toString().length()>0) {
          bmiText.setText(calculateBmi());
          bmrText.setText(calculateBmr());
        }
      }
    });

    heightFeetEditText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if(s.toString().length()>0) {
          bmiText.setText(calculateBmi());
          bmrText.setText(calculateBmr());
        }
      }
    });

    weightEditText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if(s.toString().length()>0) {
          bmiText.setText(calculateBmi());
          bmrText.setText(calculateBmr());
        }
      }
    });
    ageEditText.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable s) {
        if(s.toString().length()>0) {
          bmiText.setText(calculateBmi());
          bmrText.setText(calculateBmr());
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

  private String calculateBmr() {

    //BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age(y) + 5         (man)
    //BMR = 10 * weight(kg) + 6.25 * height(cm) - 5 * age(y) - 161     (woman)
    if(heighInchEditText.getText().toString().isEmpty() ||heightFeetEditText.getText().toString().isEmpty()
            ||weightEditText.getText().toString().isEmpty() || ageEditText.getText().toString().isEmpty() )
      return "";
    int heightInch = Integer.parseInt(heighInchEditText.getText().toString());
    int heightFeet = Integer.parseInt(heightFeetEditText.getText().toString());
    int weight = Integer.parseInt(weightEditText.getText().toString());
    int age = Integer.parseInt(ageEditText.getText().toString());
    double cms = calculatCms(heightFeet,heightInch);
    double bmr = 0;
    if(gender.equalsIgnoreCase("Male")){
      bmr = 10*weight + 6.25*cms - 5*age+5;
    }else{
      bmr = 10*weight + 6.25*cms - 5*age -161;
    }
    this.bmr = bmr;
    return String.valueOf(bmr);
  }

  private double calculatCms(int heightFeet, int heightInch) {
    return (heightFeet*12 + heightInch)*2.54;
  }

  private String calculateBmi() {
    if(heighInchEditText.getText().toString().isEmpty() ||heightFeetEditText.getText().toString().isEmpty()
            ||weightEditText.getText().toString().isEmpty() )
      return "";
    int heightInch = Integer.parseInt(heighInchEditText.getText().toString());
    int heightFeet = Integer.parseInt(heightFeetEditText.getText().toString());
    int weight = Integer.parseInt(weightEditText.getText().toString());
    double cms = calculatCms(heightFeet,heightInch);
    double m = cms/100;
    double bmi = (weight/(m*m));
    this.bmi = bmi;
    return String.valueOf(bmr);
  }

  private boolean submitItems() {
    if(!nameEditText.getText().toString().isEmpty()){
      name = nameEditText.getText().toString();
    }else{
      Toast.makeText(this,"Please enter name",Toast.LENGTH_SHORT).show();
      return false;
    }
    if(!phoneNumberEditText.getText().toString().isEmpty() && !phoneNumberEditText.getText().toString().equalsIgnoreCase("0")){
      phoneNumber = phoneNumberEditText.getText().toString();
    }else{
      Toast.makeText(this,"Please enter Phone Number",Toast.LENGTH_SHORT).show();
      return false;
    }
    if(!ageEditText.getText().toString().isEmpty() && !ageEditText.getText().toString().equalsIgnoreCase("0")){
      age = ageEditText.getText().toString();
    }else{
      Toast.makeText(this,"Please enter age",Toast.LENGTH_SHORT).show();
      return false;
    }
    if(!weightEditText.getText().toString().isEmpty()&& !weightEditText.getText().toString().equalsIgnoreCase("0")){
      weight = weightEditText.getText().toString();
    }else{
      Toast.makeText(this,"Please enter name",Toast.LENGTH_SHORT).show();
      return false;
    }
    if(!heightFeetEditText.getText().toString().isEmpty() && !heighInchEditText.getText().toString().isEmpty() && !heightFeetEditText.getText().toString().equalsIgnoreCase("0")&& !heighInchEditText.getText().toString().equalsIgnoreCase("0")){
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
    User user = new User();
    user.setId(1);
    UserPhysic userPhysic = new UserPhysic();
    userPhysic.setAge(Integer.parseInt(age));
    userPhysic.setBmi(String.valueOf(bmi));
    userPhysic.setHeight(String.valueOf(calculatCms(Integer.parseInt(heightFeetEditText.getText().toString()),
            Integer.parseInt(heighInchEditText.getText().toString()))));
    userPhysic.setWeight(String.valueOf(Integer.parseInt(weightEditText.getText().toString())));
    userPhysic.setUserId(1/*(new AppPreferences(this).getUserId())*/);
    user.setName(nameEditText.getText().toString());
    PhoneNumber phoneNumber = new PhoneNumber();
    phoneNumber.setUserId(/*new AppPreferences(this).getUserId())*/1);
    phoneNumber.setNumber(phoneNumberEditText.getText().toString());
    phoneNumber.setType("primary");
    user.setUserPhysic(userPhysic);
    ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
    phoneNumbers.add(phoneNumber);
    user.setPhoneNumbers(phoneNumbers);
    user.setGender(gender);
    user.setLifestyle(lifestyle);

    Intent intent = new Intent(UserInformationActivity.this, FoodPreferenceActivity.class);
    intent.putExtra("user_data", user);
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
        break;
      case R.id.back_button:
        finish();
        break;
    }
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    overridePendingTransition(R.anim.slide_half_left_to_right, R.anim.slide_left_to_right);
  }
}
