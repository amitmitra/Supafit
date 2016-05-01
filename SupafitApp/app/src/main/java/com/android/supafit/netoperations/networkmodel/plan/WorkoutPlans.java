package com.android.supafit.netoperations.networkmodel.plan;

import java.io.Serializable;
import java.util.List;

public class WorkoutPlans implements Serializable{

	private long id;
	private Long userId;
	private Long trainerId;
	private String dateCreated;
	private String trainerRemarks;
	private List<WorkoutPlanDetail> workoutPlanDetails;
	
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
	public Long getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(Long trainerId) {
		this.trainerId = trainerId;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getTrainerRemarks() {
		return trainerRemarks;
	}
	public void setTrainerRemarks(String trainerRemarks) {
		this.trainerRemarks = trainerRemarks;
	}
	public List<WorkoutPlanDetail> getWorkoutPlanDetails() {
		return workoutPlanDetails;
	}
	public void setWorkoutPlanDetails(List<WorkoutPlanDetail> workoutPlanDetails) {
		this.workoutPlanDetails = workoutPlanDetails;
	}
}
