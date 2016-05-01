package com.android.supafit.netoperations.networkmodel.plan;

import java.io.Serializable;

public class WorkoutTemplate implements Serializable{

	private long id;
	private Long userId;
	private Long coachId;
	private long workoutId;
	private long exerciseTimingId;
	private String instructions;
	private String date;
	private int status;
	private String userRemarks;
	private String coachRemarks;
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
	public Long getCoachId() {
		return coachId;
	}
	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}
	public long getWorkoutId() {
		return workoutId;
	}
	public void setWorkoutId(long workoutId) {
		this.workoutId = workoutId;
	}
	public long getExerciseTimingId() {
		return exerciseTimingId;
	}
	public void setExerciseTimingId(long exerciseTimingId) {
		this.exerciseTimingId = exerciseTimingId;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUserRemarks() {
		return userRemarks;
	}
	public void setUserRemarks(String userRemarks) {
		this.userRemarks = userRemarks;
	}
	public String getCoachRemarks() {
		return coachRemarks;
	}
	public void setCoachRemarks(String coachRemarks) {
		this.coachRemarks = coachRemarks;
	}
}
