package com.cognixia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cognixia.model.Application;

@Service
public class ApplicationServiceDummy implements ApplicationService{

	 @Override
	    public List<Application> getApplicationsByUserId(int userid) {
	        return new ArrayList<>();
	    }

	    @Override
	    public ResponseEntity<Application> addApplication(Application application) {
	        return ResponseEntity.ok(new Application());
	    }

	    @Override
	    public ResponseEntity<Application> deleteApplicationById(int id) {
	        return ResponseEntity.ok(new Application());
	    }
}
