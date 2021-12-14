package com.softland.spring.mockito.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softland.spring.mockito.api.model.Employee;
import com.softland.spring.mockito.api.repository.EmployeeRepository;
import com.softland.spring.mockito.api.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;

	@Override
	public Employee add(Employee employee) {
		return repository.save(employee);
	}

	@Override
	public List<Employee> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Employee> findById(Integer id) {
		return repository.findById(id);
	}

}
