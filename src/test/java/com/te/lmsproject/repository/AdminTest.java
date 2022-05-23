package com.te.lmsproject.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AdminTest {

	ObjectMapper mapper = new ObjectMapper();
	String json="{\"adminId\":null,\"adminName\":\"abc\",\"emailId\":null,\"password\":null,\"gender\":null,\"role\":null}";
	
	@Test
	public void jsonConversionTest() throws JsonProcessingException {
		Admin admin = new Admin();
		admin.setAdminName("abc");
//		System.out.println(mapper.writeValueAsString(admin));
		Admin admin2= mapper.readValue(json, Admin.class);
		assertEquals("abc", admin2.getAdminName());
		
	}
}
