package com.te.lmsproject.dao.mentor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.entity.mentor.Attendance;

@Repository
public interface AttendaceDao extends JpaRepository<Attendance, Integer>{

}
