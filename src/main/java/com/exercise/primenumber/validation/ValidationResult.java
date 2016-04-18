package com.exercise.primenumber.validation;

public class ValidationResult {

	private boolean isValid;
	private String reason;

	public ValidationResult(boolean isValid) {
		this.isValid = isValid;
	}
	
	public ValidationResult(boolean isValid, String reason) {
		this.reason = reason;
	}

	public boolean isValid() {
		return isValid;
	}

	public String getReason() {
		return reason;
	}
	
}
