package com.eMeetServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eMeetServer.model.User;
import com.eMeetServer.service.Impl.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired 
	private UserService userService;
	
	
	//this api is use to create a user 
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		try
		{
			
			return new ResponseEntity<>(userService.createUser(user),HttpStatus.OK);
			
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			return new ResponseEntity<>(null,HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	//get user details by username
	@GetMapping("/{username}")
	public ResponseEntity<User> getUser(@PathVariable("username") String username)
	{
		return new ResponseEntity<>(userService.findUser(username),HttpStatus.OK);
	}
	
	
	//use to delete an User by id
	@DeleteMapping("/deleteUser/{id}")
	public void deleteUser(@PathVariable("id") long id)
	{
		userService.deleteUser(id);
	}
	
	
	//use to update user details
	@PutMapping("/editUser")
	public ResponseEntity<User> editUser(@RequestBody User user)
	{
		return new ResponseEntity<>(userService.editUser(user),HttpStatus.OK);
	}
	
	
	///
	@PutMapping("/disableUser/{id}")
	public void disableUserr(@PathVariable long id)
	{
		userService.disableUser(id);
	}
	
	@GetMapping("/getAllUser")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
}
