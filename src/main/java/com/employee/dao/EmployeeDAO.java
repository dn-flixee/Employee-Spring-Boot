package com.employee.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long>, CrudRepository<Employee, Long>{

	//public List<Employee> findBySalaryGreaterThan(Double salary);
	
	public List<Employee> findByNameContaining(String name);
}
