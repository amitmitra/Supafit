package com.android.supafit.netoperations.networkmodel.plan;

import java.io.Serializable;
import java.util.List;

public class MealPlans implements Serializable{

	private long id;
	private Long userId;
	private Long dietitianId;
	private String dateCreated;
	private String dietitianRemarks;
	private List<MealPlanDetail> mealPlanDetails;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getDietitianId() {
		return dietitianId;
	}
	public void setDietitianId(Long dietitianId) {
		this.dietitianId = dietitianId;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDietitianRemarks() {
		return dietitianRemarks;
	}
	public void setDietitianRemarks(String dietitianRemarks) {
		this.dietitianRemarks = dietitianRemarks;
	}
	public List<MealPlanDetail> getMealPlanDetails() {
		return mealPlanDetails;
	}
	public void setMealPlanDetails(List<MealPlanDetail> mealPlanDetails) {
		this.mealPlanDetails = mealPlanDetails;
	}
}
