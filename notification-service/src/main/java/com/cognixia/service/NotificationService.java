package com.cognixia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.model.Notification;
import com.cognixia.repository.NotificationRepository;

@Service
public class NotificationService{
	
	@Autowired
	NotificationRepository notifRepo;
	
	//GET all
	public List<Notification> listNotifications(){
		
		List<Notification> notifications = notifRepo.findAll();
		return notifications;
	}
	
	//POST cust
	public Notification addNewNotification(Notification notif) {
		notif.getMessage();
		Notification newNotif= notifRepo.save(notif);
		return newNotif;
	}	
	
	//GET cust by id
	public Optional<Notification> getNotifByID(int id){
		Optional<Notification> notif = notifRepo.findById(id);
		return notif;
	}

}
