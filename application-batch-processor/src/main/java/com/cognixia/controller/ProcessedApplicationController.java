package com.cognixia.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cognixia.model.ProcessedApplication;
import com.cognixia.service.ProcessedApplicationService;

@RestController
@RequestMapping("/procapp")
public class ProcessedApplicationController {

	@Autowired
	private ProcessedApplicationService processedAppService;
	
	//GET
	@GetMapping
	public ResponseEntity<List<ProcessedApplication>> getApplications(){
		return ResponseEntity.ok(processedAppService.listProcessedApplications());
	}
	
	@PostMapping
	public ResponseEntity<ProcessedApplication>addApplication(@RequestBody ProcessedApplication app){

		ProcessedApplication newApp = processedAppService.addApplication(app);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{appID}")
				.buildAndExpand(newApp.getApplicationID()).toUri();
		return ResponseEntity.created(location).build();
	}

}
