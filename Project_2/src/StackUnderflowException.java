/**
 * Exception class. Thrown when a top or pop method is called on an empty stack.
 * @author sebastianashcallay
 *
 */
public class StackUnderflowException extends Exception {
	
	/**
	 * No-arg constructor: StackUnderflowException()
	 * StackUnderflowException created with default message.
	 */
	public StackUnderflowException() {
		this("Cannot perform method on an empty stack.");
	}
	
	/**
	 * Parameterized constructor: StackUnderflowException()
	 * StackUnderflowException created with custom message.
	 * @param error: Custom error message
	 */
	public StackUnderflowException(String error) {
		super(error);
	}
}
