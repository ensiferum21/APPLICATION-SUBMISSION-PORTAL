package com.cognixia.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Service
@FeignClient(name = "APPLICANT-SERVICE")
public interface ApplicationService {
	
//	@GetMapping("/applicationform")
//	public String showForm(Model model);
}
