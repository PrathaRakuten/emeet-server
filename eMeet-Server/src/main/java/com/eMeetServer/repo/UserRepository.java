package com.eMeetServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.eMeetServer.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

	
	public User findByUserName(String username);
	
	
}
