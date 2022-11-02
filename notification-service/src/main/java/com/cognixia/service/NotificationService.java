package com.cognixia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.cognixia.model.Notification;
import com.cognixia.repository.NotificationRepository;

@Service
public class NotificationService{
	
	@Autowired
	NotificationRepository notifRepo;
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String sender;
	
	//GET all
	public List<Notification> listNotifications(){
		
		List<Notification> notifications = notifRepo.findAll();
		return notifications;
	}
	
	//POST cust
//	public Notification addNewNotification(Notification notif) {
//		notif.getMessage();
//		notif.getNotiSendDate();
//		Notification newNotif= notifRepo.save(notif);
//		return newNotif;
//	}	
	
	//GET cust by id
	public Optional<Notification> getNotifByID(int id){
		Optional<Notification> notif = notifRepo.findById(id);
		return notif;
	}
	
	public String sendEMail(Notification details) {
		// Try block to check for exceptions
		try {
			// Creating a simple mail message
			SimpleMailMessage mailMessage = new SimpleMailMessage();

			// Setting up necessary details
			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipientEmail());
			mailMessage.setText(details.getMessage());
			mailMessage.setSubject(details.getSubject());

			// Sending the mail
			javaMailSender.send(mailMessage);
			details.getMessage();
			details.getNotiSendDate();
			notifRepo.save(details);
			
			return "Mail Sent Successfully...";
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			return "Error while Sending Mail";
		}
	}

}
