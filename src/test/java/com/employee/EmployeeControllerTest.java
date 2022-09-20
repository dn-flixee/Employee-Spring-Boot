package com.employee;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.employee.controller.EmployeeController;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class)
public class EmployeeControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeService employeeService;

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void createEmployeeTest() throws Exception {

		Employee employee = new Employee();
		employee.setEmpId(1);
		employee.setName("Ramesh");
		employee.setMailId("ramesh@abc.com");
		employee.setSalary(1234.56);

		Mockito.when(employeeService.createEmployee(Mockito.any(Employee.class))).thenReturn(employee);

		mockMvc.perform(MockMvcRequestBuilders.post("/createEmployee").content(asJsonString(employee))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}
	
	@Test
	public void getEmployeeTest() throws Exception {
		
		Employee employee = new Employee();
		employee.setEmpId(1L);
		employee.setName("Ramesh");
		employee.setMailId("ramesh@abc.com");
		employee.setSalary(1234.56);

		Mockito.when(employeeService.getEmployeeById(Mockito.anyInt())).thenReturn(employee);

		mockMvc.perform(MockMvcRequestBuilders.get("/employee/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void updateEmployeeTest() throws Exception{
		
		Employee employee = new Employee();
		employee.setEmpId(1);
		employee.setName("Ramesh");
		employee.setMailId("ramesh@abc.com");
		employee.setSalary(1234.56);
		
		Mockito.when(employeeService.updateEmployee(Mockito.any(Employee.class),Mockito.any(Employee.class))).thenReturn(employee.getName());
		
		mockMvc.perform(MockMvcRequestBuilders.put("/updateEmployee").content(asJsonString(employee))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void deleteEmployeeTest() throws Exception{
		
		Employee employee = new Employee();
		employee.setEmpId(1);
		employee.setName("Ramesh");
		employee.setMailId("ramesh@abc.com");
		employee.setSalary(1234.56);
		
		Mockito.when(employeeService.deleteEmployeeById(Mockito.anyInt())).thenReturn("Employee is deleted");
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteEmployee/1"))
		.andExpect(status().isOk());
	}
}
