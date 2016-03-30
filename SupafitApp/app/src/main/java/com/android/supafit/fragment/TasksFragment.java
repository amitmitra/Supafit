package com.android.supafit.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.supafit.R;
import com.android.supafit.model.dbmodel.DBExercise;
import com.android.supafit.model.dbmodel.DBMeal;
import com.android.supafit.ui.ExpandAnimation;
import com.android.supafit.utils.DummyData;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by harsh on 2/23/16.
 */
public class TasksFragment extends Fragment implements View.OnClickListener{

    ListView mTasksList;
    List<Object> mTaskObjectList;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    View view = inflater.inflate(R.layout.task_fragment_layout,null);

      mTaskObjectList = DummyData.getTasks();

      mTasksList = (ListView)view.findViewById(R.id.task_list);
      mTasksList.setAdapter(new TaskAdapter(getActivity(), mTaskObjectList));
      mTasksList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
              View toolbar = view.findViewById(R.id.data_layout);
              ExpandAnimation expandAni = new ExpandAnimation(toolbar, 1000);
              toolbar.startAnimation(expandAni);
              view.findViewById(R.id.top_button_click_icon).setVisibility(View.GONE);
              view.findViewById(R.id.bottom_button_click_icon).setVisibility(View.VISIBLE);
          }
      });
    return view;
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

        if(mTaskList.get(position) instanceof DBMeal){
            DBMeal meal = (DBMeal)mTaskList.get(position);
            titleText.setText(meal.getMeal_name());
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

        return convertView;
    }
}
