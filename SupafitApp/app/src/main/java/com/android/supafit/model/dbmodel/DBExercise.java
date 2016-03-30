package com.android.supafit.model.dbmodel;

/**
 * Created by amitmitra on 16/03/16.
 */
public class DBExercise {

    private long id;
    private String exercise_date;
    private String exercise_start_time;
    private String exercise_end_time;
    private long exercise_id;
    private String exercise_name;
    private String exercise_steps;
    private double expected_calorie_burned;
    private String exercise_status;
    private long done_exercise_id;
    private String done_exercise_name;
    private String done_exercise_steps;
    private double actual_calorie_burned;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExercise_date() {
        return exercise_date;
    }

    public void setExercise_date(String exercise_date) {
        this.exercise_date = exercise_date;
    }

    public String getExercise_start_time() {
        return exercise_start_time;
    }

    public void setExercise_start_time(String exercise_start_time) {
        this.exercise_start_time = exercise_start_time;
    }

    public String getExercise_end_time() {
        return exercise_end_time;
    }

    public void setExercise_end_time(String exercise_end_time) {
        this.exercise_end_time = exercise_end_time;
    }

    public long getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(long exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public String getExercise_steps() {
        return exercise_steps;
    }

    public void setExercise_steps(String exercise_steps) {
        this.exercise_steps = exercise_steps;
    }

    public double getExpected_calorie_burned() {
        return expected_calorie_burned;
    }

    public void setExpected_calorie_burned(double expected_calorie_burned) {
        this.expected_calorie_burned = expected_calorie_burned;
    }

    public String getExercise_status() {
        return exercise_status;
    }

    public void setExercise_status(String exercise_status) {
        this.exercise_status = exercise_status;
    }

    public long getDone_exercise_id() {
        return done_exercise_id;
    }

    public void setDone_exercise_id(long done_exercise_id) {
        this.done_exercise_id = done_exercise_id;
    }

    public String getDone_exercise_name() {
        return done_exercise_name;
    }

    public void setDone_exercise_name(String done_exercise_name) {
        this.done_exercise_name = done_exercise_name;
    }

    public String getDone_exercise_steps() {
        return done_exercise_steps;
    }

    public void setDone_exercise_steps(String done_exercise_steps) {
        this.done_exercise_steps = done_exercise_steps;
    }

    public double getActual_calorie_burned() {
        return actual_calorie_burned;
    }

    public void setActual_calorie_burned(double actual_calorie_burned) {
        this.actual_calorie_burned = actual_calorie_burned;
    }
}
