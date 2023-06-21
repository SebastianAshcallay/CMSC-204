/**
 * Exception class. THrown when a dequeue method is called on an empty queue.
 * @author sebastianashcallay
 *
 */
public class QueueUnderflowException extends Exception {
	
	/**
	 * No-arg constructor: QueueUnderflowException()
	 * QueueUnderflowException created with default message.
	 */
	public QueueUnderflowException() {
		this("Cannot perform method on an empty queue.");
	}
	
	/**
	 * Parameterized constructor: QueueUnderflowException()
	 * QueueUnderflowException created with custom message.
	 * @param error: Custom error message
	 */
	public QueueUnderflowException(String error) {
		super(error);
	}

}
