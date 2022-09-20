package com.employee.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValid implements ConstraintValidator<NameConstraint, String>{

	@Override
	public void initialize(NameConstraint constraintAnnotation) {
		
	}

//	@Override
//	public boolean isValid(String name, ConstraintValidatorContext context) {
//		// TODO Auto-generated method stub
//		int flag=0;
//		for(int i=0; i<name.length();i++) {
//			if(name.charAt(i)<65 || name.charAt(i)>90 || name.charAt(i)<97 || name.charAt(i)>122) {
//				flag=1;
//				break;
//			}
//		}
//		if(flag==1)
//			return false;
//		else
//			return true;
//	}
	
	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(name!=null) {
			Pattern pattern= Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
			Matcher matcher=pattern.matcher(name);
			if(matcher.matches()) {
				return true;
			}
			else
				return false;
		}
		else
			return true;
	}

}