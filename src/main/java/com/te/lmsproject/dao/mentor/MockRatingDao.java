package com.te.lmsproject.dao.mentor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.te.lmsproject.entity.mentor.MockRatings;

@Repository
public interface MockRatingDao extends JpaRepository<MockRatings, Integer>{

}
