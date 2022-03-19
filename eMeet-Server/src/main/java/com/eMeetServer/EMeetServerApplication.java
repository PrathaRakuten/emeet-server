package com.eMeetServer;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eMeetServer.model.User;
import com.eMeetServer.repo.UserRepository;
import com.eMeetServer.service.Impl.MeetingService;



@SpringBootApplication
public class EMeetServerApplication  implements CommandLineRunner{

	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args){
		SpringApplication.run(EMeetServerApplication.class, args);
		System.out.print("emeet server started");
		

	
		
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		
		
	}

}
