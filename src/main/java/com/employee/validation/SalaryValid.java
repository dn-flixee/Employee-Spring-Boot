package com.employee.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SalaryValid implements ConstraintValidator<SalaryConstraint, String>{

	@Override
	public void initialize(SalaryConstraint constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(String salary, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(salary!=null) {
			try {
				double sal=Double.parseDouble(salary);
				return true;
			}catch(NumberFormatException e) {
				return false;
			}
		}
		else
			return true;		
	}

}
