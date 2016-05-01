package com.android.supafit.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.supafit.database.dbmodel.DBUser;

/**
 * Created by amitmitra on 15/03/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SupafitDB.db";

    /* 1. basic_info table | start*/
    private static final String basic_info_table = "basic_info";
    private static final String column_basic_info_id = "id";
    private static final String column_name = "name";
    private static final String column_joining_date = "joining_date";
    private static final String column_phone_number = "phone_number";
    private static final String column_gender = "gender";
    private static final String column_age = "age";
    private static final String column_height = "height";
    private static final String column_weight = "weight";
    private static final String column_lifestyle = "lifestyle";
    private static final String column_bmi = "bmi";
    private static final String column_bmi_status = "bmi_status";
    private static final String column_bmr = "bmr";
    private static final String column_diet_preference = "diet_preference";
    private static final String column_current_plan_id = "current_plan_id";
    private static final String column_current_plan_name = "current_plan_name";
    private static final String column_allergies = "allergies";
    private static final String column_cuisines = "cuisines";
    private static final String column_medical_conditions = "medical_conditions";
    private static final String column_goals = "goals";
    private static final String column_points_earned = "points_earned";
    private static final String column_points_spent = "points_spent";
    private static final String column_referral_code = "referral_code";
    private static final String column_avg_water = "avg_water";
    private static final String column_avg_sleep = "avg_sleep";
    private static final String column_avg_step = "avg_step";
    private static final String column_avg_calorie_burned = "avg_calorie_burned";
    private static final String column_avg_calorie_consumed = "avg_calorie_consumed";

    private static final String create_basic_info_table = "create table " + basic_info_table + "("
            + column_basic_info_id + " integer, "
            + column_name + " text, "
            + column_joining_date + " text, "
            + column_phone_number + " text, "
            + column_gender + " text, "
            + column_age + " text, "
            + column_height + " text, "
            + column_weight + " text, "
            + column_lifestyle + " text, "
            + column_bmi + " real, "
            + column_bmi_status + " text, "
            + column_bmr + " real, "
            + column_diet_preference + " text, "
            + column_current_plan_id + " integer, "
            + column_current_plan_name + " text, "
            + column_allergies + " text, "
            + column_cuisines + " text, "
            + column_medical_conditions + " text, "
            + column_goals + " text, "
            + column_points_earned + " real, "
            + column_points_spent + " real, "
            + column_referral_code + " text, "
            + column_avg_water + " real, "
            + column_avg_sleep + " real, "
            + column_avg_step + " real, "
            + column_avg_calorie_burned + " real, "
            + column_avg_calorie_consumed + " real)";

    public long addNewUser(DBUser user){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(column_joining_date, user.getJoining_date());
        values.put(column_name, user.getName());
        values.put(column_phone_number, user.getPhone_number());
        long id = database.insert(basic_info_table, null, values);
        database.close();
        return id;
    }
    /* basic_info table | end*/

    /* 2. programs table | start*/
    private static final String programs_table = "programs";
    private static final String column_program_table_id = "id";
    private static final String column_program_id = "program_id";
    private static final String column_program_name = "program_name";
    private static final String column_program_start_date = "start_date";
    private static final String column_program_end_date = "end_date";
    private static final String column_program_status = "status";

    private static final String create_programs_table = " create table " + programs_table + "("
            + column_program_table_id + " integer, "
            + column_program_id + " integer, "
            + column_program_name + " text, "
            + column_program_start_date + " text, "
            + column_program_end_date + " text, "
            + column_program_status + " text)";
    /* programs table | end*/

    /* 3. my_assistant table | start*/
    private static final String my_assistant_table = "my_assistant";
    private static final String column_assistant_id = "assistant_id";
    private static final String column_assistant_name = "name";
    private static final String column_assistant_type = "type";
    private static final String column_assistant_start_date = "assistant_start_date";
    private static final String column_assistant_end_date = "assistant_end_date";
    private static final String column_assistant_status = "assistant_status";

    private static final String create_my_assistant_table = " create table " + my_assistant_table + "("
            + column_assistant_id + " integer, "
            + column_assistant_name + " text, "
            + column_assistant_type + " text, "
            + column_assistant_start_date + " text, "
            + column_assistant_end_date + " text, "
            + column_assistant_status + " text)";
    /* my_assistant table | end*/

    /* 4. meal_task table | start*/
    private static final String meal_task_table = "meal_task";
    private static final String column_meal_task__id = "id";
    private static final String column_meal_date = "meal_date";
    private static final String column_meal_start_time = "meal_start_time";
    private static final String column_meal_end_time = "meal_end_time";
    private static final String column_meal_id = "meal_id";
    private static final String column_meal_name = "meal_name";
    private static final String column_meal_items = "meal_items";
    private static final String column_expected_calorie_consumed = "expected_calorie_consumed";
    private static final String column_meal_status = "meal_status";
    private static final String column_done_meal_id = "done_meal_id";
    private static final String column_done_meal_name = "done_meal_name";
    private static final String column_done_meal_items = "done_meal_items";
    private static final String column_actual_calorie_consumed = "actual_calorie_consumed";

    private static final String create_meal_task_table = " create table " + meal_task_table + "("
            + column_meal_task__id + " integer, "
            + column_meal_date + " text, "
            + column_meal_start_time + " text, "
            + column_meal_end_time + " text, "
            + column_meal_id + " integer, "
            + column_meal_name + " text, "
            + column_meal_items + " text, "
            + column_expected_calorie_consumed + " real, "
            + column_meal_status + " text, "
            + column_done_meal_id + " integer, "
            + column_done_meal_name + " text, "
            + column_done_meal_items + " text, "
            + column_actual_calorie_consumed + " real)";
    /* meal_task table | end*/

    /* 5. exercise_task table | start*/
    private static final String exercise_task_table = "exercise_task";
    private static final String column_exercise_task_id = "id";
    private static final String column_exercise_date = "exercise_date";
    private static final String column_exercise_start_time = "exercise_start_time";
    private static final String column_exercise_end_time = "exercise_end_time";
    private static final String column_exercise_id = "exercise_id";
    private static final String column_exercise_name = "exercise_name";
    private static final String column_exercise_steps = "exercise_steps";
    private static final String column_expected_calorie_burned = "expected_calorie_burned";
    private static final String column_exercise_status = "exercise_status";
    private static final String column_done_exercise_id = "done_exercise_id";
    private static final String column_done_exercise_name = "done_exercise_name";
    private static final String column_done_exercise_steps = "done_exercise_steps";
    private static final String column_actual_calorie_burned = "actual_calorie_burned";

    private static final String create_exercise_task_table = " create table " + exercise_task_table + "("
            + column_exercise_task_id + " integer, "
            + column_exercise_date + " text, "
            + column_exercise_start_time + " text, "
            + column_exercise_end_time + " text, "
            + column_exercise_id + " integer, "
            + column_exercise_name + " text, "
            + column_exercise_steps + " text, "
            + column_expected_calorie_burned + " real, "
            + column_exercise_status + " text, "
            + column_done_exercise_id + " integer, "
            + column_done_exercise_name + " text, "
            + column_done_exercise_steps + " text, "
            + column_actual_calorie_burned + " real)";
    /* exercise_task table | end*/

    /* 6. steps_walked table | start*/
    private static final String steps_walked_table = "steps_walked";
    private static final String column_steps_id = "id";
    private static final String column_steps_date = "steps_date";
    private static final String column_steps_time = "steps_time";
    private static final String column_step_count = "steps_count";

    private static final String create_steps_walked_table = " create table " + steps_walked_table + "("
            + column_steps_id + " integer primary key autoincrement, "
            + column_steps_date + " text, "
            + column_steps_time + " text, "
            + column_step_count + " integer)";
    /* steps_walked table | end*/

    /* 7. water_consumed table | start*/
    private static final String water_consumed_table = "water_consumed";
    private static final String column_water_id = "id";
    private static final String column_water_consume_date = "water_consume_date";
    private static final String column_water_consume_time = "water_consume_time";
    private static final String column_water_consumed = "water_consumed";

    private static final String create_water_consumed_table = " create table " + water_consumed_table + "("
            + column_water_id + " integer primary key autoincrement, "
            + column_water_consume_date + " text, "
            + column_water_consume_time + " text, "
            + column_water_consumed + " real)";
    /* water_consumed table | end*/

    /* 8. sleep_consumed table | start*/
    private static final String sleep_consumed_table = "sleep_consumed";
    private static final String column_sleep_id = "id";
    private static final String column_start_date_time = "start_date_time";
    private static final String column_end_date_time = "end_date_time";

    private static final String create_sleep_consumed_table = " create table " + sleep_consumed_table + "("
            + column_sleep_id + " integer primary key autoincrement, "
            + column_start_date_time + " text, "
            + column_end_date_time + " text)";
    /* sleep_consumed table | end*/

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_basic_info_table);
        db.execSQL(create_programs_table);
        db.execSQL(create_my_assistant_table);
        db.execSQL(create_meal_task_table);
        db.execSQL(create_exercise_task_table);
        db.execSQL(create_steps_walked_table);
        db.execSQL(create_water_consumed_table);
        db.execSQL(create_sleep_consumed_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + basic_info_table);
        db.execSQL("drop table if exists " + programs_table);
        db.execSQL("drop table if exists " + my_assistant_table);
        db.execSQL("drop table if exists " + meal_task_table);
        db.execSQL("drop table if exists " + exercise_task_table);
        db.execSQL("drop table if exists " + steps_walked_table);
        db.execSQL("drop table if exists " + water_consumed_table);
        db.execSQL("drop table if exists " + sleep_consumed_table);

        onCreate(db);
    }
}
