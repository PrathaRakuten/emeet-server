package com.eMeetServer.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eMeetServer.Config.JwtUtil;
import com.eMeetServer.model.JwtRequest;
import com.eMeetServer.model.JwtResponse;
import com.eMeetServer.model.User;
import com.eMeetServer.service.Impl.UserDetailsServiceImpl;


@RestController
@CrossOrigin("*")
public class AuthController  {
	
	
	@Autowired
	private AuthenticationManager authenticationManage;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtil jwtutil;
	
	//api to generate token
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		try {
			
			authenticate(jwtRequest.getUserName(),jwtRequest.getPassWord());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e);
		}
		
		UserDetails userDetails =this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUserName());
		
		String token=this.jwtutil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
	private void authenticate(String username,String password) throws Exception
	{
		try {
			 authenticationManage.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}
		catch(DisabledException e)
		{
			throw new Exception("User Disable"+e.getMessage());
		}
		catch(BadCredentialsException e)
		{
			throw new Exception("Bad Credentials "+e.getMessage());
		}
	}
	
	
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal)
	{
		return ((User)this.userDetailsServiceImpl.loadUserByUsername(principal.getName()));
	}


	

}
