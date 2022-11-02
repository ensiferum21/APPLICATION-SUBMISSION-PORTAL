package com.cognixia.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognixia.model.Application;

@Service
@FeignClient(name = "APPLICANT-SERVICE")
public interface ApplicationService {
	
//	@GetMapping("/applicationform")
//	public String showForm(Model model);
	
    @GetMapping("/applications/user/{user}")
    public List<Application> getApplicationsByUserId(@PathVariable int userid);

    @PostMapping("/applications")
    public ResponseEntity<Application> addApplication(@RequestBody Application application);

    @DeleteMapping("/applications/{id}")
    public ResponseEntity<Application> deleteApplicationById(@PathVariable int id);
}
