package com.employee.entity;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.pl.REGON;

import com.employee.validation.NameConstraint;
import com.employee.validation.SalaryConstraint;

import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Employee {
	private long empId;
	
	//@NotBlank(message="Name can not be blank")
	//@NameConstraint
	private String name;
	
	//@NotNull(message="Email can not be null")
	@Email(message="Invalid Email address")
	private String mailId;
	
	//@SalaryConstraint
	//@Pattern(regex = "[0-9]+")
	//@RegExp("[0-9]+")   
	
	private double salary;

	public Employee() {
		
	}
	
	public Employee(Long empId,String name, String mailId, double salary) {
		this.empId=empId;
		this.name=name;
		this.mailId=mailId;
		this.salary=salary;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"empId=" + empId +
				", name='" + name + '\'' +
				", mailId='" + mailId + '\'' +
				", salary=" + salary +
				'}';
	}

	@Id
	@GeneratedValue
	public long getEmpId() {
		return empId;
	}
	
	public void setEmpId(long l) {
		this.empId=l;
	}
	
	@Column(name="name")
	public String getName() {
		if(name==null) {
			this.name="";
		}
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	@Column(name="emailId")
	public String getMailId() {
		return mailId;
	}
	
	public void setMailId(String mailId) {
		this.mailId=mailId;
	}
	
	@Column(name="salary")
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary=salary;
	}
	
}
