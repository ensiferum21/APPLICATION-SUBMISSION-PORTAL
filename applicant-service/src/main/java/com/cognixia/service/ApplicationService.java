package com.cognixia.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.common.exception.ApplicationNotFoundException;
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
	
	//POST cust
	public Application addApplication(Application app) {
		app.getAppStatus();
		app.getAppSubmissionDate();
		Application savedApp = appRepo.save(app);
		return savedApp;
	}	
	
	//GET cust by id
	public Application getAppByID(int id){
		Application app = appRepo.findById(id).orElseThrow(ApplicationNotFoundException::new);
		return app;
	}

	//read from MySQL, write to JSON file
	public void writeToJSON() {
		List<Application> app = appRepo.findAll();
		
		 String path = "C:\\Users\\Kevin\\OneDrive\\Documents\\GitHub\\APPLICATION-SUBMISSION-PORTAL\\applicant-service\\applicationJSON\\application.json";
		 
		 try {
	            ObjectMapper mapper = new ObjectMapper();
	            mapper.registerModule(new JavaTimeModule());
	            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	            mapper.writeValue(new File(path), app);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
