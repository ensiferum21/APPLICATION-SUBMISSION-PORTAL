package com.cognixia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Application {
	
	private int applicationID;
	private String name;
	private LocalDate dob;
	private LocalDateTime appSubmissionDate;
	private String race;
	private String countryOfBirth;
	private String covidVaccStatus;
	private String appStatus = "Pending";
	private int userID;
	
	public int getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public LocalDateTime getAppSubmissionDate() {
		return appSubmissionDate;
	}
	public void setAppSubmissionDate(LocalDateTime appSubmissionDate) {
		this.appSubmissionDate = appSubmissionDate;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getCountryOfBirth() {
		return countryOfBirth;
	}
	public void setCountryOfBirth(String countryOfBirth) {
		this.countryOfBirth = countryOfBirth;
	}
	public String getCovidVaccStatus() {
		return covidVaccStatus;
	}
	public void setCovidVaccStatus(String covidVaccStatus) {
		this.covidVaccStatus = covidVaccStatus;
	}
	public String getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(String appStatus) {
		this.appStatus = appStatus;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
}