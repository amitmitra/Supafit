package com.android.supafit.utils;

import com.android.supafit.model.dbmodel.DBExercise;
import com.android.supafit.model.dbmodel.DBMeal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by amitmitra on 16/03/16.
 */
public class DummyData {

    public static List<Object> getTasks(){
        List<Object> taskList = new ArrayList<Object>();

        DBMeal meal = new DBMeal();
        meal.setId(0);
        meal.setMeal_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        meal.setMeal_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal.setMeal_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal.setMeal_id(0);
        meal.setMeal_name("Pre-Breakfast");
        meal.setMeal_items("1 Bowl Sprouts, lots of water");
        meal.setExpected_calorie_consumed(60.5);
        meal.setMeal_status("Done");
        meal.setDone_meal_id(0);
        meal.setDone_meal_name("Pre-Breakfast");
        meal.setDone_meal_items("1 Bowl Sprouts and lots of water");
        meal.setActual_calorie_consumed(60.5);
        taskList.add(meal);

        DBExercise exercise = new DBExercise();
        exercise.setId(0);
        exercise.setExercise_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        exercise.setExercise_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        exercise.setExercise_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        exercise.setExercise_id(0);
        exercise.setExercise_name("Running");
        exercise.setExercise_steps("Run for half hour");
        exercise.setExpected_calorie_burned(120.5);
        exercise.setExercise_status("Done");
        exercise.setDone_exercise_id(0);
        exercise.setDone_exercise_name("Running");
        exercise.setDone_exercise_steps("Run for half hour");
        exercise.setActual_calorie_burned(120.5);
        taskList.add(exercise);

        DBMeal meal1 = new DBMeal();
        meal1.setId(1);
        meal1.setMeal_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        meal1.setMeal_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal1.setMeal_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal1.setMeal_id(1);
        meal1.setMeal_name("Breakfast");
        meal1.setMeal_items("Avacado Toast with Eggs, Peanut Butter Banana Smoothie");
        meal1.setExpected_calorie_consumed(100.5);
        meal1.setMeal_status("Done");
        meal1.setDone_meal_id(1);
        meal1.setDone_meal_name("Breakfast");
        meal1.setDone_meal_items("Avacado Toast with Eggs, Peanut Butter Banana Smoothie");
        meal1.setActual_calorie_consumed(100.5);
        taskList.add(meal1);

        DBMeal meal2 = new DBMeal();
        meal2.setId(2);
        meal2.setMeal_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        meal2.setMeal_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal2.setMeal_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal2.setMeal_id(2);
        meal2.setMeal_name("Lunch");
        meal2.setMeal_items("1 Bowl Paneer Curry, 1 Bowl Arhar Daal, 4 Chapatis, 1 Bowl Rice, Salad");
        meal2.setExpected_calorie_consumed(400);
        meal2.setMeal_status("Skipped");
        taskList.add(meal2);

        DBMeal meal3 = new DBMeal();
        meal3.setId(3);
        meal3.setMeal_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        meal3.setMeal_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal3.setMeal_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal3.setMeal_id(0);
        meal3.setMeal_name("Evening Snack");
        meal3.setMeal_items("1 Bowl Sprouts, lots of water");
        meal3.setExpected_calorie_consumed(60.5);
        meal3.setMeal_status("Done");
        meal3.setDone_meal_id(0);
        meal3.setDone_meal_name("Pre-Breakfast");
        meal3.setDone_meal_items("1 Bowl Sprouts and lots of water");
        meal3.setActual_calorie_consumed(60.5);
        taskList.add(meal3);

        DBExercise exercise2 = new DBExercise();
        exercise2.setId(1);
        exercise2.setExercise_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        exercise2.setExercise_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        exercise2.setExercise_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        exercise2.setExercise_id(1);
        exercise2.setExercise_name("Workout");
        exercise2.setExercise_steps("Hands and Legs Stretching, Pushups for 30 mins");
        exercise2.setExpected_calorie_burned(200);
        exercise2.setExercise_status("Altered");
        exercise2.setDone_exercise_id(0);
        exercise2.setDone_exercise_name("Running");
        exercise2.setDone_exercise_steps("Run for half hour");
        exercise2.setActual_calorie_burned(120.5);
        taskList.add(exercise);

        DBMeal meal4 = new DBMeal();
        meal4.setId(4);
        meal4.setMeal_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        meal4.setMeal_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal4.setMeal_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal4.setMeal_id(0);
        meal4.setMeal_name("PreDinner");
        meal4.setMeal_items("1 Bowl Sprouts, lots of water");
        meal4.setExpected_calorie_consumed(60.5);
        meal4.setMeal_status("Done");
        meal4.setDone_meal_id(0);
        meal4.setDone_meal_name("Pre-Breakfast");
        meal4.setDone_meal_items("1 Bowl Sprouts and lots of water");
        meal4.setActual_calorie_consumed(60.5);
        taskList.add(meal4);

        DBMeal meal5 = new DBMeal();
        meal5.setId(5);
        meal5.setMeal_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        meal5.setMeal_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal5.setMeal_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal5.setMeal_id(0);
        meal5.setMeal_name("Dinner");
        meal5.setMeal_items("1 Bowl Sprouts, lots of water");
        meal5.setExpected_calorie_consumed(60.5);
        meal5.setMeal_status("Done");
        meal5.setDone_meal_id(0);
        meal5.setDone_meal_name("Pre-Breakfast");
        meal5.setDone_meal_items("1 Bowl Sprouts and lots of water");
        meal5.setActual_calorie_consumed(60.5);
        taskList.add(meal5);

        return taskList;
    }

