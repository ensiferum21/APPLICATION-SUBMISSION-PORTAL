package com.cognixia.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cognixia.common.exception.UserIDMismatchException;
import com.cognixia.model.User;
import com.cognixia.repository.UserRepository;
import com.cognixia.service.ApplicationService;
import com.cognixia.service.UserService;

@RestController
@RequestMapping("/user/auth")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/index")
    public String loadIndex(){
        return "index.html";
    }	
	
	//GET /user
	@GetMapping
	public ResponseEntity<List<User>> getUser(){
		return ResponseEntity.ok(userService.listAllUsers());
	}
	
	//POST
	@PostMapping
	public ResponseEntity<User>addUser(@Valid @RequestBody User user){
		String pwd= user.getPassword();
		String encrptedPwd = passwordEncoder.encode(pwd);
		user.setPassword(encrptedPwd);
		
		User newUser = userService.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userID}")
				.buildAndExpand(newUser.getUserID()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	//GET /{id}
	@GetMapping("/{userID}")
	public ResponseEntity<User> getUserById(@PathVariable int userID){
		User userFound = userService.getUserByID(userID);
		if(userFound == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(userFound);
		}
	}
	
	//PUT /{id}
	@PutMapping("/{userID}")
	public ResponseEntity<User> updateUser(@PathVariable int userID, @Valid @RequestBody User newUser){	
		if(userID != newUser.getUserID())
			throw new UserIDMismatchException("IDs must match");
		User updatedUser = userService.updateUser(newUser);
		if(updatedUser == null)
			return ResponseEntity.notFound().build(); 
		else
			return ResponseEntity.ok(newUser);
	}
	
	//DELETE /{id}
	@DeleteMapping("/{userID}")
	public ResponseEntity<User> deleteUser(@PathVariable int userID)
	{
		if(userService.deleteUser(userID))
			return ResponseEntity.noContent().build();
		else
			return ResponseEntity.notFound().build(); 
	}

}
