package com.cognixia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserHomeController {
	
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public ModelAndView method() {
	    return new ModelAndView("redirect:" + "http://localhost:8080/api/applicantservice/");
	}
	
	// I ADDED
	@RequestMapping(value = {"/logout"}, method = RequestMethod.POST)
    public String logoutDo(HttpServletRequest request,HttpServletResponse response)
    {
        return "redirect:/login";
    }

}
