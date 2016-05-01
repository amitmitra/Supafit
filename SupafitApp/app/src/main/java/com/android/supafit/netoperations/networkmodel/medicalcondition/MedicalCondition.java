package com.android.supafit.netoperations.networkmodel.medicalcondition;

import java.io.Serializable;

public class MedicalCondition implements Serializable{

	private long id;
	private String condition;
	private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
