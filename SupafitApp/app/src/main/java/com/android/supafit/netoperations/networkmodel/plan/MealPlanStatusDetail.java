package com.android.supafit.netoperations.networkmodel.plan;

import com.android.supafit.netoperations.networkmodel.food.FoodItemMeasure;

import java.io.Serializable;

public class MealPlanStatusDetail implements Serializable{

	public MealPlanStatusDetail(){}
	public MealPlanStatusDetail(long id, Long mealPlanDetailId,
			String item, Double quantity, FoodItemMeasure foodItemMeasure) {
		super();
		this.id = id;
		this.mealPlanDetailId = mealPlanDetailId;
		this.item = item;
		this.quantity = quantity;
		this.foodItemMeasure = foodItemMeasure;
	}

	private long id;
	private Long mealPlanDetailId;
	private String item;
	private Double quantity;
	private FoodItemMeasure foodItemMeasure;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getMealPlanDetailId() {
		return mealPlanDetailId;
	}
	public void setMealPlanDetailId(Long mealPlanDetailId) {
		this.mealPlanDetailId = mealPlanDetailId;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public FoodItemMeasure getFoodItemMeasure() {
		return foodItemMeasure;
	}
	public void setFoodItemMeasure(FoodItemMeasure foodItemMeasure) {
		this.foodItemMeasure = foodItemMeasure;
	}
}
