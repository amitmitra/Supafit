package com.android.supafit.netoperations.networkmodel.plan;

import com.android.supafit.netoperations.networkmodel.exercise.Timing;
import com.android.supafit.netoperations.networkmodel.workout.Workout;

import java.io.Serializable;
import java.util.List;

public class WorkoutPlanDetail implements Serializable{


	private long id;
	private String planDate;
	private WorkoutPlans workoutPlans;
	private Workout workout;
	private Timing workoutTiming;
	private String specialInstructions;
	private String trainerRemarks;
	private List<WorkoutPlanStatusDetail> workoutPlanStatus;
	private Integer planStatus;
	private String userRemarks;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPlanDate() {
		return planDate;
	}
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	public WorkoutPlans getWorkoutPlans() {
		return workoutPlans;
	}
	public void setWorkoutPlans(WorkoutPlans workoutPlans) {
		this.workoutPlans = workoutPlans;
	}
	public Workout getWorkout() {
		return workout;
	}
	public void setWorkout(Workout workout) {
		this.workout = workout;
	}
	public Timing getWorkoutTiming() {
		return workoutTiming;
	}
	public void setWorkoutTiming(Timing workoutTiming) {
		this.workoutTiming = workoutTiming;
	}
	public String getSpecialInstructions() {
		return specialInstructions;
	}
	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}
	public String getTrainerRemarks() {
		return trainerRemarks;
	}
	public void setTrainerRemarks(String trainerRemarks) {
		this.trainerRemarks = trainerRemarks;
	}
	public List<WorkoutPlanStatusDetail> getWorkoutPlanStatus() {
		return workoutPlanStatus;
	}
	public void setWorkoutPlanStatus(List<WorkoutPlanStatusDetail> workoutPlanStatus) {
		this.workoutPlanStatus = workoutPlanStatus;
	}
	public Integer getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(Integer planStatus) {
		this.planStatus = planStatus;
	}
	public String getUserRemarks() {
		return userRemarks;
	}
	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}
}
