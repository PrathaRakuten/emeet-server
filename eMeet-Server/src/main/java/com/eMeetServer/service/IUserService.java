package com.eMeetServer.service;

import java.util.List;
import java.util.Set;

import com.eMeetServer.model.Meeting;
import com.eMeetServer.model.User;

public interface IUserService {
	
	
	//this method is use to add new user in database 
	//first it will check that if that user is already exist in db if it exist
	//it throw an exception 
    User createUser(User user) throws Exception;
    
    
    //find user by username
    User findUser(String username) ;

    
    //delete user by id
    
    void deleteUser(long id);
 
    //edit a user
    
    User editUser(User user);
    
    //disable user
    void disableUser(long id);
    
    //display all the users
    List<User> getAllUsers();
    
    Set<Meeting> findUserMeeting(String username);
    
    Set<Meeting> setUserMeetings(Set<Meeting> meetings,String userName);
    
   
}
