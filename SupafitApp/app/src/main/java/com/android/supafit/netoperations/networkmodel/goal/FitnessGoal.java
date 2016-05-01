package com.android.supafit.netoperations.networkmodel.goal;

import java.io.Serializable;

public class FitnessGoal implements Serializable{


	private long id;
	private String goal;
	private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
