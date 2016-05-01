package com.android.supafit.netoperations.networkmodel.exercise;

import java.io.Serializable;

public class ExerciseCategory implements Serializable{

	private long id;
	private String category;
	private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
