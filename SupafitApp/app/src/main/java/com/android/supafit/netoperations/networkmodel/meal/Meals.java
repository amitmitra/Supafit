package com.android.supafit.netoperations.networkmodel.meal;

import java.io.Serializable;
import java.util.List;

public class Meals implements Serializable{

	private long id;
	private String name;
	private Long dietitianId;
	private List<UserMeals> meals;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getDietitianId() {
		return dietitianId;
	}
	public void setDietitianId(Long dietitianId) {
		this.dietitianId = dietitianId;
	}
	public List<UserMeals> getMeals() {
		return meals;
	}
	public void setMeals(List<UserMeals> meals) {
		this.meals = meals;
	}
}
