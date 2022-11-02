package com.cognixia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.model.ProcessedApplication;
import com.cognixia.repository.ProcessedApplicationRepository;

@Service
public class ProcessedApplicationService{
	
	@Autowired
	ProcessedApplicationRepository processedAppRepo;
	
	//GET all
	public List<ProcessedApplication> listProcessedApplications(){
		List<ProcessedApplication> applications = processedAppRepo.findAll();
		return applications;
	}
	
	public ProcessedApplication addApplication(ProcessedApplication app) {
		return processedAppRepo.save(app);
	}
	
}
