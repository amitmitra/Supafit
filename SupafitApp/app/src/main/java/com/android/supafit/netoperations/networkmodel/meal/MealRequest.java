package com.android.supafit.netoperations.networkmodel.meal;

import java.io.Serializable;
import java.util.List;

public class MealRequest implements Serializable{

	private long id;
	private long userId;
	private String mealName;
	private List<UserMeals> meals;
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
	public String getMealName() {
		return mealName;
	}
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
	public List<UserMeals> getMeals() {
		return meals;
	}
	public void setMeals(List<UserMeals> meals) {
		this.meals = meals;
	}
}
