package com.android.supafit.netoperations.networkmodel.coach;

import java.io.Serializable;

public class CoachPhoneNumber implements Serializable{


	private long id;
	private long coachId;
	private String type;
	private String number;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCoachId() {
		return coachId;
	}
	public void setCoachId(long coachId) {
		this.coachId = coachId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