    public static List<DBMeal> getMeals(){
        List<DBMeal> mealsList = new ArrayList<DBMeal>();

        DBMeal meal = new DBMeal();
        meal.setId(0);
        meal.setMeal_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        meal.setMeal_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal.setMeal_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal.setMeal_id(0);
        meal.setMeal_name("Pre-Breakfast");
        meal.setMeal_items("1 Bowl Sprouts, lots of water");
        meal.setExpected_calorie_consumed(60.5);
        meal.setMeal_status("Done");
        meal.setDone_meal_id(0);
        meal.setDone_meal_name("Pre-Breakfast");
        meal.setDone_meal_items("1 Bowl Sprouts and lots of water");
        meal.setActual_calorie_consumed(60.5);
        mealsList.add(meal);

        DBMeal meal1 = new DBMeal();
        meal1.setId(1);
        meal1.setMeal_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        meal1.setMeal_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal1.setMeal_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal1.setMeal_id(1);
        meal1.setMeal_name("Breakfast");
        meal1.setMeal_items("Avacado Toast with Eggs, Peanut Butter Banana Smoothie");
        meal1.setExpected_calorie_consumed(100.5);
        meal1.setMeal_status("Done");
        meal1.setDone_meal_id(1);
        meal1.setDone_meal_name("Breakfast");
        meal1.setDone_meal_items("Avacado Toast with Eggs, Peanut Butter Banana Smoothie");
        meal1.setActual_calorie_consumed(100.5);
        mealsList.add(meal1);

        DBMeal meal2 = new DBMeal();
        meal2.setId(2);
        meal2.setMeal_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        meal2.setMeal_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal2.setMeal_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal2.setMeal_id(2);
        meal2.setMeal_name("Lunch");
        meal2.setMeal_items("1 Bowl Paneer Curry, 1 Bowl Arhar Daal, 4 Chapatis, 1 Bowl Rice, Salad");
        meal2.setExpected_calorie_consumed(400);
        meal2.setMeal_status("Skipped");
        mealsList.add(meal2);

        DBMeal meal3 = new DBMeal();
        meal3.setId(3);
        meal3.setMeal_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        meal3.setMeal_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal3.setMeal_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal3.setMeal_id(0);
        meal3.setMeal_name("Evening Snack");
        meal3.setMeal_items("1 Bowl Sprouts, lots of water");
        meal3.setExpected_calorie_consumed(60.5);
        meal3.setMeal_status("Done");
        meal3.setDone_meal_id(0);
        meal3.setDone_meal_name("Pre-Breakfast");
        meal3.setDone_meal_items("1 Bowl Sprouts and lots of water");
        meal3.setActual_calorie_consumed(60.5);
        mealsList.add(meal3);

        DBMeal meal4 = new DBMeal();
        meal4.setId(4);
        meal4.setMeal_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        meal4.setMeal_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal4.setMeal_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal4.setMeal_id(0);
        meal4.setMeal_name("PreDinner");
        meal4.setMeal_items("1 Bowl Sprouts, lots of water");
        meal4.setExpected_calorie_consumed(60.5);
        meal4.setMeal_status("Done");
        meal4.setDone_meal_id(0);
        meal4.setDone_meal_name("Pre-Breakfast");
        meal4.setDone_meal_items("1 Bowl Sprouts and lots of water");
        meal4.setActual_calorie_consumed(60.5);
        mealsList.add(meal4);

        DBMeal meal5 = new DBMeal();
        meal5.setId(5);
        meal5.setMeal_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        meal5.setMeal_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal5.setMeal_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        meal5.setMeal_id(0);
        meal5.setMeal_name("Dinner");
        meal5.setMeal_items("1 Bowl Sprouts, lots of water");
        meal5.setExpected_calorie_consumed(60.5);
        meal5.setMeal_status("Done");
        meal5.setDone_meal_id(0);
        meal5.setDone_meal_name("Pre-Breakfast");
        meal5.setDone_meal_items("1 Bowl Sprouts and lots of water");
        meal5.setActual_calorie_consumed(60.5);
        mealsList.add(meal5);

        return mealsList;
    }


    public static List<DBExercise> getExercises(){
        List<DBExercise> exerciseList = new ArrayList<DBExercise>();

        DBExercise exercise = new DBExercise();
        exercise.setId(0);
        exercise.setExercise_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        exercise.setExercise_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        exercise.setExercise_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        exercise.setExercise_id(0);
        exercise.setExercise_name("Running");
        exercise.setExercise_steps("Run for half hour");
        exercise.setExpected_calorie_burned(120.5);
        exercise.setExercise_status("Done");
        exercise.setDone_exercise_id(0);
        exercise.setDone_exercise_name("Running");
        exercise.setDone_exercise_steps("Run for half hour");
        exercise.setActual_calorie_burned(120.5);
        exerciseList.add(exercise);

        DBExercise exercise2 = new DBExercise();
        exercise2.setId(1);
        exercise2.setExercise_date(AppUtility.formatDate(Calendar.getInstance().getTime()));
        exercise2.setExercise_start_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        exercise2.setExercise_end_time(AppUtility.formatTime(Calendar.getInstance().getTime()));
        exercise2.setExercise_id(1);
        exercise2.setExercise_name("Workout");
        exercise2.setExercise_steps("Hands and Legs Stretching, Pushups for 30 mins");
        exercise2.setExpected_calorie_burned(200);
        exercise2.setExercise_status("Altered");
        exercise2.setDone_exercise_id(0);
        exercise2.setDone_exercise_name("Running");
        exercise2.setDone_exercise_steps("Run for half hour");
        exercise2.setActual_calorie_burned(120.5);
        exerciseList.add(exercise);


        return exerciseList;
    }
}
