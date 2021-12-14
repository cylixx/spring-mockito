/*
	Mocking With @MockBean

*/
package com.softland.spring.mockito.integrationtest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.softland.spring.mockito.SpringMockitoApplication;
import com.softland.spring.mockito.api.model.Employee;
import com.softland.spring.mockito.api.repository.EmployeeRepository;
import com.softland.spring.mockito.api.service.EmployeeService;
import com.softland.spring.mockito.api.service.impl.EmployeeServiceImpl;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = SpringMockitoApplication.class)
public class MockBeanTests {
	
	@TestConfiguration
	static class EmployeeServiceImplIntegrationTest {
		
		@Bean
		public EmployeeService employeeService() {
			return new EmployeeServiceImpl();
		}
	}
	
	@Autowired
	private EmployeeService employeeService;
	
	@MockBean
	private EmployeeRepository repository;
	
//	@Before
//	public void setUp() {
//		Employee marco = new Employee(1, "Marco", "IT");
//		
//		Mockito.when(repository.findById(1)).thenReturn(Optional.of(marco)); 
//	}
	
	@Test
	public void whenValidId_thenEmployeeShouldFound() {
		Employee marco = new Employee(1, "Marco", "IT");
		Mockito.when(repository.findById(1)).thenReturn(Optional.of(marco)); 
		
		String expectedName = "Marco"; 
		Optional<Employee> found = employeeService.findById(1);
		assertThat(found.get().getName()).isEqualTo(expectedName);
	}

}
