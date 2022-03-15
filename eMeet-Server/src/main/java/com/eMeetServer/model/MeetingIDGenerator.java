package com.eMeetServer.model;

import java.util.Random;

public class MeetingIDGenerator 
{

    String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
	String numbers = "0123456789";

	   
    String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;


	 StringBuilder sb = new StringBuilder();

	   
	    Random random = new Random();
	    int length = 15;
	    
	    public String generateString()
	    {

	    for(int i = 0; i < length; i++) {

	     
	      int index = random.nextInt(alphaNumeric.length());

	     
	      char randomChar = alphaNumeric.charAt(index);

	   
	      sb.append(randomChar);
	    }
	      return sb.toString();
	    }
	  
}
	
	

