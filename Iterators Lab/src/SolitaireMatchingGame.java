import java.util.*;
public class SolitaireMatchingGame {
	
	public static void main(String[] args) {
		
		// Generate random values between 10 and 99
		// Place them in an instance of java.util.ArrayList
		ArrayList<String> numSet = randomNumGenerator(12);
		showNumSet("These are the values on our list:", numSet);
		numSet = removePairs(numSet);
		showNumSet("After removing matching values, the list is:", numSet);
		
		// If all values are removed from the list, you win!
		if (numSet.size() == 0)
			System.out.println("\nYou win!!");
		else
			System.out.println("\nYou lose");
	}
	
	/* 
	 * Returns an ArrayList of randomly-generated Integers 
	 * @param elements: Number of elements in our ArrayList
	 */
	private static ArrayList<String> randomNumGenerator(int elements)  {
		ArrayList<String> returnSet = new ArrayList<>();
		for (int i = 0; i < elements; i++)
			returnSet.add(Integer.toString((int)((Math.random() * (100 - 10)) + 10)));
		
		return returnSet;
	}
	
	/*
	 * Remove pairs that have a first or second-digit match.
	 * @param arr: ArrayList of values
	 */
	private static ArrayList<String> removePairs(ArrayList<String> arr) {
		ListIterator<String> numIterator = arr.listIterator();
		while (numIterator.hasNext()) {
			String num1 = numIterator.next();
			String num2;
			
			if (numIterator.hasNext())
				num2 = numIterator.next();
			else
				break; // Reached the end of the list
			
			
			if ((num1.charAt(0) == num2.charAt(0)) || 
				(num1.charAt(1) == num2.charAt(1))) {
				
				// Remove pair of consecutive integers
				numIterator.remove();
				numIterator.previous();
				numIterator.remove();
				if (numIterator.hasPrevious())
					numIterator.previous(); // Return to last element before match (this will be num1)
				
				// If two elements match at the beginning, num1 will be the following element
			
			} else {
				numIterator.previous(); // Return to last element compared (this will be num1)
			}
		}
		
		return arr;
	}
	
	/*
	 * Shows our list of random (integer) values between 10 and 99
	 * @param arr: ArrayList of values
	 */
	private static void showNumSet(String message, ArrayList<String> arr) {
		System.out.print(message);
		for (String n: arr)
			System.out.print(" " + n);
		System.out.println();
	}
}
