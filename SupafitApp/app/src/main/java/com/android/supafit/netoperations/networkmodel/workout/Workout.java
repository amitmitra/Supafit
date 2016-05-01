package com.android.supafit.netoperations.networkmodel.workout;

import java.io.Serializable;
import java.util.List;

public class Workout implements Serializable {

	private long id;
	private Long trainerId;
	private String workoutName;
	private List<UserWorkout> workouts;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}
	public String getWorkoutName() {
		return workoutName;
	}
	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}
	public List<UserWorkout> getWorkouts() {
		return workouts;
	}
	public void setWorkouts(List<UserWorkout> workouts) {
		this.workouts = workouts;
	}
}
