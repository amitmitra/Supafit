package com.android.supafit.netoperations.networkmodel.food;

import java.io.Serializable;

public class FoodItems implements Serializable{

	private long id;
	private long food_type_id;
	private String foodItem;
	private String description;
	private String calories;
	private String measureParameter;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFood_type_id() {
		return food_type_id;
	}
	public void setFood_type_id(long food_type_id) {
		this.food_type_id = food_type_id;
	}
	public String getFoodItem() {
		return foodItem;
	}
	public void setFoodItem(String foodItem) {
		this.foodItem = foodItem;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCalories() {
		return calories;
	}
	public void setCalories(String calories) {
		this.calories = calories;
	}
	public String getMeasureParameter() {
		return measureParameter;
	}
	public void setMeasureParameter(String measureParameter) {
		this.measureParameter = measureParameter;
	}
}
