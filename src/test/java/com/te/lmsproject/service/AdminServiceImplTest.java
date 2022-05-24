package com.te.lmsproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.te.lmsproject.dao.admin.BatchDao;
import com.te.lmsproject.dao.admin.MentorDao;
import com.te.lmsproject.dao.admin.RequestDao;
import com.te.lmsproject.dao.employee.EmployeeDao;
import com.te.lmsproject.dao.util.TechnologyDao;
import com.te.lmsproject.dto.admin.AddBatchDto;
import com.te.lmsproject.repository.admin.Batch;
import com.te.lmsproject.repository.admin.Mentor;
import com.te.lmsproject.repository.util.Technologies;
import com.te.lmsproject.service.admin.AdminServiceImpl;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {
	@InjectMocks
	AdminServiceImpl adminServiceImpl;
	
	@Mock
	BatchDao batchDao;
	
	@Mock
	MentorDao mentorDao;
	
	@Mock
	TechnologyDao technologyDao;
	
	@Mock
	private RequestDao requestDao;
	
	@Mock
	private EmployeeDao employeeDao;


@Test
	 void addBatchTest() throws Exception {
		AddBatchDto batchDto= new AddBatchDto();
		batchDto.setBatchId(1);
		batchDto.setBatchName("abc");
		List<Batch> batchs= new ArrayList<Batch>();
		Batch batch = new Batch();
		batch.setBatchId(1);
		batch.setBatchName("abc");
		Mentor mentor = new Mentor();
		mentor.setMentorName("abc");
		batch.setMentor(mentor);
		List<Technologies> findAllById = new ArrayList<Technologies>();
		Technologies technologies= new Technologies();
		technologies.setId(1);
		findAllById.add(technologies);
		batch.setTechnicalId(findAllById);
		batchs.add(batch);
		mentor.setBatch(batchs);
				Mockito.when(mentorDao.findByMentorName(Mockito.any())).thenReturn(mentor);
		
				Mockito.when(technologyDao.findAllById(Mockito.any())).thenReturn(findAllById);
		
		
				Mockito.when(batchDao.save(batch)).thenReturn(batch);
				Batch batch2 =adminServiceImpl.addBatch(batchDto) ;
				assertEquals("abc", batch2.getBatchName());
	}
}
