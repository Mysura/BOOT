package com.example.service;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.Repository.EmployeeMongoRepository;
import com.example.Repository.EmployeeRepository;
import com.example.model.Employee;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class EmployeeServiceImplTest{
	
	@Mock
	public EmployeeRepository employeeRepository;
	@Mock
	public EmployeeMongoRepository employeeMongoRepository;
	
	@InjectMocks
	public EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindAll(){
		Mockito.when(employeeRepository.findAll()).thenReturn(new ArrayList<Employee>());
		Collection<Employee> empObjs = employeeServiceImpl.findAll();
		Assert.assertEquals(empObjs.size(), 0);
	}
	
	@Test
	public void testFindOne() {
		Employee emp = new Employee();
		emp.setId(25);
		emp.setName("xxxx");
		Mockito.when(employeeRepository.findOne(Matchers.anyInt())).thenReturn(emp);
		Employee empl = employeeServiceImpl.findOne(10);
		Assert.assertEquals(Integer.valueOf(empl.getId()), Integer.valueOf(25));
		Assert.assertEquals(empl.getName(),"xxxx");
	}
	
	@Test(expected =RuntimeException.class)
	public void testSaveWithException() { 
		Employee emp1 = new Employee();
		emp1.setId(6);
		emp1.setName("xxxx");
		Mockito.when(employeeRepository.save(emp1)).thenReturn(emp1);
		Employee employee = employeeServiceImpl.save(emp1);
		Assert.assertEquals(Integer.valueOf(employee.getId()), Integer.valueOf(25));
		Assert.assertEquals(employee.getName(),"xxxx");
	}
	@Test
	public void testSave() throws Exception{ 
		Employee emp1 = new Employee();
		emp1.setId(25);
		emp1.setName("xxxx");
		Mockito.when(employeeRepository.save(emp1)).thenReturn(emp1);
		Employee employee = employeeServiceImpl.save(emp1);
		Assert.assertEquals(Integer.valueOf(employee.getId()), Integer.valueOf(25));
		Assert.assertEquals(employee.getName(),"xxxx");
	}
	
	@Test
	public void testDelete(){
		employeeServiceImpl.delete(1);
		Mockito.verify(employeeRepository,Mockito.times(1)).delete(Matchers.anyInt());
	}

}
