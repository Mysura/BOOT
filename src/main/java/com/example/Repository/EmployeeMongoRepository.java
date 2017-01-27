package com.example.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.model.Employee;

public interface EmployeeMongoRepository extends MongoRepository<Employee, Integer>{
	
	Employee findByName(String name);

}
