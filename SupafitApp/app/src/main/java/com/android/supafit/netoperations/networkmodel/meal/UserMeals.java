package com.android.supafit.netoperations.networkmodel.meal;

import java.io.Serializable;

public class UserMeals implements Serializable{


	private long id;
	private long mealId;
	private String item;
	private Double quantity;
	private Long itemMeasureId;
	private String calories;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getItem() {
		return item;
	}
	public long getMealId() {
		return mealId;
	}
	public void setMealId(long mealId) {
		this.mealId = mealId;
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
	public Long getItemMeasureId() {
		return itemMeasureId;
	}
	public void setItemMeasureId(Long itemMeasureId) {
		this.itemMeasureId = itemMeasureId;
	}
	public String getCalories() {
		return calories;
	}
	public void setCalories(String calories) {
		this.calories = calories;
	}
}
