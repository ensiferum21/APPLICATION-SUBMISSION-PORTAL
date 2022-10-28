package com.cognixia.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class User {

	@Id
	@GeneratedValue
	private int userID;
	
	@NotNull(message="Username cannot be blank!")
	private String username;
	
	@NotNull(message="Password cannot be blank!")
	private String password;
	
	private String email;
	
	@Size(min=8, max=8, message="mobile number only 8 digits")
	private String mobile;
	private LocalDateTime timestamp;
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int userID, String username, String password, String email, String mobile, LocalDateTime timestamp) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobile = mobile;
		this.timestamp = timestamp;
	}
	
}
