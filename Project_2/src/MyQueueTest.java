 
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyQueueTest {
	public MyQueue<String> stringQ;
	public String a="a", b="b", c="c", d="d", e="e", f="f";
	public ArrayList<String> fill = new ArrayList<String>();
	
	// STUDENT: student tests will use the doubleQ
	public MyQueue<Double> doubleQ;
	// STUDENT: add variables as needed for your student tests
	public Double pi = 3.14, euler = 2.72, phi = 1.62,
		      gamma = 0.58, sqrt = 1.41, half = 0.5;
	public ArrayList<Double> numFill = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		stringQ = new MyQueue<String>(5);
		stringQ.enqueue(a);
		stringQ.enqueue(b);
		stringQ.enqueue(c);
		
		//STUDENT: add setup for doubleQ for student tests
		doubleQ = new MyQueue<Double>(5);
		doubleQ.enqueue(pi);
		doubleQ.enqueue(euler);
		doubleQ.enqueue(phi);
	}

	@After
	public void tearDown() throws Exception {
		stringQ = null;
		doubleQ = null;
	}

	@Test
	public void testIsEmpty() {
		try {
			assertEquals(false,stringQ.isEmpty());
			stringQ.dequeue();
			stringQ.dequeue();
			stringQ.dequeue();
			assertEquals(true, stringQ.isEmpty());
		
		} catch (QueueUnderflowException e) {
			System.out.println(e.getMessage());
			assertTrue("Test shouldn't throw exception", true);
		}
	}

	@Test
	public void testDequeue() {
		try {
			assertEquals(a, stringQ.dequeue());
			assertEquals(b, stringQ.dequeue());
			assertEquals(c, stringQ.dequeue());
			//Queue is empty, next statement should cause QueueUnderFlowException
			stringQ.dequeue();
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
		catch (QueueUnderflowException e){
			assertTrue("This should have caused an QueueUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
	}
	
	@Test
	public void testDequeueStudent() {
		// Use the doubleQ for student tests
		try {
			assertEquals(pi, doubleQ.dequeue());
			assertEquals(euler, doubleQ.dequeue());
			assertEquals(phi, doubleQ.dequeue());
			// Queue is empty, next statement should cause QueueUnderFlowException
			doubleQ.dequeue();
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
		catch (QueueUnderflowException e){
			assertTrue("This should have caused an QueueUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueUnderflowException", false);
		}
	}

	@Test
	public void testSize() {
		try {
			assertEquals(3, stringQ.size());
			stringQ.enqueue(d);
			assertEquals(4, stringQ.size());
			stringQ.dequeue();
			stringQ.dequeue();
			assertEquals(2, stringQ.size());
		
		} catch (QueueOverflowException | QueueUnderflowException e) {
			System.out.println(e.getMessage());
			assertTrue("Test shouldn't throw exception", true);
		}
	}

	@Test
	public void testEnqueue() {
		try {
			assertEquals(3, stringQ.size());
			assertEquals(true, stringQ.enqueue(d));
			assertEquals(4, stringQ.size());
			assertEquals(true, stringQ.enqueue(e));
			assertEquals(5, stringQ.size());
			//Queue is full, next statement should cause QueueOverFlowException
			stringQ.enqueue(f);
			assertTrue("This should have caused an QueueOverflowException", false);
		}
		catch (QueueOverflowException e){
			assertTrue("This should have caused an QueueOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueOverflowException", false);
		}
	}

	@Test
	public void testEnqueueStudent() {
		// Use the doubleQ for student tests
		try {
			assertEquals(3, doubleQ.size());
			assertEquals(true, doubleQ.enqueue(gamma));
			assertEquals(4, doubleQ.size());
			assertEquals(true, doubleQ.enqueue(sqrt));
			assertEquals(5, doubleQ.size());
			//Queue is full, next statement should cause QueueOverFlowException
			doubleQ.enqueue(half);
			assertTrue("This should have caused an QueueOverflowException", false);
		}
		catch (QueueOverflowException e){
			assertTrue("This should have caused an QueueOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an QueueOverflowException", false);
		}
	}

	@Test
	public void testIsFull() {
		try {
			assertEquals(false, stringQ.isFull());
			stringQ.enqueue(d);
			stringQ.enqueue(e);
			assertEquals(true, stringQ.isFull());
		
		} catch (QueueOverflowException e) {
			System.out.println(e.getMessage());
			assertTrue("Test shouldn't throw exception", true);
		}
	}

	@Test
	public void testToString() {
		try {
			assertEquals("abc", stringQ.toString());
			stringQ.enqueue(d);
			assertEquals("abcd", stringQ.toString());
			stringQ.enqueue(e);
			assertEquals("abcde", stringQ.toString());
		
		} catch (QueueOverflowException e) {
			System.out.println(e.getMessage());
			assertTrue("Test shouldn't throw exception", true);
		}
	}
	
	@Test
	public void testToStringStudent() {
		// Use the doubleQ for student tests
		try {
			assertEquals("3.14; 2.72; 1.62", doubleQ.toString("; "));
			doubleQ.enqueue(gamma);
			assertEquals("3.14; 2.72; 1.62; 0.58", doubleQ.toString("; "));
			doubleQ.enqueue(sqrt);
			assertEquals("3.14; 2.72; 1.62; 0.58; 1.41", doubleQ.toString("; "));
		
		} catch (QueueOverflowException e) {
			System.out.println(e.getMessage());
			assertTrue("Test shouldn't throw exception", false);
		}
	}

	@Test
	public void testToStringDelimiter() {
		try {
			assertEquals("a%b%c", stringQ.toString("%"));
			stringQ.enqueue(d);
			assertEquals("a&b&c&d", stringQ.toString("&"));
			stringQ.enqueue(e);
			assertEquals("a/b/c/d/e", stringQ.toString("/"));
		
		} catch (QueueOverflowException e) {
			System.out.println(e.getMessage());
			assertTrue("Test shouldn't throw exception", true);
		}
	}

	@Test
	public void testFill() {
		try {
			fill.add("apple");
			fill.add("banana");
			fill.add("carrot");
			//start with an empty queue
			stringQ = new MyQueue<String>(5);
			//fill with an ArrayList
			stringQ.fill(fill);
			assertEquals(3,stringQ.size());
			assertEquals("apple", stringQ.dequeue());
			assertEquals("banana", stringQ.dequeue());
			assertEquals("carrot", stringQ.dequeue());	
		
		} catch (QueueOverflowException | QueueUnderflowException e) {
			System.out.println(e.getMessage());
			assertTrue("Test shouldn't throw exception", true);
		}
	}

}
