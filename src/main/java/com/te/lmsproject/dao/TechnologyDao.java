package com.te.lmsproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.entity.util.Technologies;

@Repository
public interface TechnologyDao extends JpaRepository<Technologies, Integer>{
	

}
