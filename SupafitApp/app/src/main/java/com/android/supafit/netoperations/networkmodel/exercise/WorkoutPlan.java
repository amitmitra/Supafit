package com.android.supafit.netoperations.networkmodel.exercise;

import com.android.supafit.netoperations.networkmodel.coach.Coach;

import java.io.Serializable;
import java.util.List;

public class WorkoutPlan implements Serializable{

	private long userId;
	private Coach coach;
    private List<WorkoutDetail> details;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public Coach getCoach() {
		return coach;
	}
	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	public List<WorkoutDetail> getDetails() {
		return details;
	}
	public void setDetails(List<WorkoutDetail> details) {
		this.details = details;
	}
}
