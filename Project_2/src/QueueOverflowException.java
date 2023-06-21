/**
 * Exception class. Thrown when an enqueue method is called on a full queue.
 * @author sebastianashcallay
 *
 */
public class QueueOverflowException extends Exception {
	
	/**
	 * No-arg constructor: QueueOverflowException()
	 * QueueOverflowException created with default message.
	 */
	public QueueOverflowException() {
		this("Cannot perform method on a full queue.");
	}
	
	/**
	 * Parameterized constructor: QueueOverflowException()
	 * QueueOverflowException created with custom message.
	 * @param error: Custom error message
	 */
	public QueueOverflowException(String error) {
		super(error);
	}

}
