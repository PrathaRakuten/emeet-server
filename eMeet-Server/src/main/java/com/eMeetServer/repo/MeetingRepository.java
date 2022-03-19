package com.eMeetServer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eMeetServer.model.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

	Meeting findByMeetingNo(String meetingno);
	
	List<Meeting> findAllByisPublished(boolean isPublished);
	
}
