package com.cognixia.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.cognixia.model.Application;
import com.cognixia.repository.ApplicationRepository;

@Controller
public class HomeController {
	
	@Autowired
	private ApplicationRepository appRepository;
	
	@GetMapping("/index")
    public String loadIndex(){
        return "index.html";
    }
	
	@GetMapping("/applicationform")
	public String showForm(Model model)
	{
		Application application = new Application();
		model.addAttribute("application", application);
		
		List<String> listRace = Arrays.asList("Chinese", "Malay", "Indian", "Eurasian");
		model.addAttribute("listRace", listRace);
		
		List<String> listCountry = Arrays.asList("Singapore", "Malaysia", "Thailand", "Vietnam");
		model.addAttribute("listCountry", listCountry);
		
		List<String> listVaccSataus = Arrays.asList("Vaccinated", "Not Vaccinated");
		model.addAttribute("listVaccSataus", listVaccSataus);
		
		return "application_form";
	}
	
	@PostMapping("/applicationform")
	public String submitForm(@ModelAttribute("user") Application user, HttpSession session) 
	{
	    System.out.println(user);
	    appRepository.save(user);
	    session.setAttribute("message", "Application submitted");
	    return "application_success";
	}
}