package com.android.supafit.netoperations.networkmodel.user;

import java.io.Serializable;

public class UserMedicalCondition implements Serializable {


	private long id;
	private long userId;
	private long medicalConditionId;
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
	public long getMedicalConditionId() {
		return medicalConditionId;
	}
	public void setMedicalConditionId(long medicalConditionId) {
		this.medicalConditionId = medicalConditionId;
	}
}
