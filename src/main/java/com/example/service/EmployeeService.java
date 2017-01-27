package com.example.service;

import java.util.Collection;

import com.example.model.Employee;

public interface EmployeeService {
	
	public Collection<Employee> findAll();
	
	public Employee findOne(int id);
	
	public Employee save(Employee emp);
	
	public void delete(int id);

	public Employee findByMongoUserName(String userName);
	
	

}
