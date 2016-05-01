package com.android.supafit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.netoperations.networkmodel.food.FoodPreferences;
import com.android.supafit.netoperations.networkmodel.user.User;
import com.android.supafit.ui.RegularFontEditText;
import com.android.supafit.ui.RegularFontTextViewBold;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by harsh on 3/30/16.
 */
public class FoodPreferenceActivity extends AppCompatActivity implements View.OnClickListener {

  @Bind(R.id.veg_radio_button) RadioButton vegRadioButton;
  @Bind(R.id.egg_radio_button) RadioButton eggRadioButton;
  @Bind(R.id.nonveg_radio_button) RadioButton nonvegRadioButton;
  @Bind(R.id.diet_radio_group) RadioGroup dietRadioGroup;
  @Bind(R.id.north) CheckBox north;
  @Bind(R.id.west) CheckBox west;
  @Bind(R.id.east) CheckBox east;
  @Bind(R.id.chinese) CheckBox chinese;
  @Bind(R.id.south) CheckBox south;
  @Bind(R.id.continental) CheckBox continental;
  @Bind(R.id.allergy_edittext) RegularFontEditText allergyEdittext;

  private RegularFontTextViewBold title_text;
  private AppCompatImageView back_button;
  private AppCompatImageView done_button;

  String cusine;
  String dietPreference;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.food_preferences_layout);
    ButterKnife.bind(this);
    initViews();
    setUpFoodPrefToolbar();
  }

  private void setUpFoodPrefToolbar() {
    title_text = (RegularFontTextViewBold) findViewById(R.id.title_text);
    back_button = (AppCompatImageView) findViewById(R.id.back_button);
    done_button = (AppCompatImageView) findViewById(R.id.done_button);
    done_button.setOnClickListener(this);
    back_button.setOnClickListener(this);
  }

  private void initViews() {
    dietRadioGroup.check(R.id.veg_radio_button);
    dietPreference = "Vegetarian";
    dietRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
          case R.id.veg_radio_button: dietPreference = "Vegetarian"; break;
          case R.id.egg_radio_button: dietPreference = "Eggetarian"; break;
          case R.id.nonveg_radio_button: dietPreference = "Non-Vegetarian"; break;
        }
      }
    });
  }



  private boolean submitItems() {
    User user = (User)getIntent().getSerializableExtra("user_data");
    ArrayList<FoodPreferences> foodArrayList = new ArrayList<>();
    if(north.isChecked()){
      FoodPreferences food = new FoodPreferences();
      food.setCuisine("North India");
      food.setDescription("North Indian style food");
      food.setId(1);
      foodArrayList.add(food);
    }
    if(west.isChecked()){
      FoodPreferences food = new FoodPreferences();
      food.setId(1);
      food.setCuisine("West India");
      food.setDescription("West Indian style food");
      foodArrayList.add(food);
    }
    if(east.isChecked()){
      FoodPreferences food = new FoodPreferences();
      food.setId(1);
      food.setCuisine("East India");
      food.setDescription("East Indian style food");
      foodArrayList.add(food);
    }
    if(south.isChecked()){
      FoodPreferences food = new FoodPreferences();
      food.setId(3);
      food.setCuisine("South India");
      food.setDescription("South Indian style food");
      foodArrayList.add(food);
    }
    if(continental.isChecked()){
      FoodPreferences food = new FoodPreferences();
      food.setId(1);
      food.setCuisine("Continental");
      food.setDescription("Europe dishes");
      foodArrayList.add(food);
    }
    if(chinese.isChecked()){
      FoodPreferences food = new FoodPreferences();
      food.setId(1);
      food.setCuisine("Chinese");
      food.setDescription("Chinese dishes");
      foodArrayList.add(food);
    }
    user.setFoodPreferences(foodArrayList);
    Intent intent = new Intent(FoodPreferenceActivity.this, MedicalConditionActivity.class);
    intent.putExtra("user_data", user);
    startActivity(intent);
    overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
    /*NetworkHandler signInHandler = new NetworkHandler() {
      @Override
      public void success(Object response) {
        Intent intent = new Intent(FoodPreferenceActivity.this, MedicalConditionActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_right_to_left, R.anim.slide_right_to_half_left);
      }

      @Override
      public void failure(Exception e) {
        Toast.makeText(FoodPreferenceActivity.this,"network error",Toast.LENGTH_SHORT).show();
      }
    };

    try {
      VolleyRequest.updateFoodInformation(FoodPreferenceActivity.this,signInHandler,foodArrayList);
    } catch (JSONException e) {
      e.printStackTrace();
    }*/
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
