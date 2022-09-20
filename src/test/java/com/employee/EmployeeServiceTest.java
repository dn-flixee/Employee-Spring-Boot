package com.employee;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.employee.dao.EmployeeDAO;
import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

	@Autowired
	private EmployeeService employeeService; 
	
	@MockBean
	private EmployeeDAO employeeDAO;
	
	@Test
	public void createEmployeeTest() {
		
		Employee employee=new Employee();
		employee.setEmpId(1L);
		employee.setName("Ramesh");
		employee.setMailId("ramesh@abc.com");
		employee.setSalary(1234.56);
		
		Mockito.when(employeeDAO.save(employee)).thenReturn(employee);
		
		assertThat(employeeService.createEmployee(employee)).isEqualTo(employee);
	}
	
	@Test
	public void getEmployeeByIdTest() {
		
		Employee employee=new Employee();
		employee.setEmpId(1);
		employee.setName("Ramesh");
		employee.setMailId("ramesh@abc.com");
		employee.setSalary(1234.56);
		
		Mockito.when(employeeDAO.findById(1L)).thenReturn(Optional.of(employee));
		
		assertThat(employeeService.getEmployeeById(1L)).isEqualTo(employee);
	}
	
	@Test
	public void updateEmployeeTest()  {
		
		Employee employee=new Employee();
		employee.setEmpId(1);
		employee.setName("Ramesh");
		employee.setMailId("ramesh@abc.com");
		employee.setSalary(1234.56);
		
		Mockito.when(employeeDAO.findById(1L)).thenReturn(Optional.of(employee));
		
		employee.setMailId("ramesh@gmail.com");
		
		Mockito.when(employeeDAO.save(employee)).thenReturn(employee);
		
		assertThat(employeeService.updateEmployee(employee,employee)).isEqualTo(employee);
	}
	
	@Test
	public void deleteEmployeeTest() {
		
		Employee employee=new Employee();
		employee.setEmpId(1);
		employee.setName("Ramesh");
		employee.setMailId("ramesh@abc.com");
		employee.setSalary(1234.56);
		
		Mockito.when(employeeDAO.findById(1L)).thenReturn(Optional.of(employee));
		Mockito.when(employeeDAO.existsById(employee.getEmpId())).thenReturn(false);
		
		assertFalse(employeeDAO.existsById(employee.getEmpId()));
	}
}
