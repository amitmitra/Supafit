package com.android.supafit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.model.networkmodel.Food;
import com.android.supafit.netoperations.VolleyRequest;
import com.android.supafit.netoperations.handler.NetworkHandler;
import com.android.supafit.ui.RegularFontEditText;
import com.android.supafit.ui.planslist.PlanListActivity;

import org.json.JSONException;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by harsh on 3/30/16.
 */
public class FoodPreferenceActivity extends AppCompatActivity {

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

  String cusine;
  String dietPreference;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.food_preferences_layout);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    ButterKnife.bind(this);
    initViews();
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
    ArrayList<Food> foodArrayList = new ArrayList<>();
    if(north.isChecked()){
      Food food = new Food();
      food.setId(1);
      food.setCuisine("North India");
      food.setDescription("North Indian style food");
      foodArrayList.add(food);
    }
    if(west.isChecked()){
      Food food = new Food();
      food.setId(1);
      food.setCuisine("West India");
      food.setDescription("West Indian style food");
      foodArrayList.add(food);
    }
    if(east.isChecked()){
      Food food = new Food();
      food.setId(1);
      food.setCuisine("East India");
      food.setDescription("East Indian style food");
      foodArrayList.add(food);
    }
    if(south.isChecked()){
      Food food = new Food();
      food.setId(3);
      food.setCuisine("South India");
      food.setDescription("South Indian style food");
      foodArrayList.add(food);
    }
    if(continental.isChecked()){
      Food food = new Food();
      food.setId(1);
      food.setCuisine("Continental");
      food.setDescription("Europe dishes");
      foodArrayList.add(food);
    }
    if(chinese.isChecked()){
      Food food = new Food();
      food.setId(1);
      food.setCuisine("Chinese");
      food.setDescription("Chinese dishes");
      foodArrayList.add(food);
    }
    NetworkHandler signInHandler = new NetworkHandler() {
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
    }
    return true;
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    ButterKnife.unbind(this);
  }
}
