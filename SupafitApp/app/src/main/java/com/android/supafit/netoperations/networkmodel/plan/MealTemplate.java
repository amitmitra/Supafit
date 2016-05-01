package com.android.supafit.netoperations.networkmodel.plan;

import java.io.Serializable;

public class MealTemplate implements Serializable{

	private long id;
	private long userId;
	private long coachId;
	private long mealId;
	private long foodItemMeasureId;
	private long mealTimingsId;
	private String instructions;
	private String date;
	private int status;
	private String userRemarks;
	private String coachRemarks;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getCoachId() {
		return coachId;
	}
	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}
	public long getMealId() {
		return mealId;
	}
	public void setMealId(long mealId) {
		this.mealId = mealId;
	}
	public long getFoodItemMeasureId() {
		return foodItemMeasureId;
	}
	public void setFoodItemMeasureId(long foodItemMeasureId) {
		this.foodItemMeasureId = foodItemMeasureId;
	}
	public long getMealTimingsId() {
		return mealTimingsId;
	}
	public void setMealTimingsId(long mealTimingsId) {
		this.mealTimingsId = mealTimingsId;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUserRemarks() {
		return userRemarks;
	}
	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}
	public String getCoachRemarks() {
		return coachRemarks;
	}
	public void setCoachRemarks(String coachRemarks) {
		this.coachRemarks = coachRemarks;
	}
}
