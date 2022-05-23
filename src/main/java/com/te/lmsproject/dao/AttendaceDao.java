package com.te.lmsproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.repository.Attendance;

@Repository
public interface AttendaceDao extends JpaRepository<Attendance, Integer>{

}
