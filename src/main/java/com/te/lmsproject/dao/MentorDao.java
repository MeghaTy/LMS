package com.te.lmsproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.repository.Mentor;

@Repository
public interface MentorDao extends JpaRepository<Mentor, String> {
	
	public Mentor findByMentorName(String mentorName);
	

	public List<Mentor> findByEmpIdIn(List<String> mentorId);


	public Mentor findByEmpId(String mentorId);

}
