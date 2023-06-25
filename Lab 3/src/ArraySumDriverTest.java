/**
 * Test class for recursive methods: sumOfArray and fibonacci.
 * @author Sebastian Ashcallay
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArraySumDriverTest {

	public final Integer[] numArray = {5, 45, 89, 33, 2, 2, 44, 16};
	public final int[] fibNumbers = {0,1,1,2,3,5,8,13,21,34,55};
	
	@BeforeEach
	public void setUp() throws Exception {
	} 

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testSumOfArray() {
		try {
			
			assertEquals(172, ArraySumDriver.sumOfArray(numArray, 3));
			assertEquals(5, ArraySumDriver.sumOfArray(numArray, 0));
			assertEquals(176, ArraySumDriver.sumOfArray(numArray, 5));
			assertEquals(236, ArraySumDriver.sumOfArray(numArray, 7));
			
		} catch (Exception e) {
			assertEquals("This method shouldn't throw an exception", false);
		}
	}

	@Test
	public void testFibonacci() {
		try {
			
			for (int i = 0; i < 11; i++)
				assertEquals(fibNumbers[i], ArraySumDriver.fibonacci(i));
			
		} catch (Exception e) {
			assertEquals("This method shouldn't throw an exception", false);
		}
	}

}
