/**
 * Exception class. Thrown when a Notation format is incorrect.
 * @author sebastianashcallay
 *
 */
public class InvalidNotationFormatException extends Exception {
	
	/**
	 * No-arg constructor: InvalidNotationFormatException()
	 * InvalidNotationFormatException created with default message.
	 */
	public InvalidNotationFormatException() {
		this("Notation format is incorrect.");
	}
	
	/**
	 * Parameterized constructor: InvalidNotationFormatException()
	 * InvalidNotationFormatException created with custom message.
	 * @param error: Custom error message
	 */
	public InvalidNotationFormatException(String error) {
		super(error);
	}

}
