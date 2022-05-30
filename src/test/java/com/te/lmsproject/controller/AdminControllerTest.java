package com.te.lmsproject.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.te.lmsproject.entity.admin.Batch;
import com.te.lmsproject.entity.util.ResponseBody;
import com.te.lmsproject.service.admin.AdminServiceImpl;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AdminControllerTest {

	@MockBean
	private AdminServiceImpl adminService;

	private MockMvc mockmvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void setUp() throws Exception {
		mockmvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	void testAddBatch() throws UnsupportedEncodingException, Exception {
		Batch addBatch = new Batch();
		Mockito.when(adminService.addBatch(Mockito.any())).thenReturn(addBatch);
		String result = mockmvc.perform(post("/api/admin/batch/").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapper.writeValueAsBytes(addBatch))
				.accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseBody response = mapper.readValue(result, ResponseBody.class);
		assertEquals("data inserted",response.getMessage());
	}



}
