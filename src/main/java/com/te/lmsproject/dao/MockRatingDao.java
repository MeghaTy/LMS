package com.te.lmsproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.repository.MockRatings;

@Repository
public interface MockRatingDao extends JpaRepository<MockRatings, Integer>{

}
