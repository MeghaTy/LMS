package com.te.lmsproject.dao.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.entity.admin.Mentor;

@Repository
public interface MentorDao extends JpaRepository<Mentor, String> {
	
	public Mentor findByMentorName(String mentorName);
	

	public List<Mentor> findByEmployeeIdIn(List<String> mentorId);


	public Mentor findByEmployeeId(String mentorId);

}
