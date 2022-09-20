package com.employee.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import com.employee.entity.Employee;
import com.employee.exception.ValidationException;

import javax.validation.constraints.Null;

public abstract class EmployeeValidation implements Errors {

	public static boolean createValidation(Employee employee) throws ValidationException {
		
		int flag1=0, flag2=0, flag3=0;

		if (employee.getName() == null || employee.getName().isBlank() || employee.getName().isEmpty()) {
			throw new ValidationException("Name should be entered");
		} else {
			Pattern pattern = Pattern.compile(new String("^[a-zA-Z\\s]*$"));
			Matcher matcher = pattern.matcher(employee.getName());
			if (matcher.matches()) {
				//return true;
				flag1=1;
			} else {
				throw new ValidationException("Name should contain characters only");
			}
		}

		if (employee.getMailId() == null || employee.getMailId().isBlank() || employee.getMailId().isEmpty()) {
			throw new ValidationException("Email id should be entered");
		}else {
			flag2=1;
		}

		if (employee.getSalary() < 0)  {
			throw new ValidationException("Salary should Positive Number");
			// use @notNull for valid
		}
		else {
			flag3=1;
		}
		
		if(flag1==1 && flag2==1 && flag3==1)
			return true;
		else
			return false;
	}

	public static boolean updateValidation(Employee employee) throws ValidationException {
		
		int flag1=0, flag2=0;
		
		if (employee.getName() != null) {
			Pattern pattern = Pattern.compile(new String("^[a-zA-Z\\s]*$"));
			Matcher matcher = pattern.matcher(employee.getName());
			if (matcher.matches()) {
				flag1=1;
			} else {
				throw new ValidationException("Name should contain characters only");
			}
		} else {
			flag1=1;
		}

		if (employee.getSalary() < 0)  {
			throw new ValidationException("Salary should Positive Number");
			// use @notNull for valid
		}
		else {
			flag2=1;
		}

		if(flag1==1 && flag2==1) 
			return true;
		else
			return false;
	}
}
