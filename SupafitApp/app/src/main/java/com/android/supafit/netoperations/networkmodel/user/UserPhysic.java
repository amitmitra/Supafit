package com.android.supafit.netoperations.networkmodel.user;

import java.io.Serializable;

public class UserPhysic implements Serializable {


	private long id;
	private long userId;
	private int age;
	private String weight;
	private String height;
	private String bmi;
	private String bmr;
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
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getBmi() {
		return bmi;
	}
	public void setBmi(String bmi) {
		this.bmi = bmi;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBmr() {
		return bmr;
	}
	public void setBmr(String bmr) {
		this.bmr = bmr;
	}
}
