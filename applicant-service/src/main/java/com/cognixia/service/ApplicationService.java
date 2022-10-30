package com.cognixia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.common.exception.ApplicationNotFoundException;
import com.cognixia.model.Application;
import com.cognixia.repository.ApplicationRepository;

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
		Application savedApp = appRepo.save(app);
		return savedApp;
	}	
	
	//GET cust by id
	public Application getAppByID(int id){
		Application app = appRepo.findById(id).orElseThrow(ApplicationNotFoundException::new);
		return app;
	}

}
