/**
 * This class implements two recursive methods: sumOfArray and fibonacci.
 * @author Sebastian Ashcallay
 *
 */
public class ArraySumDriver {
	private final static int ARRAY_SIZE = 6;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int index = 0;

		Integer[] myArray = new Integer[ARRAY_SIZE];
		
		myArray[index++] = 13;
		myArray[index++] = 5;
		myArray[index++] = 12;
		myArray[index++] = 6;
		
		int sum = sumOfArray(myArray, 3);
		System.out.println(sum);
		
		myArray[index++] = 7;
		myArray[index++] = 1;
		
		sum = sumOfArray(myArray, 5);
		System.out.println(sum);
		
		
		int fib = fibonacci(10);
		System.out.print(fib);
		
	}
	
	/**
	 * Recursive method for generating sum of values in array
	 * @param arr array of Integers
	 * @param num index of array to sum all previous index values (including num)
	 * @return sum of array values
	 */
	public static int sumOfArray(Integer[] arr, int num) {
		// Implement your code here
		if (num == 0)
			return arr[0];
		else
			return arr[num] + sumOfArray(arr, --num);
	}
	
	/**
	 * Recursive method to compute a Fibonacci number using the
	 * dynamic programming version.
	 * @param n: Nth element of Fibonacci series function Fib(n).
	 */
	public static int fibonacci(int n) {
		
		int[] value = new int[n + 1];
		
		// Base cases: fib(0) = 0 and fib(1) = 1
		if (n == 0)
			return 0;
		
		if (n == 1)
			return 1;
		
		if (value[n] != 0)
			return value[n];
		
		if (value[n - 1] != 0) {
			value[n] = value[n - 1] + value[n - 2];
			return value[n];
		
		} else {
			
			// Recursive cases: fib(n) = fib(n -1) + fib(n - 2), n > 2
			value[n - 1] = fibonacci(n - 1);
			value[n - 2] = fibonacci(n - 2);
			value[n] = value[n - 1] + value[n - 2];
			return value[n];
		}
			
			
	}
}