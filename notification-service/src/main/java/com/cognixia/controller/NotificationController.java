package com.cognixia.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.model.Notification;
import com.cognixia.service.NotificationService;

@RestController
@RequestMapping("/notif")
public class NotificationController {

	@Autowired
	private NotificationService notifService;

	//GET
	@GetMapping
	public List<Notification> getApplications(){
		return notifService.listNotifications();
	}

	//POST
	@PostMapping
	public Notification addNotification(@RequestBody Notification notif){
		
		Notification newNotif = notifService.addNewNotification(notif);
		return newNotif;
	}

	//GET /{id}
	@GetMapping("/{notifID}")
	public Optional<Notification> getNotifById(@PathVariable int notifID){

		Optional<Notification> notif = notifService.getNotifByID(notifID);		
		return notif;	
	}
	
	// Sending Email
    @PostMapping("/send")
    public String sendMail(@RequestBody Notification details)
    {
        String status = notifService.sendEMail(details);
 
        return status;
    }

}
