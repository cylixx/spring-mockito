/*
	Integration test
	
*/
package com.softland.spring.mockito.integrationtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softland.spring.mockito.api.model.Employee;
import com.softland.spring.mockito.api.model.Response;

@RunWith(SpringRunner.class)
//The MockMvc requires @AutoConfigureMockMvc to be auto configured.
@AutoConfigureMockMvc
@SpringBootTest
class SpringMockitoApplicationTests {

	@Autowired
    private MockMvc mockMvc;

	ObjectMapper om = new ObjectMapper();

	@Test
	public void addEmployeeTest() throws Exception {
		Employee employee = new Employee();
		employee.setName("Marco");
		employee.setDept("IT");
		String jsonRequest = om.writeValueAsString(employee);
		MvcResult result = mockMvc
				.perform(post("/employee").content(jsonRequest).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Marco"))
				.andReturn();
//		String resultContent = result.getResponse().getContentAsString(); //este sale sobrando
		//esto ya es redundante porque arriba ya esta validando el estatus
		Assert.assertTrue(result.getResponse().getStatus() == HttpStatus.OK.value()); 

	}
	
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
	
}
