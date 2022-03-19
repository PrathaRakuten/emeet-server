package com.eMeetServer.Config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.eMeetServer.service.Impl.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		final String requestToken=request.getHeader("Authorization");
		System.out.println(requestToken);
		
		String username=null,jwtToken=null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer "))
		{
			try {
				
				jwtToken=requestToken.substring(7);
				username=this.jwtutil.extractUsername(jwtToken);
			}
			catch(ExpiredJwtException e){
				e.printStackTrace();
				System.out.println("Token expired");
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("error");
			}
		}
		else
		{
			System.out.println("Invalid Token");
		}
		
		
		if(username!=null &&SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userdetails=this.userDetailsServiceImpl.loadUserByUsername(username);
			
			if(this.jwtutil.validateToken(jwtToken, userdetails))
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationFilter =new UsernamePasswordAuthenticationToken(
						userdetails,null,userdetails.getAuthorities());
				usernamePasswordAuthenticationFilter.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationFilter);
			}
			
		}
		else {
			System.out.println("Token is not valid");
		}
		
		
		filterChain.doFilter(request, response);
		
	}
	
	

}
