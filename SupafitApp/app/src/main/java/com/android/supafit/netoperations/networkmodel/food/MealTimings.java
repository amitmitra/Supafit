package com.android.supafit.netoperations.networkmodel.food;

import java.io.Serializable;

public class MealTimings implements Serializable{

	private long id;
	private String type;
	private String timings;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTimings() {
		return timings;
	}
	public void setTimings(String timings) {
		this.timings = timings;
	}
}
