import java.util.ArrayList;

/**
 * Array-based stack implementation.
 * @author sebastianashcallay
 *
 * @param <T> data type
 */
public final class MyStack<T> implements StackInterface<T> {
	
	private T[] stack; // array-based stack implementation
	private int topIdx; // top of the stack
	private boolean integrityOK;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
	
	/**
	 * No-arg constructor: MyStack()
	 * Uses DEFAULT_CAPACITY as the size of the stack.
	 */
	public MyStack() {
		this(DEFAULT_CAPACITY);
	}
	
	/**
	 * Parameterized constructor: MyStack()
	 * Uses custom capacity as the size of the stack.
	 * @param capacity: Size of the stack assigned by the client
	 */
	public MyStack(int initialCapacity) {
		integrityOK = false; // uninitialized stack
		checkCapacity(initialCapacity);
		
		@SuppressWarnings("unchecked")
		T[] preStack = (T[])new Object[initialCapacity]; // cast is safe because the new array initially contains null entries
		stack = preStack;
		topIdx = -1;
		
		integrityOK = true; // initialized (safe) stack 
	}

	@Override
	public boolean isEmpty() {
		return (topIdx < 0); // topIdx is -1 if it's empty
	}

	@Override
	public boolean isFull() {
		return (topIdx == (stack.length - 1)); // topIdx is one less than the stack capacity
	}

	@Override
	public T pop() throws StackUnderflowException {
		T stackTop = top(); // Retrieve top of the stack
		stack[topIdx] = null; // Remove top of the stack
		topIdx--;
		return stackTop;
	}

	@Override
	public T top() throws StackUnderflowException {
		checkIntegrity();
		if (!isEmpty())
			return stack[topIdx]; // if stack is not empty
		else
			throw new StackUnderflowException(); // if stack is empty
	}

	@Override
	public int size() {
		return (topIdx + 1); // One more than topIdx is the number of elements in the stack
	}

	@Override
	public boolean push(T e) throws StackOverflowException {
		checkIntegrity();
		if (!isFull()) {
			stack[topIdx + 1] = e;
			topIdx++;
			return true;
		
		} else
			throw new StackOverflowException();
	}
	
	@Override
	public String toString() {
		return toString("");
	}

	@Override
	public String toString(String delimiter) {
		String stackStr = "";
		for (int i = 0; i <= topIdx; i++) {
			if (i < topIdx)
				stackStr += (stack[i].toString() + delimiter);
			if (i == topIdx)
				stackStr += stack[i].toString();
		}
		
		return stackStr;
	}

	@Override
	public void fill(ArrayList<T> list) throws StackOverflowException {
		if (!isEmpty() || isFull())
			throw new StackOverflowException();
		else {
			/* This method requires the first element in the ArrayList to be 
			 * the first bottom element of the Stack. In other words, the first
			 * element we must push onto the stack must be the first element in
			 * the ArrayList. Therefore the stack must be empty prior to pushing.
			 */
			
			checkIntegrity(); // checks that the stack is not corrupted
			checkCapacity(list.size()); // checks if size is appropriate
			
			// Deep Copy of ArrayList
			ArrayList<T> newList = new ArrayList<>();
			newList.addAll(list);
			
			// Add from ArrayList to stack
			for (T element: newList)
				push(element);
		}
		
	}
	
	/*
	 * checkCapacity() Method:
	 * Checks capacity of stack.
	 * Throws IllegalStateException
	 */
	private void checkCapacity(int cap) {
		if (cap > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a stack whose capacity exceeds " +
											"allowed maximum of " + MAX_CAPACITY);
	}
	
	/*
	 * checkIntegrity() Method:
	 * Throws an exception if this object is not initialized.
	 * Throws SecurityException
	 */
	private void checkIntegrity() {
		if (!integrityOK)
			throw new SecurityException("MyStack object is corrupt.");
	}

}
