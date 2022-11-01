package com.cognixia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Application {

	@Id
	@GeneratedValue
	private int applicationID;
	
	@NotNull(message="Name cannot be blank!")
	private String name;	
	
	@NotNull
	@PastOrPresent(message="Date of Birth cannot be future!")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	
//	@Transient
//	@Min(value=18, message = "Age should not be less than 18")
//	private int age = ageCalculator();
	
	private LocalDateTime appSubmissionDate = LocalDateTime.now();
	
	private String race;
	private String countryOfBirth;
	private String covidVaccStatus;
	private String appStatus = "Submitted";
	
//	public int ageCalculator() {
//		int age = 0;
//		LocalDate now = LocalDate.now();
//	    age = Period.between(dob, now).getYears();
//	    
//	    return age;
//	}
	
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
	
	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Application(int applicationID, @NotNull(message = "Name cannot be blank!") String name,
			@PastOrPresent(message = "Date of Birth cannot be future!") LocalDate dob,
			@PastOrPresent(message = "Submission date cannot be future!") LocalDateTime appSubmissionDate, String race,
			String countryOfBirth, String covidVaccStatus, String appStatus) {
		super();
		this.applicationID = applicationID;
		this.name = name;
		this.dob = dob;
		this.appSubmissionDate = appSubmissionDate;
		this.race = race;
		this.countryOfBirth = countryOfBirth;
		this.covidVaccStatus = covidVaccStatus;
		this.appStatus = appStatus;
	}		
	
}
