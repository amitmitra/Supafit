package com.android.supafit.netoperations.networkmodel.plan;

import com.android.supafit.netoperations.networkmodel.food.MealTimings;
import com.android.supafit.netoperations.networkmodel.meal.Meals;

import java.io.Serializable;
import java.util.List;

public class MealPlanDetail implements Serializable{

	private long id;
	private String planDate;
	private MealPlans mealPlans;
	private Meals meal;
	private MealTimings mealTiming;
	private String specialInstructions;
	private String coachRemarks;
	private List<MealPlanStatusDetail> mealPlanStatus;
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
	public Meals getMeal() {
		return meal;
	}
	public MealPlans getMealPlans() {
		return mealPlans;
	}
	public void setMealPlans(MealPlans mealPlans) {
		this.mealPlans = mealPlans;
	}
	public void setMeal(Meals meal) {
		this.meal = meal;
	}
	public MealTimings getMealTiming() {
		return mealTiming;
	}
	public void setMealTiming(MealTimings mealTiming) {
		this.mealTiming = mealTiming;
	}
	public String getSpecialInstructions() {
		return specialInstructions;
	}
	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}
	public String getCoachRemarks() {
		return coachRemarks;
	}
	public void setCoachRemarks(String coachRemarks) {
		this.coachRemarks = coachRemarks;
	}
	/*public List<MealPlanStatus> getMealPlanStatus() {
		return mealPlanStatus;
	}
	public void setMealPlanStatus(List<MealPlanStatus> mealPlanStatus) {
		this.mealPlanStatus = mealPlanStatus;
	}*/
	/*public MealPlanStatus getMealPlanStatus() {
		return mealPlanStatus;
	}
	public void setMealPlanStatus(MealPlanStatus mealPlanStatus) {
		this.mealPlanStatus = mealPlanStatus;
	}*/
	/*public List<MealPlanStatus> getMealPlanStatus() {
		return mealPlanStatus;
	}
	public void setMealPlanStatus(List<MealPlanStatus> mealPlanStatus) {
		this.mealPlanStatus = mealPlanStatus;
	}*/
	public Integer getPlanStatus() {
		return planStatus;
	}
	public List<MealPlanStatusDetail> getMealPlanStatus() {
		return mealPlanStatus;
	}
	public void setMealPlanStatus(List<MealPlanStatusDetail> mealPlanStatus) {
		this.mealPlanStatus = mealPlanStatus;
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
