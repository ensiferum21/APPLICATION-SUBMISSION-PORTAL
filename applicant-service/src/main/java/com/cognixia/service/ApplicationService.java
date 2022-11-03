package com.cognixia.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.common.exception.ApplicationNotFoundException;
import com.cognixia.common.exception.InvalidAgeException;
import com.cognixia.model.Application;
import com.cognixia.repository.ApplicationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class ApplicationService{
	
	@Autowired
	ApplicationRepository appRepo;
	
	//GET all
	public List<Application> listApplications(){
		List<Application> applications = appRepo.findAll();
		return applications;
	}
	
//	public void ageCalculator(Application app) {
//	    LocalDate d = LocalDate.now();
//	    int age = Period.between(app.getDob(), d).getYears();
//	    
//	    if(age < 18) {
//	    	String msg = "Age should not be less than 18";
//	    	System.out.println(msg);
//	    }
//	}
	
	//POST application
	public Application addApplication(Application app) {
		Application savedApp = null;
		
		LocalDate d = LocalDate.now();
	    int age = Period.between(app.getDob(), d).getYears();
		
	    if(age < 18) {
	    	throw new InvalidAgeException();
	    }else {
	    	savedApp = appRepo.save(app);
	    }
		return savedApp;
	}	
	
	//GET application by id
	public Application getAppByID(int id){
		Application app = appRepo.findById(id).orElseThrow(ApplicationNotFoundException::new);
		return app;
	}

	//read from MySQL, write to JSON file
	public void writeToJSON() {
		List<Application> app = appRepo.findAll();
		
		 String path = "C:\\Users\\Kevin\\OneDrive\\Documents\\GitHub\\APPLICATION-SUBMISSION-PORTAL\\applicant-service\\src\\main\\resources\\tempJSON\\application.json";
		 
		 try {
	            ObjectMapper mapper = new ObjectMapper();
	            mapper.registerModule(new JavaTimeModule());
	            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	            mapper.writeValue(new File(path), app);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public boolean purgeData() {
		appRepo.deleteAll();
		return true;
	}
	
}
