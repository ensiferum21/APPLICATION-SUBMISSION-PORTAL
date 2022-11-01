package com.cognixia.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Notification {

	@Id
	@GeneratedValue
	private int notificationID;
	
	private int applicationID;
	private String message = "This email is to notify that you have successfully submitted an application. Your application will be reviewed shortly!";	
	private String recipientEmail;
	private LocalDateTime notiSendDate = LocalDateTime.now();
	
	public int getNotificationID() {
		return notificationID;
	}
	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}
	public int getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(int applicationID) {
		this.applicationID = applicationID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRecipientEmail() {
		return recipientEmail;
	}
	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}
	public LocalDateTime getNotiSendDate() {
		return notiSendDate;
	}
	public void setNotiSendDate(LocalDateTime notiSendDate) {
		this.notiSendDate = notiSendDate;
	}
	
	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Notification(int notificationID, int applicationID, String message, String recipientEmail,
			LocalDateTime notiSendDate) {
		super();
		this.notificationID = notificationID;
		this.applicationID = applicationID;
		this.message = message;
		this.recipientEmail = recipientEmail;
		this.notiSendDate = notiSendDate;
	}
		
}
