package com.employee.controller;

import java.util.List;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dao.EmployeeDAO;
import com.employee.entity.Employee;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.service.EmployeeService;
import com.employee.validator.EmployeeValidation;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeDAO employeeDAO;

//	@PostMapping("/employee")
//	public Employee createEmployee(@Valid @RequestBody Employee employee) {
//		return employeeService.createEmployee(employee);
//	}
	@GetMapping("/")
	public String helloWorld(){
		return  "Welcome";
	}

	@PostMapping("/employee")
	public ResponseEntity<String> createEmployee(@Valid @RequestBody Employee employee) {
		if (EmployeeValidation.createValidation(employee)) {
			employeeService.createEmployee(employee);
			return new ResponseEntity<>("Employee created successfully", HttpStatus.OK);
		} else
			return new ResponseEntity<>("Employee is not created", HttpStatus.BAD_REQUEST);
	}

//	@GetMapping("/employee/{id}")
//	public ResponseEntity<Object> getEmployee(@PathVariable long id) throws EmployeeNotFoundException{
//
//		if(employeeService.getEmployeeById(id)!=null) {
//			Employee employee=employeeService.getEmployeeById(id);
//			return new ResponseEntity<>(employee, HttpStatus.OK);
//		}
//		else
//			throw new EmployeeNotFoundException();
//	}
	

	@GetMapping("/employee/{id}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable long id) throws EmployeeNotFoundException {
		Optional<Employee> employee = Optional.of(employeeService.getEmployeeById(id));
		if (employee.isPresent()) {
			return new ResponseEntity<>(employee, HttpStatus.OK);
		} else
			throw new EmployeeNotFoundException();
	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getEmployees();
	}

	@GetMapping("/employee/{pagenumber}/{pagesize}")
	public Page<Employee> getEmployeesByPagination(@PathVariable int pagenumber, @PathVariable int pagesize) {
		return employeeService.getEmployeesByPagination(pagenumber, pagesize);
	}
	
//	@GetMapping("/employeeSalary/{salary}")
//	public List<Employee> getEmployeeBySalary(@PathVariable Double salary){
//		return employeeService.getEmployeeBySalary(salary);
//	}
	
	@GetMapping("/employees/{name}")
	public List<Employee> getEmployeeByNameContaining(@PathVariable String name){
		return employeeService.getEmployeeByNameContaining(name);
	}

//	@PutMapping("/employee")
//	public ResponseEntity<String> updateEmployee(@Valid @RequestBody Employee employee) throws EmployeeNotFoundException{
//		if(employeeService.getEmployeeById(employee.getEmpId()) != null) {
//			employeeService.updateEmployee(employee, employeeService.getEmployeeById(employee.getEmpId()));
//			return new ResponseEntity<>("Data for "+employee.getName()+" is updated!", HttpStatus.OK);
//		}
//		else
//			throw new EmployeeNotFoundException();
//	}

	@PutMapping("/employee")
	public ResponseEntity<String> updateEmployee(@Valid @RequestBody Employee employee) throws EmployeeNotFoundException{
		if(EmployeeValidation.updateValidation(employee)) {
			if(employeeService.getEmployeeById(employee.getEmpId()) != null) {
				String name=employeeService.updateEmployee(employee, employeeService.getEmployeeById(employee.getEmpId()));
				return new ResponseEntity<>("Data for "+name+" is updated!", HttpStatus.OK);
			}
			else
				throw new EmployeeNotFoundException();
		}else
			return new ResponseEntity<>("Employee has not been updated", HttpStatus.OK);
	}
	
//	@PutMapping("/employee")
//	public ResponseEntity<String> updateEmployee(@Valid @RequestBody Employee employee)
//			throws EmployeeNotFoundException {
//		if (EmployeeValidation.updateValidation(employee)) {
//			Optional<Employee> employee1 = Optional.of(employeeService.getEmployeeById(employee.getEmpId()));
//			if (employee1.isPresent()) {
//				String name = employeeService.updateEmployee(employee, employee1);
//				return new ResponseEntity<>("Data for " + name + " is updated!", HttpStatus.OK);
//			} else
//				throw new EmployeeNotFoundException();
//		} else
//			return new ResponseEntity<>("Employee has not been updated", HttpStatus.OK);
//	}

	@DeleteMapping("/employee/{id}")
	public String deleteEmployee(@PathVariable int id) throws EmployeeNotFoundException {
		if (employeeService.getEmployeeById(id) != null) {
			employeeService.deleteEmployeeById(id);
			return "Employee is deleted";
		} else
			throw new EmployeeNotFoundException();
	}
}
