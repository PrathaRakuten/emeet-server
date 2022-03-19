package com.eMeetServer.service.Impl;

import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eMeetServer.model.Meeting;
import com.eMeetServer.model.User;
import com.eMeetServer.repo.MeetingRepository;
import com.eMeetServer.service.IMeetingService;


@Service
public class MeetingService implements IMeetingService {

	
	@Autowired
	MeetingRepository meetingRepository;
	
	@Override
	public List<Meeting> getAllMeeting() {
		
		return meetingRepository.findAll();
		
	}
	
	public Meeting createMeeting(Meeting meeting) throws Exception
	{
         
		meeting.setMeetingNo(idGenerator());
		
    	meeting=meetingRepository.save(meeting);
		
		return meeting;
	}
	public String idGenerator()
	{
	  String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers = "0123456789";

		   
	    String alphaNumeric = upperAlphabet + numbers;


		 StringBuilder sb = new StringBuilder();

		   
		    Random random = new Random();
		    int length = 9;
		    
		    

		    for(int i = 0; i < length; i++) {

		     
		      int index = random.nextInt(alphaNumeric.length());

		     
		      char randomChar = alphaNumeric.charAt(index);

		   
		      sb.append(randomChar);
		    }
		      return "M"+sb.toString();
		    }

	@Override
	public Meeting updateMeeting(Meeting meeting) {
		meetingRepository.save(meeting);
		
		return meeting;
	}

	@Override
	public List<Meeting> isPublished() {
		return meetingRepository.findAllByisPublished(true);
	}

	@Override
	public Meeting meetingbyid(String meetingid) {
	return meetingRepository.findByMeetingNo(meetingid);
	}

	@Override
	public Set<User> setMeetingsUser(Set<User> meetingusers, String meetingNo) {
		Meeting m=meetingRepository.findByMeetingNo(meetingNo);
		System.out.println(m);
		//m.setUserMeetings(null);
		m.setUserMeetings(meetingusers);
		meetingRepository.save(m);
		return m.getUserMeetings();
	}

	
	
	

	

}
