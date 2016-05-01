package com.android.supafit.netoperations.networkmodel.user;

import com.android.supafit.netoperations.networkmodel.address.Address;
import com.android.supafit.netoperations.networkmodel.coach.Coach;
import com.android.supafit.netoperations.networkmodel.food.FoodPreferences;
import com.android.supafit.netoperations.networkmodel.goal.FitnessGoal;
import com.android.supafit.netoperations.networkmodel.medicalcondition.MedicalCondition;

import java.io.Serializable;
import java.util.List;


public class User implements Serializable {
	private long id;
	private String userId;
	private Coach trainer;
	private String name;
	private String dob;
	private String email;
	private String otp;
	private String gcmId;
	private String password;
	private String imageURL;
	private String gender;
	private List<PhoneNumber> phoneNumbers;
	private UserPhysic userPhysic;
	private Coach dietitan;
	private String alternateEmailId;
	private String fieldOfWork;
	private String lifestyle;
	List<Address> userAddresses;
	List<FitnessGoal> goals;
	List<MedicalCondition> medicalConditions;
	List<FoodPreferences> foodPreferences;
	private String dateCreated;
	private String dateUpdated;
	private Coach yogaTrainer;
	private List<ProgramSubscription> subscriptions;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getGcmId() {
		return gcmId;
	}
	public void setGcmId(String gcmId) {
		this.gcmId = gcmId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		phoneNumbers = phoneNumbers;
	}
	public UserPhysic getUserPhysic() {
		return userPhysic;
	}
	public void setUserPhysic(UserPhysic userPhysic) {
		this.userPhysic = userPhysic;
	}
	public String getAlternateEmailId() {
		return alternateEmailId;
	}
	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}
	public String getFieldOfWork() {
		return fieldOfWork;
	}
	public void setFieldOfWork(String fieldOfWork) {
		this.fieldOfWork = fieldOfWork;
	}
	public String getLifestyle() {
		return lifestyle;
	}
	public void setLifestyle(String lifestyle) {
		this.lifestyle = lifestyle;
	}
	public List<Address> getUserAddresses() {
		return userAddresses;
	}
	public void setUserAddresses(List<Address> userAddresses) {
		this.userAddresses = userAddresses;
	}
	public List<FitnessGoal> getGoals() {
		return goals;
	}
	public void setGoals(List<FitnessGoal> goals) {
		this.goals = goals;
	}
	public List<MedicalCondition> getMedicalConditions() {
		return medicalConditions;
	}
	public void setMedicalConditions(List<MedicalCondition> medicalConditions) {
		this.medicalConditions = medicalConditions;
	}
	public List<FoodPreferences> getFoodPreferences() {
		return foodPreferences;
	}
	public void setFoodPreferences(List<FoodPreferences> foodPreferences) {
		this.foodPreferences = foodPreferences;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public Coach getDietitan() {
		return dietitan;
	}
	public void setDietitan(Coach dietitan) {
		this.dietitan = dietitan;
	}
	public Coach getYogaTrainer() {
		return yogaTrainer;
	}
	public void setYogaTrainer(Coach yogaTrainer) {
		this.yogaTrainer = yogaTrainer;
	}
	public List<ProgramSubscription> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(List<ProgramSubscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
	public Coach getTrainer() {
		return trainer;
	}
	public void setTrainer(Coach trainer) {
		this.trainer = trainer;
	}
}
