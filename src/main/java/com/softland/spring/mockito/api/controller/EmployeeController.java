package com.softland.spring.mockito.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.softland.spring.mockito.api.model.Employee;
import com.softland.spring.mockito.api.model.Response;
import com.softland.spring.mockito.api.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee result = employeeService.add(employee);
		return new ResponseEntity<Employee>(result, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> allEmployee() {
		List<Employee> employees = employeeService.findAll();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	
	
	@PostMapping("/add2")
	public Response addEmployee2(@RequestBody Employee employee) {
		Employee result = employeeService.add(employee);
		return new Response(employee.getId() + " inserted", Boolean.TRUE);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getById(@PathVariable Integer id) {
		Optional<Employee> result = employeeService.findById(id);
		return ResponseEntity.of(result);
	}

}
