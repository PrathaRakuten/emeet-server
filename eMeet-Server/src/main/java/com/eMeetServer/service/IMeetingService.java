package com.eMeetServer.service;

import java.util.List;
import java.util.Set;

import com.eMeetServer.model.Meeting;
import com.eMeetServer.model.User;

public interface IMeetingService {

	List<Meeting> getAllMeeting()  throws Exception;	
	
	Meeting updateMeeting(Meeting meeting);
	
   List<Meeting> isPublished();
   
   Meeting meetingbyid(String meetingid);
   
   Set<User> setMeetingsUser(Set<User> meetingusers, String meetingNo);
}
