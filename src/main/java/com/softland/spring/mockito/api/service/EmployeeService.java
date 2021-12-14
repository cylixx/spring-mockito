package com.softland.spring.mockito.api.service;

import java.util.List;
import java.util.Optional;

import com.softland.spring.mockito.api.model.Employee;

public interface EmployeeService {

	Employee add(Employee employee);
	Optional<Employee> findById(Integer id);
	List<Employee> findAll();
	
}
