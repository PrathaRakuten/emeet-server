package com.eMeetServer.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eMeetServer.model.Meeting;
import com.eMeetServer.model.User;
import com.eMeetServer.repo.UserRepository;
import com.eMeetServer.service.Impl.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired 
	private UserService userService;
	
	
	
	
	//this api is use to create a user 
	@PostMapping("/")
	public User createUser(@RequestBody User user)
	{
		try
		{
			
			return userService.createUser(user);
			
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			return null;
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
	
	@GetMapping("/getUserMeeting/{username}")
	public Set<Meeting> getUserMeeting(@PathVariable("username") String username)
	{
		return userService.findUserMeeting(username);
	}
	
	
	@PutMapping("/{userName}/meetings")
	public Set<Meeting> setUserMeeting(
			@PathVariable("userName") String userName,
			@RequestBody Set<Meeting> userMeetings)
	{
		return userService.setUserMeetings(userMeetings,userName);
	}
	
	 
	
	
	
	
}
