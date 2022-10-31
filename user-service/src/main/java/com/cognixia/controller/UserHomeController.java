package com.cognixia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserHomeController {
	
//	@GetMapping("/redirect")
//	public String homeRedirect()
//	{
//		return "redirect";
//	}
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public ModelAndView method() {
	    return new ModelAndView("redirect:" + "http://localhost:8080/api/applicantservice/");
	}

}
