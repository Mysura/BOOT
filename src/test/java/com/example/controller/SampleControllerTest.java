package com.example.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.model.Employee;
import com.example.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@org.springframework.transaction.annotation.Transactional
public class SampleControllerTest {

	@Mock
	public EmployeeService empService;

	@InjectMocks
	public SampleController sampleController;
	
	public MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(sampleController).build();
	}

	@Test
	public void testGetEmployees() throws Exception {
		Mockito.when(empService.findAll()).thenReturn(new ArrayList<Employee>());
		mockMvc.perform(get("/employees")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"));
	}

	@Test
	public void testCreateEmployee() throws Exception {
		Mockito.when(empService.findOne(Matchers.anyInt())).thenReturn(null);
		Employee emp = new Employee();
		emp.setId(20);
		Mockito.when(empService.save(Matchers.any(Employee.class))).thenReturn(emp);
		String json = new ObjectMapper().writeValueAsString(emp);
		mockMvc.perform(post("/create").content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(201)).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void testCreateEmployeeWithAlreadyData() throws Exception {
		Employee emp = new Employee();
		emp.setId(20);
		Mockito.when(empService.findOne(Matchers.anyInt())).thenReturn(emp);
		Mockito.when(empService.save(Matchers.any(Employee.class))).thenReturn(null);
		String json = new ObjectMapper().writeValueAsString(emp);
		mockMvc.perform(post("/create").content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(400));
	}

	@Test
	public void testCreateEmployeeWithNull() throws Exception {
		Employee emp = new Employee();
		emp.setId(20);
		Mockito.when(empService.findOne(Matchers.anyInt())).thenReturn(null);
		Mockito.when(empService.save(Matchers.any(Employee.class))).thenReturn(null);
		String json = new ObjectMapper().writeValueAsString(emp);
		mockMvc.perform(post("/create").content(json).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(400));
	}

	@Test
	public void testGetEmpWithData() throws Exception {
		Mockito.when(empService.findOne(Matchers.anyInt())).thenReturn(new Employee());
		mockMvc.perform(get("/employee/1")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void testGetEmpWithoutData() throws Exception {
		Mockito.when(empService.findOne(Matchers.anyInt())).thenReturn(null);
		mockMvc.perform(get("/employee/1")).andExpect(status().is(400));
	}
	
	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(delete("/delete/1")).andExpect(status().is(200));
		Mockito.verify(empService,Mockito.times(1)).delete(Matchers.anyInt());
	}
}
