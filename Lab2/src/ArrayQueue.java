/**
 * This is an array-based queue implementation.
 * @author sebastianashcallay
 *
 */
public class ArrayQueue<T> implements QueueInterface<T> {
	
	private T[] queue; // Circular array of queue entries and one unused element
	private int frontIndex;
	private int backIndex;
	private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	/**
	 * No-arg constructor: ArrayQueue()
	 * Initializes array-based queue with default capacity.
	 */
	public ArrayQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Parameterized constructor: ArrayQueue()
	 * Initializes array-based queue with custom capacity.
	 * @param initialCapacity
	 */
	public ArrayQueue(int initialCapacity) {
		integrityOK = false;
		checkCapacity(initialCapacity);
		
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[])new Object[initialCapacity + 1];
		queue = tempQueue;
		frontIndex = 0;
		backIndex = initialCapacity;
		integrityOK = true;
	}

	@Override
	public void enqueue(T newEntry) {
		checkIntegrity();
		ensureCapacity();
		backIndex = (backIndex + 1) % queue.length;
		queue[backIndex] = newEntry;
		
	}

	@Override
	public T dequeue() throws EmptyQueueException {
		checkIntegrity();
		if(isEmpty())
			throw new EmptyQueueException();
		else {
			T front = queue[frontIndex];
			queue[frontIndex] = null;
			frontIndex = (frontIndex + 1) % queue.length;
			return front;
		}
	}

	@Override
	public T getFront() throws EmptyQueueException {
		checkIntegrity();
		if(isEmpty())
			throw new EmptyQueueException();
		else
			return queue[frontIndex];
	}

	@Override
	public boolean isEmpty() {
		checkIntegrity();
		return frontIndex == ((backIndex + 1) % queue.length);
	}

	@Override
	public void clear() {
		
		try {
			while (queue[frontIndex] != null) // When the front index is null, the entire queue will be null (clear)
				dequeue();
		
		} catch(EmptyQueueException e) {
			// Our queue is empty, therefore, our queue is clear
		
		} finally {
			// reset index positions
			frontIndex = 0;
			backIndex = queue.length - 1;
		}

	}
	
	/*
	 * Private method: ensureCapacity()
	 * This method doubles the size of the array queue if it is full.
	 * Precondition: checkIntegrity has been called.
	 */
	private void ensureCapacity() {
		if (frontIndex == ((backIndex + 2) % queue.length)) {
			// If array is full, double size of array
			T[] oldQueue = queue;
			int oldSize = oldQueue.length;
			int newSize = oldSize * 2;
			checkCapacity(newSize - 1);
			integrityOK = false;
			
			// The cast is safe because the new array contains null entries
			@SuppressWarnings("unchecked")
			T[] tempQueue = (T[]) new Object[newSize];
			queue = tempQueue;
			
			for (int index = 0; index < oldSize - 1; index++) {
				queue[index] = oldQueue[frontIndex];
				frontIndex = (frontIndex + 1) % oldSize;
			}
			
			frontIndex = 0;
			backIndex = oldSize - 2;
			integrityOK = true;
			
		}
	}
	
	/*
	 * Private method: checkIntegrity()
	 * This method throws an exception if this object is not initialized.
	 */
	private void checkIntegrity() {
		if (!integrityOK)
			throw new SecurityException("ArrayQueue object is corrupt.");
	}
	
	/*
	 * Private method: checkCapacity()
	 * This method throws an exception if the client requests a capacity that is too large.
	 */
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a queue whose capacity exceeds" +
											" allowed maximum of " + MAX_CAPACITY);
	}
}
