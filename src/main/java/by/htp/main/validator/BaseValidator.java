package by.htp.main.validator;

import java.util.List;

public class BaseValidator {
	private final static String DELIMITER = ";";
	protected List<String> errors;

	public BaseValidator() {
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public String buildErrorMessage() {
		StringBuilder errorMessage = new StringBuilder();
		for (String error : this.errors) {
			errorMessage.append(error + DELIMITER);
		}
		errorMessage.deleteCharAt(errorMessage.lastIndexOf(DELIMITER));
		return errorMessage.toString();
	}
}
