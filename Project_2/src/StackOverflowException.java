/**
 * Exception class. Thrown when a push method is called on a full stack.
 * @author sebastianashcallay
 *
 */
public class StackOverflowException extends Exception {
	
	/**
	 * No-arg constructor: StackOverflowException()
	 * StackOverflowException created with default message.
	 */
	public StackOverflowException() {
		this("Cannot perform method on a full stack.");
	}
	
	/**
	 * Parameterized constructor: StackOverflowException()
	 * StackOverflowException created with custom message.
	 * @param error: Custom error message
	 */
	public StackOverflowException(String error) {
		super(error);
	}

}
