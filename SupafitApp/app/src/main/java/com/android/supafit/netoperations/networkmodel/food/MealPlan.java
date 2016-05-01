package com.android.supafit.netoperations.networkmodel.food;

import com.android.supafit.netoperations.networkmodel.coach.Coach;

import java.io.Serializable;
import java.util.List;


public class MealPlan implements Serializable{

	private long userId;
	private Coach coach;
    private List<FoodDetail> details;
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
	public List<FoodDetail> getDetails() {
		return details;
	}
	public void setDetails(List<FoodDetail> details) {
		this.details = details;
	}
}
