package com.eMeetServer.service.Impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eMeetServer.model.User;
import com.eMeetServer.repo.UserRepository;
import com.eMeetServer.service.IUserService;


@Service
public class UserService implements IUserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User createUser(User user) throws Exception
	{
		//check if that user is already present or not
		
		User u=userRepository.findByUserName(user.getUserName());
		
		if(u!=null)
		{
			System.out.println("User Already Exist"+user.getUserName());
			throw new Exception("User already exist");
		}
		else
		{
			u=userRepository.save(user);
		}
		
		return u;
	}

	@Override
	public User findUser(String username) {
		
		return userRepository.findByUserName(username);
	}

	@Override
	public void deleteUser(long id) {
		
		userRepository.deleteById(id);
		
	}

	@Override
	public User editUser(User user) {
		
		return userRepository.save(user);
		
	}

	@Override
	public void disableUser(long id) {
		User temp=userRepository.findById(id).orElse(null);
		
		System.out.println(temp.getEmail());
		temp.setActive(false);
		userRepository.save(temp);
		
	}

    public List<User> getAllUsers()
	{
		return userRepository.findAll();
		
	}
	
	

}
