import java.io.IOException;
import java.util.*;

public class CourseDBStructure implements CourseDBStructureInterface {
	
	private int numberOfEntries;
	private static final int MIN_CAPACITY = 3;
	private static final int MAX_CAPACITY = 10000;
	
	// Hash table with buckets: Separate Chaining
	private LinkedList<CourseDBElement>[] hashTable;
	private int hashTableSize; // Prime: 4k + 3
	private static final int MAX_SIZE = 2 * MAX_CAPACITY;
	private boolean integrityOK = false;
	private static final double LOAD_FACTOR = 1.5;

	/**
	 * Parameterized Constructor:
	 * This constructor takes in an integer that is the estimated number of courses.
	 * @param numOfCourses: Estimated number of courses
	 */
	public CourseDBStructure(int numOfCourses) {
		numOfCourses = checkCapacity(numOfCourses);
		numberOfEntries = 0; // Dictionary is empty	
		
		/* Construct the hash table with buckets:
		 * 1. Determine the size of the hash table by finding a 4K+3 prime just greater than n / loading factor, 
		 *    where n is numOfCourses.
		 * 2. If the size is a 4k+3 prime, move to the next 4k+3 prime.  */
		hashTableSize = getNextPrime((int) Math.round(numOfCourses / LOAD_FACTOR));
		checkSize(hashTableSize); // Check that size is not too large
		
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		LinkedList<CourseDBElement>[] temp = (LinkedList<CourseDBElement>[]) new LinkedList[hashTableSize];
		hashTable = temp;
		integrityOK = true;
	}
	
	/**
	 * Test Constructor:
	 * This constructor is implemented for testing purposes.
	 * @param test: "Testing" string
	 * @param numOfCourses: Hash Table size
	 */
	public CourseDBStructure(String test, int HTsize) {
		checkSize(HTsize); // Check that size is not too large
		hashTableSize = HTsize;
		
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		LinkedList<CourseDBElement>[] temp = (LinkedList<CourseDBElement>[]) new LinkedList[hashTableSize];
		hashTable = temp;
		integrityOK = true;
	}
	
	private int checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException("Attempt to create a dictionary whose capacity exceeds allowed maximum of " + MAX_CAPACITY);
		else if (capacity < 1)
			throw new IllegalStateException("Attempt to create a unoperative dictionary with a capacity of less than 1");
		else if (capacity < MIN_CAPACITY)
			capacity = MIN_CAPACITY;
		
