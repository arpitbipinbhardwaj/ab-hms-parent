package com.ab.hms.common.customconstraint;

import javax.validation.ConstraintValidator;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidatorContext;


public class DateFormatValidator implements
ConstraintValidator<ValidDate, String>{
	
public void initialize(ValidDate validdate) {
		
	}

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		
		String regex = "^(3[01]|[12][0-9]|0[1-9])-(1[0-2]|0[1-9])-[0-9]{4}$";
		Pattern datePattern = Pattern.compile(regex);
		return (datePattern.matcher(value).matches());
	}
}
