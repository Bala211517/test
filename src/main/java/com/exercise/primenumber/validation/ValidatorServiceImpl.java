package com.exercise.primenumber.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

/**
 * ValidatorServiceImpl validates against the input parameter and returns
 * {@code ValidationResult}. The reason is set if the validation fails.
 * ValidatorServiceImpl is thread safe.
 *
 */
@Component("validatorService")
public class ValidatorServiceImpl implements ValidatorService{
	private Logger logger = LoggerFactory.getLogger(ValidatorServiceImpl.class);
	
	private final ValidationResult VALIDATION_RESULT_TRUE = new ValidationResult(true);
	private final String INPUT_FORMAT = "Please enter whole number between 0 and " + Integer.MAX_VALUE;

	public ValidationResult validate(String number) {
		if(Strings.isNullOrEmpty(number)){
			StringBuilder builder = new StringBuilder("Given number is null or empty.\n");
			builder.append(INPUT_FORMAT);
			return new ValidationResult(false, builder.toString());
		}
		
		try{
			int parsedInt = Integer.parseInt(number);
			if(parsedInt < 0){
				StringBuilder builder = new StringBuilder("Input number ");
				builder.append(number);
				builder.append(" is invalid.\n");
				builder.append(INPUT_FORMAT);
				return new ValidationResult(false, builder.toString());
			}
		} catch(NumberFormatException e){
			logger.error("NumberFormatException while parsing {}. {}", number, e);
			StringBuilder builder = new StringBuilder("Format exception while parsing the input: ");
			builder.append(number);
			builder.append(".\n");
			builder.append(INPUT_FORMAT);
			return new ValidationResult(false, builder.toString());
		}
		return VALIDATION_RESULT_TRUE;
	}

}
