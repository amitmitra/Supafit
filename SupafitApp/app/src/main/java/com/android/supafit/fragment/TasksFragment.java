package com.android.supafit.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.supafit.R;
import com.android.supafit.database.dbmodel.DBExercise;
import com.android.supafit.database.dbmodel.DBMeal;
import com.android.supafit.netoperations.VolleyRequest;
import com.android.supafit.netoperations.handler.NetworkHandler;
import com.android.supafit.netoperations.networkmodel.meal.UserMeals;
import com.android.supafit.netoperations.networkmodel.plan.MealPlanDetail;
import com.android.supafit.netoperations.networkmodel.plan.MealPlans;
import com.android.supafit.ui.ExpandAnimation;
import com.android.supafit.ui.RegularFontEditText;
import com.android.supafit.ui.RegularFontTextView;
import com.android.supafit.ui.RegularFontTextViewBold;
import com.android.supafit.utils.DummyData;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by harsh on 2/23/16.
 */
public class TasksFragment extends Fragment implements View.OnClickListener{

    ListView mTasksList;
    List<Object> mTaskObjectList;
    List<MealPlanDetail> mealPlanDetail = new ArrayList<MealPlanDetail>();
    TextView no_item_text;

    @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    View view = inflater.inflate(R.layout.task_fragment_layout,null);

        no_item_text = (TextView)view.findViewById(R.id.no_item_text);

      mTaskObjectList = new ArrayList<Object>();//DummyData.getTasks();
      getMealPlanDatafromServer();

      mTasksList = (ListView)view.findViewById(R.id.task_list);

      mTasksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
              View toolbar = view.findViewById(R.id.data_layout);
              ExpandAnimation expandAni = new ExpandAnimation(toolbar, 1000);
              toolbar.startAnimation(expandAni);
              view.findViewById(R.id.top_button_click_icon).setVisibility(View.GONE);
              view.findViewById(R.id.bottom_button_click_icon).setVisibility(View.VISIBLE);
          }
      });
      getMealPlanDatafromServer();
    return view;
  }

    private void getMealPlanDatafromServer() {
            NetworkHandler handler = new NetworkHandler() {
                @Override
                public void success(Object response) {
                  /*  List<MealPlans> meals = (List<MealPlans>)response;

                    if(mealPlanDetail.size() != 0 && !mealPlanDetail.isEmpty()){
                        no_item_text.setVisibility(View.GONE);
                        mTasksList.setVisibility(View.VISIBLE);
                    List<MealPlanDetail> details = meals.get(0).getMealPlanDetails();

                        for (MealPlanDetail meal : details){
                            DBMeal dbMeal = new DBMeal();
                            dbMeal.setMeal_name(meal.getMeal().getName());
                            String timings = meal.getMealTiming().getTimings();
                            String[] splitter = timings.split("to");
                            String start_time = splitter[0];
                            String end_time = splitter[1];
                            dbMeal.setMeal_start_time(start_time);
                            dbMeal.setMeal_end_time(end_time);

                            List<UserMeals> uMeals =  meal.getMeal().getMeals();
                            String s = "";
                            double meal_calories = 0;
                            for (UserMeals m : uMeals){
                                s = s + m.getItem() + ",";
                                if(m.getCalories()!= null) {
                                    meal_calories = meal_calories + Double.parseDouble(m.getCalories());
                                }
                            }
                            s = s.substring(0, s.length()-1);
                            meal.getMealPlanStatus();
                            dbMeal.setMeal_items(s);
                            dbMeal.setExpected_calorie_consumed(meal_calories);

                            mTaskObjectList.add(dbMeal);
                        }
                    } else {
                        no_item_text.setVisibility(View.VISIBLE);
                        mTasksList.setVisibility(View.GONE);
                    }*/
                    mTaskObjectList = DummyData.getTasks();
                    mTasksList.setAdapter(new TaskAdapter(getActivity(), mTaskObjectList));
                }

                @Override
                public void failure(Exception e) {
                    Toast.makeText(getActivity(),"Unable to fetch",Toast.LENGTH_SHORT).show();
                }
            };
            try {
                VolleyRequest.getPlanDetailsforMeal(handler,1,"2016-03-10","2016-03-10");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    @Override
    public void onClick(View v) {
        
    }
}


class TaskAdapter extends BaseAdapter{

    List<Object> mTaskList = new ArrayList<Object>();
    Context c;

    public  TaskAdapter(Context c, List<Object> mTaskList){
        this.c = c;
        this.mTaskList = mTaskList;
    }

    @Override
    public int getCount() {
        return mTaskList.size();
    }

    @Override
    public Object getItem(int position) {
        return mTaskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(c).inflate(R.layout.task_list_item, null);

        TextView titleText = (TextView) convertView.findViewById(R.id.title);
        final View toolbar = convertView.findViewById(R.id.data_layout);
        final ImageView topButton = (ImageView) convertView.findViewById(R.id.top_button_click_icon);
        final ImageView bottomButton = (ImageView) convertView.findViewById(R.id.bottom_button_click_icon);
        final AppCompatButton yes_button = (AppCompatButton) convertView.findViewById(R.id.yes_button);
        final AppCompatButton no_button = (AppCompatButton) convertView.findViewById(R.id.no_button);
        final RegularFontTextView job_done_text = (RegularFontTextView) convertView.findViewById(R.id.job_done_text);
        final RegularFontEditText add_comment_box = (RegularFontEditText) convertView.findViewById(R.id.add_comment_box);
        RegularFontTextView meal_start_time = (RegularFontTextView) convertView.findViewById(R.id.meal_start_time);
        LinearLayout item_list = (LinearLayout) convertView.findViewById(R.id.item_list);
        RegularFontTextViewBold calorie_count_text = (RegularFontTextViewBold) convertView.findViewById(R.id.calorie_count_text);

        if(mTaskList.get(position) instanceof DBMeal){
            DBMeal meal = (DBMeal)mTaskList.get(position);
            titleText.setText(meal.getMeal_name());
            meal_start_time.setText(meal.getMeal_start_time());

            String timings = meal.getMeal_items();
            String[] splitter = timings.split(",");

            for(int i = 0 ; i<splitter.length ; i++){
                RegularFontTextViewBold meal_items = new RegularFontTextViewBold(c);
                meal_items.setText("- " +splitter[i]);
                meal_items.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                meal_items.setTextColor(Color.BLACK);
                item_list.addView(meal_items);
            }
            calorie_count_text.setText(meal.getExpected_calorie_consumed()+" Kcal");
        } else if(mTaskList.get(position) instanceof DBExercise){
            DBExercise exercise = (DBExercise) mTaskList.get(position);
            titleText.setText(exercise.getExercise_name());
        }



        bottomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LinearLayout.LayoutParams) toolbar.getLayoutParams()).bottomMargin = -500;
                toolbar.setVisibility(View.GONE);
                topButton.setVisibility(View.VISIBLE);
                bottomButton.setVisibility(View.GONE);
            }
        });
        yes_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes_button.setBackgroundColor(R.color.color_light_grey);
                no_button.setBackgroundColor(android.R.color.transparent);
                job_done_text.setVisibility(View.VISIBLE);
                add_comment_box.setVisibility(View.GONE);
            }
        });

        no_button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                no_button.setBackgroundColor(R.color.color_light_grey);
                yes_button.setBackgroundColor(android.R.color.transparent);
                job_done_text.setVisibility(View.GONE);
                add_comment_box.setVisibility(View.VISIBLE);
                add_comment_box.requestFocus();
                return false;
            }
        });

        return convertView;
    }


}
