package com.cognixia.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
public class ProcessedApplication {

	@Id
	@GeneratedValue
	private int applicationID;
	
	private String name;	
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonDeserialize(using=LocalDateDeserializer.class)
	@JsonSerialize(using=LocalDateSerializer.class)
	private LocalDate dob;
	
	@JsonDeserialize(using=LocalDateTimeDeserializer.class)
	@JsonSerialize(using=LocalDateTimeSerializer.class)
	private LocalDateTime appSubmissionDate;
	
	private String race;
	private String countryOfBirth;
	private String covidVaccStatus;
	private String appStatus;
	
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
	
	public ProcessedApplication() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProcessedApplication(int applicationID, String name, LocalDate dob, LocalDateTime appSubmissionDate,
			String race, String countryOfBirth, String covidVaccStatus, String appStatus) {
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
