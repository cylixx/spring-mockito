/*
	Integration test
	
*/
package com.softland.spring.mockito.integrationtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softland.spring.mockito.api.model.Employee;
import com.softland.spring.mockito.api.model.Response;
import com.softland.spring.mockito.api.service.EmployeeService;
import com.softland.spring.mockito.api.service.impl.StockService;

//@RunWith(SpringRunner.class)
// SpringExtension This extension causes the initialization of part of the Spring context.
@ExtendWith(SpringExtension.class)
//The MockMvc requires @AutoConfigureMockMvc to be auto configured.
@AutoConfigureMockMvc
// @SpringBootTest meaning that the full application context of the Spring Boot application will be created for this test. 
//@SpringBootTest
// @WebMvcTest to start a sliced Spring context that includes only beans that are relevant for our web-layer (Spring MVC Components).
@WebMvcTest
class SpringMockitoNoPersistDbTest {
	
	@MockBean  //@MockBean replace a bean that already exists in the application context with a mock.
	private EmployeeService employeeService;
	
	@MockBean
	private StockService stockService;

	@Autowired
    private MockMvc mockMvc;

	ObjectMapper om = new ObjectMapper();

	@Test
	public void addEmployeeTest() throws Exception {
		Employee employee = new Employee();
		employee.setName("Marco");
		employee.setDept("IT");
		String jsonRequest = om.writeValueAsString(employee);
		
		
		Employee marco = new Employee(1, "Marco", "IT");
		Mockito.when(employeeService.add(employee)).thenReturn(marco); 
		
		MvcResult result = mockMvc
				.perform(post("/employee").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(jsonPath("$.name").value("Marco"))
				.andReturn();
//		String resultContent = result.getResponse().getContentAsString(); //este sale sobrando
		//esto ya es redundante porque arriba ya esta validando el estatus
		Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value()); 

	}
	
	@Test
	public void getByIdTest() throws Exception {
		Mockito.when(employeeService.findById(1)).thenReturn(Optional.of(new Employee(1, "Matilda", "Web")));
		mockMvc.perform(get("/employee/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value("Matilda"));
	}
	
	
	
	/*
	@Test
	public void getEmployeeTest() throws Exception {
		MvcResult result = mockMvc
				.perform(get("/employee").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
//				.andExpect(model().size(1))
//				.andExpect(model().attributeExists("name"))
				.andReturn();
//		String resultContent = result.getResponse().getContentAsString();
		//esto ya es redundante porque arriba ya esta validando el estatus
		Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value()); 

	}
	
	@Test
	public void addEmployeeTest2() throws Exception {
		Employee employee = new Employee();
		employee.setName("Marco");
		employee.setDept("IT");
		String jsonRequest = om.writeValueAsString(employee);
		MvcResult result = mockMvc
				.perform(post("/employee/add2").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String resultContent = result.getResponse().getContentAsString();
		Response response = om.readValue(resultContent, Response.class);
		Assert.assertTrue(response.isSuccess() == Boolean.TRUE);
	}
	*/
	
}
