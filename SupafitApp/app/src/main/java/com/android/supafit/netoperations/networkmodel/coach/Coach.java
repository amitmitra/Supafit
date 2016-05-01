package com.android.supafit.netoperations.networkmodel.coach;

import com.android.supafit.netoperations.networkmodel.user.User;

import java.io.Serializable;
import java.util.List;


public class Coach implements Serializable{


	private long id;
	private String coachId;
	private String name;
	private CoachType coachType;
	private String dob;
	private String email;
	private String otp;
	private String gcmId;
	private String password;
	private String imageURL;
	private String gender;
	private List<CoachPhoneNumber> phoneNumbers;
	private String alternateEmailId;
	private String yearsOfExperience;
	private String lastExperience;
	private String languagesKnown;
	private String aboutYourself;
	private String coreCompetence;
	List<CoachAddress> addresses;
	private List<User> users;
	private int numberOfUsers;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCoachId() {
		return coachId;
	}
	public void setCoachId(String coachId) {
		this.coachId = coachId;
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
	/*public Long getCoachTypeId() {
		return coachTypeId;
	}
	public void setCoachTypeId(Long coachTypeId) {
		this.coachTypeId = coachTypeId;
	}*/
	public String getPassword() {
		return password;
	}
	public CoachType getCoachType() {
		return coachType;
	}
	public void setCoachType(CoachType coachType) {
		this.coachType = coachType;
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
	public List<CoachPhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<CoachPhoneNumber> phoneNumbers) {
		phoneNumbers = phoneNumbers;
	}
	public String getAlternateEmailId() {
		return alternateEmailId;
	}
	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}
	public String getYearsOfExperience() {
		return yearsOfExperience;
	}
	public void setYearsOfExperience(String yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}
	public String getLastExperience() {
		return lastExperience;
	}
	public void setLastExperience(String lastExperience) {
		this.lastExperience = lastExperience;
	}
	public String getLanguagesKnown() {
		return languagesKnown;
	}
	public void setLanguagesKnown(String languagesKnown) {
		this.languagesKnown = languagesKnown;
	}
	public String getAboutYourself() {
		return aboutYourself;
	}
	public void setAboutYourself(String aboutYourself) {
		this.aboutYourself = aboutYourself;
	}
	public String getCoreCompetence() {
		return coreCompetence;
	}
	public void setCoreCompetence(String coreCompetence) {
		this.coreCompetence = coreCompetence;
	}
	public List<CoachAddress> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<CoachAddress> addresses) {
		this.addresses = addresses;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public int getNumberOfUsers() {
		return numberOfUsers;
	}
	public void setNumberOfUsers(int numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}
}
