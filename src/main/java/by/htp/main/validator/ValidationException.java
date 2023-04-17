package by.htp.main.validator;

public class ValidationException extends Exception{
	private static final long serialVersionUID = 1L;

	public ValidationException() {
		super();
	}

	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ValidationException(String message) {
		super(message);
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}
    public String[] getValidationErrors(String delimiter) {
    	String[] errors = this.getMessage().split(delimiter);
    	return errors;
    }
}
