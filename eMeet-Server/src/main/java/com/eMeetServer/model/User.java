package com.eMeetServer.model;


import java.util.Collection;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name="users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	
	@Column
	private String userType="USER";

	@Column(nullable=false)
	@Size(max=20)
	private String userName;
	
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	@Size(min=64)
	private String password;
	

	@Column(nullable=false)
	@Size(min=10)
	private String phoneno;
	

	@Column
	private boolean isActive=true;

	public User(){}




	public User(String userType,@Size(max = 20) String userName, String email, @Size(min = 64) String password,
			@Size(min = 10) String phoneno, boolean isActive) {
		super();
		
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phoneno = phoneno;
		this.isActive = isActive;
	}


	public String getUserType() {
		return userType;
	}




	public void setUserType(String userType) {
		this.userType = userType;
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPhoneno() {
		return phoneno;
	}


	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
   
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "UserMeeting",  joinColumns = { 
			@JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "MID") })
	private Set<Meeting> userMeetings=new HashSet<>();


///User Details Interface Methods 
	
	
	 
	public Set<Meeting> getUserMeetings() {
		return userMeetings;
	}


  

	public void setUserMeetings(Set<Meeting> userMeetings) {
		
		this.userMeetings = userMeetings;
	}




	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
	  Set<Authority> setAuthority=new HashSet<>();
		setAuthority.add(new Authority(userType));
		return setAuthority;
	}




	@Override
	public String getUsername() {
		
		return userName;
	}




	@Override
	public boolean isAccountNonExpired() {
		return true;
	}




	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}




	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}




	@Override
	public boolean isEnabled() {
		
		return this.isActive;
	}
}
