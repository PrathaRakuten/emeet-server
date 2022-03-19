package com.eMeetServer.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eMeetServer.model.Meeting;
import com.eMeetServer.model.User;
import com.eMeetServer.service.Impl.MeetingService;

@RestController
@RequestMapping("/meeting")
@CrossOrigin("*")
public class MeetingController {
	
	
	@Autowired
	MeetingService meetingService;
	
	
	@GetMapping("/getAllMeeting")
	public List<Meeting> getAllUsers()
	{
		return meetingService.getAllMeeting();
	}
	
	@PostMapping("/")
	public Meeting createMeeting(@RequestBody Meeting meeting)
	{
		try
		{
			
			return meetingService.createMeeting(meeting);
			
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/getMeetingbyId/{mid}")
	public Meeting getMeetingbyId(@PathVariable("mid") String mid )
	{
		return meetingService.meetingbyid(mid);
	}
	
	@PostMapping("/editMeeting")
	public Meeting editMeeting(@RequestBody Meeting meeting)
	{
		
		return meetingService.updateMeeting(meeting); 
	}
	
	
	@GetMapping("/isPublished")
	public List<Meeting> editMeeting()
	{
		
		return meetingService.isPublished();
	}
	
	
	@PutMapping("/{meetingNo}/users")
	public Set<User> setUserMeeting(
			@PathVariable("meetingNo") String meetingNo,
			@RequestBody Set<User> Meetingusers)
	{
		return meetingService.setMeetingsUser(Meetingusers,meetingNo);
	}
	

	
	
}
