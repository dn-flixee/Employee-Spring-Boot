package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.employee.dao.EmployeeDAO;
import com.employee.entity.Employee;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;
	public Employee createEmployee(Employee employee) {
		return employeeDAO.save(employee);
	}
	
	public Employee getEmployeeById(long id) {
		return employeeDAO.findById(id).orElse(null);
	}

	public List<Employee> getEmployees() {
		return employeeDAO.findAll();
	}

	public Page<Employee> getEmployeesByPagination(int pagenumber, int pagesize) {
		Page<Employee> employee = employeeDAO.findAll(PageRequest.of(pagenumber - 1, pagesize));
		return employee;
	}
	
//	public List<Employee> getEmployeeBySalary(Double salary){
//		return employeeDAO.findBySalaryGreaterThan(salary);
//	}
	
	public List<Employee> getEmployeeByNameContaining(String name){
		return employeeDAO.findByNameContaining(name);
	}

//	public String updateEmployee(Employee employee, Employee oldEmployee) {		
//			oldEmployee.setName(employee.getName());
//			oldEmployee.setMailId(employee.getMailId());
//			oldEmployee.setSalary(employee.getSalary());
//			employeeDAO.save(oldEmployee);
//		return oldEmployee.getName();
//	}
	
	public String updateEmployee(Employee employee, Employee oldEmployee) {
		{
		if(!(employee.getName()==null || employee.getName().isBlank() || employee.getName().isEmpty())) 
			oldEmployee.setName(employee.getName());
		if(!(employee.getMailId()==null || employee.getMailId().isBlank() || employee.getMailId().isEmpty())) 
			oldEmployee.setMailId(employee.getMailId());
		if(!(employee.getSalary() < 0 || employee.getMailId().isBlank() || employee.getMailId().isEmpty()))
			oldEmployee.setSalary(employee.getSalary());
		}
		employeeDAO.save(oldEmployee);
		return oldEmployee.getName();
	}

	public String deleteEmployeeById(long id) {
		employeeDAO.deleteById(id);
		return "Employee is deleted!";
	}

	
}