		return capacity;		
	}
	
	private void checkSize(int size) {
		if (size > MAX_SIZE)
			throw new IllegalStateException("Attempt to create a dictionary whose capacity exceeds allowed maximum of " + MAX_SIZE);
	}
	
	private void checkIntegrity() {
		if (!integrityOK)
			throw new SecurityException("Hash Table is corrupt.");
	}
	
	
	private int getNextPrime(int num) {
		
		int k; // Prime Expression: 4k + 3. 
		
		// Determine the next closest number of the form 4k + 3
		k = ((num - 3) / 4) + 1; 
		num = (4 * k) + 3;
		
		while (!isPrime(num)) { // Check if number in the form of 4k + 3 is actually prime
			k++;
			num = (4 * k) + 3;
		}
		
		return num;
	}

	
	private boolean isPrime(int num) {
		// NOTE: The minimum capacity will always be 3. Therefore, the very next 4k+3 prime number would be 7.
		// 		 The prime number 7 is the minimum int argument this method will receive.
		// NOTE: Number of the form 4k + 3 will always be odd
		
		if (num % 3 == 0)
			return false;
	    
		for (int i = 5; i * i <= num; i += 6)
	        if ((num % i == 0) || (num % (i + 2) == 0)) 
	        	return false;
		
		return true;
	}
	
	private int getHashCode(String CRN) {
		/* Generates a hash code by multiplying the Unicode value of each character
		 * by a factor based on the character's position within the string. */
		int hashCode = 0;
		int g = 31; // Positive constant
		
		for (int i = 0; i < CRN.length(); i++)
			hashCode = g * hashCode + CRN.charAt(i);
		
		return hashCode % hashTableSize;
	}
	
	private boolean isHashTableTooFull() {
		return ((double) numberOfEntries / hashTableSize) >= LOAD_FACTOR;
	}
	
	private void resizeHashTable() {
		checkIntegrity();
		LinkedList<CourseDBElement>[] oldTable = hashTable;
		int oldSize = hashTable.length;
		int newSize = getNextPrime(oldSize * 2);
		checkSize(newSize);
		
		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		LinkedList<CourseDBElement>[] temp = (LinkedList<CourseDBElement>[])new LinkedList[newSize];
		hashTable = temp;
		numberOfEntries = 0; // Reset number of dictionary entries, since it will be incremented by add during rehash
		
		// Rehash dictionary entries from old array to the new and bigger array; skip elements that contain null
		for (int i = 0; i < oldSize; i++) {
			if ((oldTable[i] != null)) {
				// Pop all elements in a LinkedList bucket and add them to the new hash table
				for (int j = 0; j < oldTable[i].size(); j++) // j: Number of pops
					add(oldTable[i].pop());
			}
		}
	}
	
	
	@Override
	public void add(CourseDBElement element) {
		checkIntegrity();
		String CRN = String.valueOf(element.getCRN());
		int key = getHashCode(CRN);
		
		
		// If hash table is full, resize the array
		if (isHashTableTooFull())
			resizeHashTable();
		
		// If hash index is null, then enter a new LinkedList with the current element
		if (hashTable[key] == null) {
			hashTable[key] = new LinkedList<>();
			hashTable[key].add(element);
		}
		
		/* Otherwise, consider the next two scenarios:
		 * 1. The hash index has a LinkedList, but not an existing element like the current one.
		 *    In this case, we add the current element to the hash index.
		 * 2. The hash index has a LinkedList, and it has an instance of our current element.
		 *    In this case, we replace it.
		 */
		else {
			
			boolean found = false;
			
			// Search the LinkedList for an instance of the current element
			for (int i = 0; i < hashTable[key].size(); i++) {
				if (element.equals(hashTable[key].get(i))) {
					// If we find an instance of the current element, replace it
					hashTable[key].set(i, element);
					found = true;
				}
			}
			
			// If we did not find an instance of the current element, push it to the LinkedList
			if (found == false)
				hashTable[key].push(element);
		}
		
		numberOfEntries++;

	}

	@Override
	public CourseDBElement get(int crn) throws IOException {
		checkIntegrity();
		String CRN = String.valueOf(crn);
		int key = getHashCode(CRN);
		
		// If hash index is empty, then the element is not in the hash table
		if (hashTable[key] == null)
			throw new IOException("CRN not found in the database.");
		
		
		/* Steps:
		 * 1. Check the LinkedList at the hash index.
		 * 2. Iterate through all the elements until finding an element with the specified CRN.
		 *    Return that element.
		 * 3. If you can't find the element, throw an exception.
		 */
		for (int i = 0; i < hashTable[key].size(); i++) {
			if (hashTable[key].get(i).getCRN() == crn)
				return hashTable[key].get(i);
		}
		
		throw new IOException("CRN not found in the database.");
	}

	@Override
	public ArrayList<String> showAll() {
		
		// Get all CourseDBElements
		List<CourseDBElement> sortedCourses = new ArrayList<>(numberOfEntries);
		for (int i = 0; i < hashTableSize; i++) {
			if (hashTable[i] == null) // Skip unused spaces in the hash table
				continue;
			
			else
				// Iterate through all the elements at a specified hash index
				for (CourseDBElement e: hashTable[i])
					sortedCourses.add(e);
		}
		
		// Sort by CRN number
		Collections.sort(sortedCourses, new CDEComparator());
		// Descending order: Collections.sort(sortedCourses, Collections.reverseOrder());
		
		// Get strings from CourseDBElements
		ArrayList<String> courses = new ArrayList<>();
		for (CourseDBElement cde: sortedCourses)
			courses.add(cde.toString());
		
		return courses;
	}

	@Override
	public int getTableSize() {
		return hashTableSize;
	}
	
	private static class CDEComparator implements Comparator<CourseDBElement> {

		@Override
		public int compare(CourseDBElement o1, CourseDBElement o2) {
			return o1.compareTo(o2);
		}
		
	}

}
