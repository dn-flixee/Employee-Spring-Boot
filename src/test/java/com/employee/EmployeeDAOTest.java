	package com.employee;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.employee.dao.EmployeeDAO;
import com.employee.entity.Employee;

@DataJpaTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDAOTest {

	@Autowired
	private EmployeeDAO employeeDAO;

	// Save Employee
	@Test
	@Order(1)
	@Rollback(value=false)
	public void saveEmployeeTest() {

		Employee employee = Employee.builder().name("Ramesh").mailId("ramesh@abc.com")
				.salary(1234.56).build();

		employeeDAO.save(employee);

		Assertions.assertThat(employee.getEmpId()).isGreaterThan(0);

	}

	// get Employee
	@Test
	@Order(2)
	public void getEmployeeTest() {

		Employee employee = employeeDAO.findById(1L).get();

		Assertions.assertThat(employee.getEmpId()).isEqualTo(1L);
	}

	// update Employee
	@Test
	@Order(3)
	@Rollback(value=false)
	public void updateEmployeeTest() {
		
		Employee employee=employeeDAO.findById(1L).get();
		
		employee.setMailId("ramesh@gmail.com");
		Employee updatedEmployee = employeeDAO.save(employee);
		
		Assertions.assertThat(updatedEmployee.getMailId()).isEqualTo("ramesh@gmail.com");
	}
	
	//delete Employee
	@Test
	@Order(4)
	@Rollback(value=false)
	public void deleteEmployeeTest() {
		
		Employee employee=employeeDAO.findById(1L).get();
		
		employeeDAO.delete(employee);
		
		Employee employee1=null;
		Assertions.assertThat(employee.equals(employee1));
	}

}
