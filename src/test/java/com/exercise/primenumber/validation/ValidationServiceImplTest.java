package com.exercise.primenumber.validation;

import org.junit.Assert;
import org.junit.Test;

public class ValidationServiceImplTest {

	ValidatorService validator = new ValidatorServiceImpl();
	
	@Test
	public void testValidateNegativeNumber(){
		ValidationResult validate = validator.validate("-10");
		Assert.assertFalse(validate.isValid());
		
		validate = validator.validate("-2201");
		Assert.assertFalse(validate.isValid());
	}
	
	@Test
	public void testNullOrEmptyInput(){
		ValidationResult validate = validator.validate(null);
		Assert.assertFalse(validate.isValid());
		
		validate = validator.validate("");
		Assert.assertFalse(validate.isValid());
	}
	
	@Test
	public void testInvalidNumber(){
		ValidationResult validate = validator.validate("10aaa");
		Assert.assertFalse(validate.isValid());

		validate = validator.validate("abc");
		Assert.assertFalse(validate.isValid());

		validate = validator.validate(".,376");
		Assert.assertFalse(validate.isValid());
		
		validate = validator.validate("" + (Integer.MAX_VALUE + 1));
		Assert.assertFalse(validate.isValid());
	}

	@Test
	public void testValidNumber(){
		ValidationResult validate = validator.validate("10");
		Assert.assertTrue(validate.isValid());

		validate = validator.validate("" + Integer.MAX_VALUE);
		Assert.assertTrue(validate.isValid());
	}

}
