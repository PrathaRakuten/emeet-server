package com.eMeetServer.model;



import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Meeting")
public class Meeting {
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int MId;
	
	
	@Column
	private String meetingNo;
	
	@Column
	private String URL;
	
	public LocalDateTime datetime;  
	
	


	@Column
	private String Location;
	@Column
	private boolean isPublished ;
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "userMeetings")
	private Set<User> userMeetings=new HashSet<>();
	
	public Meeting()
	{}

	public LocalDateTime getDatetime() {
		return datetime;
	}


	public void setDatetime(LocalDateTime datetime) {
		this.datetime = datetime;
	}


	public Set<User> getUserMeetings() {
		return userMeetings;
	}


	public void setUserMeetings(Set<User> meetingusers) {
		this.userMeetings = meetingusers;
	}


	public int getMId() {
		return MId;
	}


	public void setMId(int mId) {
		MId = mId;
	}


	public String getMeetingNo() {
		return meetingNo;
	}


	public void setMeetingNo(String meetingNo) {
		this.meetingNo = meetingNo;
	}


	public String getURL() {
		return URL;
	}


	public void setURL(String uRL) {
		URL = uRL;
	}


	public String getLocation() {
		return Location;
	}


	public void setLocation(String location) {
		Location = location;
	}


	public boolean isPublished() {
		return isPublished;
	}


	public void setPublished(boolean isPublished) {
		this.isPublished = isPublished;
	}

	


	
	
	
	
	

}
