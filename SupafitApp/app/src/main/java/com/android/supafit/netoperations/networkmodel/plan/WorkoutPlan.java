package com.android.supafit.netoperations.networkmodel.plan;

import com.android.supafit.netoperations.networkmodel.workout.UserWorkout;

import java.io.Serializable;
import java.util.List;

public class WorkoutPlan implements Serializable{

	private String timing;
	private List<UserWorkout> items;
	public String getTiming() {
		return timing;
	}
	public void setTiming(String timing) {
		this.timing = timing;
	}
	public List<UserWorkout> getItems() {
		return items;
	}
	public void setItems(List<UserWorkout> items) {
		this.items = items;
	}
}
