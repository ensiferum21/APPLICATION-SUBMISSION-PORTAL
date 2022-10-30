package com.cognixia.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cognixia.model.Application;
import com.cognixia.service.ApplicationService;

@RestController
@RequestMapping("/app")
public class ApplicationController {

	@Autowired
	private ApplicationService appService;
	
//	@GetMapping("/index")
//    public String loadIndex(){
//        return "index.html";
//    }
	
	//GET
	@GetMapping
	public ResponseEntity<List<Application>> getApplications(){
		appService.writeToJSON();
		return ResponseEntity.ok(appService.listApplications());
	}
	
	//POST
	@PostMapping
	public ResponseEntity<Application>addApplication(@Valid @RequestBody Application app){

		Application newApp = appService.addApplication(app);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{appID}")
				.buildAndExpand(newApp.getApplicationID()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	//GET /{id}
	@GetMapping("/{appID}")
	public ResponseEntity<Application> getAppById(@PathVariable int appID){
		Application appFound = appService.getAppByID(appID);
		if(appFound == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(appFound);
		}
	}

}
