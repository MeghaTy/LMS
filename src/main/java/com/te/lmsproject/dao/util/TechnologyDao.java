package com.te.lmsproject.dao.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.repository.util.Technologies;

@Repository
public interface TechnologyDao extends JpaRepository<Technologies, Integer>{
	

}
