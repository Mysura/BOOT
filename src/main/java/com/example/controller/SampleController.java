package com.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import com.example.service.EmployeeService;

@RestController
public class SampleController {

	@Autowired
	public EmployeeService empService;

	// fetching all employees.
	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Employee>> getAllEmployess() {
		return new ResponseEntity<Collection<Employee>>(empService.findAll(), HttpStatus.OK);
	}

	// inserting Employees.
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) throws Exception{
		if (empService.findOne(emp.getId()) != null) {
			return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		} else {
			Employee createdEmployee = empService.save(emp);
			if (createdEmployee != null) {
				return new ResponseEntity<Employee>(createdEmployee, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	//getParticular Employee.
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") int id) {
		    Employee emp = empService.findOne(id);
		    if(emp != null) {
		    return new ResponseEntity<Employee>(emp, HttpStatus.OK);
		    }else{
		    	return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
		    }
	}
	
	//delete Employee
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") int id){
		empService.delete(id);
	}
	
	//getParticular Employee.
		@RequestMapping(value = "/employee/mongo/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Employee> getEmployeeName(@PathVariable("name") String name) {
			    Employee emp = empService.findByMongoUserName(name);
			    if(emp != null) {
			    return new ResponseEntity<Employee>(emp, HttpStatus.OK);
			    }else{
			    	return new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
			    }
		}
		
}
