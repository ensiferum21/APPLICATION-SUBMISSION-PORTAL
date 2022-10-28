package com.cognixia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognixia.common.exception.UserNotFoundException;
import com.cognixia.model.User;
import com.cognixia.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
//	@Autowired
//	AccountService accService;
	
	//GET all
	public List<User> listAllUsers(){
		List<User> users = userRepository.findAll();

//		for (User user : users) 
//		{
//			user.setAccounts(accService.getAccountsByCustomerId(user.getUserID()));
//		}
		return users;
	}
	
	//POST cust
	public User addUser(User user) {
		User savedUser = userRepository.save(user);
//		List<Account> accounts = cust.getAccounts();
//		
//		for(Account account:accounts) {
//			account.setCustomerID(savedCustomer.getCustomerID());
//			accService.addAccount(account);
//		}
//		savedCustomer.setAccounts(accounts);
		return savedUser;
	}	
	
	//GET cust by id
	public User getUserByID(int id){
		User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
//		user.setAccounts(accService.getAccountsByCustomerId(user.getCustomerID()));
		return user;
	}
	
	//PUT cust
	public User updateUser(User newUser) {
		getUserByID(newUser.getUserID());
		return addUser(newUser);
	}
	
	//DELETE cust
	public boolean deleteUser(int id) {
//		User userToDel = getUserByID(id);
//		List<Account> accounts = customerToDel.getAccounts();
//		
//		for(Account account:accounts) {
//			accService.deleteAccount(account.getAccountID());
//		}
		userRepository.deleteById(id);
		return true;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		CustomUserDetails userDetails = null;
		if(user !=null)
		{
			userDetails = new CustomUserDetails();
			userDetails.setUser(user);
				}
		else {
			throw new UsernameNotFoundException("username: " + username + " does not exist with the name");
		}		
	
		return userDetails;
	}

}
