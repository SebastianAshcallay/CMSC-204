import java.util.ArrayList;

/**
 * This is an array-based queue implementation.
 * @author sebastianashcallay
 *
 * @param <T> data type
 */
public final class MyQueue<T> implements QueueInterface<T> {
	
	private T[] queue; // Circular array of queue entries and one unused element
	private int frontIdx;
	private int backIdx;
	// private int added; 
	// private int removed;
	private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	/**
	 * No-arg constructor: ArrayQueue()
	 * Initializes array-based queue with default capacity.
	 */
	public MyQueue() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Parameterized constructor: ArrayQueue()
	 * Initializes array-based queue with custom capacity.
	 * @param initialCapacity
	 */
	public MyQueue(int initialCapacity) {
		integrityOK = false; // uninitialized queue
		checkCapacity(initialCapacity);
		
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempQueue = (T[])new Object[initialCapacity + 1];
		queue = tempQueue;
		frontIdx = 0;
		backIdx = initialCapacity;
		
		integrityOK = true; // initialized queue
	}
	

	@Override
	public boolean isEmpty() {
		return frontIdx == ((backIdx + 1) % queue.length);
	}

	@Override
	public boolean isFull() {
		return frontIdx == ((backIdx + 2) % queue.length);
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		checkIntegrity();
		if (isEmpty())
			throw new QueueUnderflowException();
		else {
			T front = queue[frontIdx];
			queue[frontIdx] = null;
			frontIdx = (frontIdx + 1) % queue.length;
			// removed++;
			return front;
		}
	}

	@Override
	public int size() {
		
		if (isEmpty())
			return 0;
		else if (isFull())
			return queue.length - 1;
		else
			return (frontIdx > backIdx) ? (queue.length - frontIdx + backIdx + 1) : (backIdx - frontIdx + 1);
		
		/*
		 * return (added - removed);
		 * 
		 * This alternative method makes it simpler to count the number of elements in a queue. However, I will stick
		 * with the method I implemented since it doesn't involve adding extra data members to my queue.
		 */
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		// TODO Auto-generated method stub
		checkIntegrity();
		
		// If array is full, throw QueueOverflowException
		if (frontIdx == ((backIdx + 2) % queue.length))
			throw new QueueOverflowException();
		
		// Else, add element to back of queue
		backIdx = (backIdx + 1) % queue.length;
		queue[backIdx] = e;
		// added++;
		return true;
	}
	
	@Override
	public String toString() {
		return toString("");
	}

	@Override
	public String toString(String delimiter) {
		String queueStr = "";
		
		// Scan through queue, add elements
		int i = frontIdx; // starting point
		while (i <= backIdx) {
			if (i != backIdx)
				queueStr += (queue[i].toString() + delimiter);
			else
				queueStr += queue[i].toString();
			
			if (frontIdx == (queue.length - 1))
				i = 0;
			else
				i++;	
		}
		
		return queueStr;
	}

	@Override
	public void fill(ArrayList<T> list) throws QueueOverflowException {
		if (!isEmpty() || isFull())
			throw new QueueOverflowException();
		else {
			/* This method requires the first element in the ArrayList to be 
			 * the first element of the Queue. In other words, the first
			 * element we must add onto the queue must be the first element in
			 * the ArrayList. Therefore the queue must be empty prior to addition.
			 */
			
			checkIntegrity(); // checks that the stack is not corrupted
			checkCapacity(list.size()); // checks if size is appropriate
			
			// Deep Copy of ArrayList
			ArrayList<T> newList = new ArrayList<>();
			newList.addAll(list);
			
			// Add from ArrayList to stack
			for (T element: newList)
				enqueue(element);
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
