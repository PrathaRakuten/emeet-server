package com.eMeetServer;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class EMeetServerApplication  implements CommandLineRunner{

	
	
	public static void main(String[] args){
		SpringApplication.run(EMeetServerApplication.class, args);
		System.out.print("emeet server started");
	}

	@Override
	public void run(String... args) throws Exception {
		
		
	}

}
