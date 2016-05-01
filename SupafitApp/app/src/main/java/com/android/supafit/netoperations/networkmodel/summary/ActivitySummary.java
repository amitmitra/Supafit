package com.android.supafit.netoperations.networkmodel.summary;

import java.io.Serializable;

public class ActivitySummary implements Serializable{

	private long id;
	private Long userId;
	private String date;
	private int stepsWalked;
	private Double waterConsumed;
	private Double sleepConsumed;
	private Double caloriesGained;
	private Double caloriesBurned;
	private Integer stepsTarget;
	private Double waterTarget;
	private Double sleepTarget;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStepsWalked() {
		return stepsWalked;
	}
	public void setStepsWalked(int stepsWalked) {
		this.stepsWalked = stepsWalked;
	}
	public Double getWaterConsumed() {
		return waterConsumed;
	}
	public void setWaterConsumed(Double waterConsumed) {
		this.waterConsumed = waterConsumed;
	}
	public Double getSleepConsumed() {
		return sleepConsumed;
	}
	public void setSleepConsumed(Double sleepConsumed) {
		this.sleepConsumed = sleepConsumed;
	}
	public Double getCaloriesGained() {
		return caloriesGained;
	}
	public void setCaloriesGained(Double caloriesGained) {
		this.caloriesGained = caloriesGained;
	}
	public Double getCaloriesBurned() {
		return caloriesBurned;
	}
	public void setCaloriesBurned(Double caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}
	public Integer getStepsTarget() {
		return stepsTarget;
	}
	public void setStepsTarget(Integer stepsTarget) {
		this.stepsTarget = stepsTarget;
	}
	public Double getWaterTarget() {
		return waterTarget;
	}
	public void setWaterTarget(Double waterTarget) {
		this.waterTarget = waterTarget;
	}
	public Double getSleepTarget() {
		return sleepTarget;
	}
	public void setSleepTarget(Double sleepTarget) {
		this.sleepTarget = sleepTarget;
	}
}
