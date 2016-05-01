package com.android.supafit.database.dbmodel;

/**
 * Created by amitmitra on 16/03/16.
 */
public class DBMeal {

    private long id;
    private String meal_date;
    private String meal_start_time;
    private String meal_end_time;
    private long meal_id;
    private String meal_name;
    private String meal_items;
    private double expected_calorie_consumed;
    private String meal_status;
    private long done_meal_id;
    private String done_meal_name;
    private String done_meal_items;
    private double actual_calorie_consumed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMeal_date() {
        return meal_date;
    }

    public void setMeal_date(String meal_date) {
        this.meal_date = meal_date;
    }

    public String getMeal_start_time() {
        return meal_start_time;
    }

    public void setMeal_start_time(String meal_start_time) {
        this.meal_start_time = meal_start_time;
    }

    public String getMeal_end_time() {
        return meal_end_time;
    }

    public void setMeal_end_time(String meal_end_time) {
        this.meal_end_time = meal_end_time;
    }

    public long getMeal_id() {
        return meal_id;
    }

    public void setMeal_id(long meal_id) {
        this.meal_id = meal_id;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public void setMeal_name(String meal_name) {
        this.meal_name = meal_name;
    }

    public String getMeal_items() {
        return meal_items;
    }

    public void setMeal_items(String meal_items) {
        this.meal_items = meal_items;
    }

    public double getExpected_calorie_consumed() {
        return expected_calorie_consumed;
    }

    public void setExpected_calorie_consumed(double expected_calorie_consumed) {
        this.expected_calorie_consumed = expected_calorie_consumed;
    }

    public String getMeal_status() {
        return meal_status;
    }

    public void setMeal_status(String meal_status) {
        this.meal_status = meal_status;
    }

    public long getDone_meal_id() {
        return done_meal_id;
    }

    public void setDone_meal_id(long done_meal_id) {
        this.done_meal_id = done_meal_id;
    }

    public String getDone_meal_name() {
        return done_meal_name;
    }

    public void setDone_meal_name(String done_meal_name) {
        this.done_meal_name = done_meal_name;
    }

    public String getDone_meal_items() {
        return done_meal_items;
    }

    public void setDone_meal_items(String done_meal_items) {
        this.done_meal_items = done_meal_items;
    }

    public double getActual_calorie_consumed() {
        return actual_calorie_consumed;
    }

    public void setActual_calorie_consumed(double actual_calorie_consumed) {
        this.actual_calorie_consumed = actual_calorie_consumed;
    }
}
