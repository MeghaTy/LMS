package com.te.lmsproject.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.repository.Batch;
import com.te.lmsproject.repository.Mentor;

@Repository
public interface BatchDao extends JpaRepository<Batch, Integer> {

	Batch findByBatchId(int batchId);
	
	Batch findByBatchName(String batchName);

	List<Batch> findByMentor(Mentor mentor);
	

}
