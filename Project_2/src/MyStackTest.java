 
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyStackTest {
	public MyStack<String> stringS;
	public String a="a", b="b", c="c", d="d", e="e", f="f";
	public ArrayList<String> fill = new ArrayList<String>();
	
	// STUDENT: student tests will use the doubleS
	public MyStack<Double> doubleS;
	// STUDENT: add variables as needed for your student tests
	public Double pi = 3.14, euler = 2.72, phi = 1.62,
			      gamma = 0.58, sqrt = 1.41, half = 0.5;
	public ArrayList<Double> numFill = new ArrayList<>();
	
	@Before
	public void setUp() throws Exception {
		stringS = new MyStack<String>(5);
		stringS.push(a);
		stringS.push(b);
		stringS.push(c);
		
		//STUDENT: add setup for doubleS for student tests
		doubleS = new MyStack<Double>(5);
		doubleS.push(pi);
		doubleS.push(euler);
		doubleS.push(phi);
	}

	@After
	public void tearDown() throws Exception {
		stringS = null;
		doubleS = null;
	}

	@Test
	public void testIsEmpty() {
		try {
			assertEquals(false,stringS.isEmpty());
			stringS.pop();
			stringS.pop();
			stringS.pop();
			assertEquals(true, stringS.isEmpty());
		
		} catch (StackUnderflowException e) {
			System.out.println(e.getMessage());
			assertTrue("Test shouldn't throw exception", true);
		}
	}

	@Test
	public void testIsFull() {
		try {
			assertEquals(false, stringS.isFull());
			stringS.push(d);
			stringS.push(e);
			assertEquals(true, stringS.isFull());
		
		} catch (StackOverflowException e) {
			System.out.println(e.getMessage());
			assertTrue("Test shouldn't throw exception", false);
		}
	}

	@Test
	public void testPop() {
		try {
			assertEquals(c, stringS.pop());
			assertEquals(b, stringS.pop());
			assertEquals(a, stringS.pop());
			// Stack is empty, next statement should cause StackUnderFlowException
			stringS.pop();
			assertTrue("This should have caused an StackUnderflowException", false);
		}
		catch (StackUnderflowException e){
			assertTrue("This should have caused an StackUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackUnderflowException", false);
		}
	}

	@Test
	public void testPopStudent() {
		// Use the doubleS for student tests
		try {
			assertEquals(phi, doubleS.pop());
			assertEquals(euler, doubleS.pop());
			assertEquals(pi, doubleS.pop());
			// Stack is empty, next statement should cause StackUnderflowException
			doubleS.pop();
			assertTrue("This should have caused an StackUnderflowException", false);
		
		} catch (StackUnderflowException e) {
			assertTrue("This should have caused an StackUnderflowException", true);
		
		} catch (Exception e) {
			assertTrue("This should have caused an StackUnderflowException", false);
		}
	}
	
	@Test
	public void testTop() {
		try {
			assertEquals(c, stringS.top());
			stringS.push(d);
			assertEquals(d, stringS.top());
			stringS.pop();
			stringS.pop();
			assertEquals(b, stringS.top());		
		
		} catch (StackOverflowException | StackUnderflowException e) {
			System.out.println(e.getMessage());
			assertTrue("Test shouldn't throw exception", false);
		}
	}

	@Test
	public void testSize() {
		try {
			assertEquals(3, stringS.size());
			stringS.push(d);
			assertEquals(4, stringS.size());
			stringS.pop();
			stringS.pop();
			assertEquals(2, stringS.size());
		
		} catch (StackOverflowException | StackUnderflowException e) {
			System.out.println(e.getMessage());
			assertTrue("Test shouldn't throw exception", false);
		}
	}

	@Test
	public void testPush() {
		try {
			assertEquals(3, stringS.size());
			assertEquals(true, stringS.push(d));
			assertEquals(4, stringS.size());
			assertEquals(true, stringS.push(e));
			assertEquals(5, stringS.size());
			//Queue is full, next statement should cause QueueOverFlowException
			stringS.push(f);
			assertTrue("This should have caused an StackOverflowException", false);
		}
		catch (StackOverflowException e){
			assertTrue("This should have caused an StackOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackOverflowException", false);
		}
	}

	@Test
	public void testPushStudent() {
		//Use the doubleS for student tests
		try {
			assertEquals(3, doubleS.size());
			assertEquals(true, doubleS.push(gamma));
			assertEquals(4, doubleS.size());
			assertEquals(true, doubleS.push(sqrt));
			assertEquals(5, doubleS.size());
			// Stack is full, next statement should cause StackOverFlowException
			doubleS.push(half);
			assertTrue("This should have caused an StackOverflowException", false);
		}
		catch (StackOverflowException e){
			assertTrue("This should have caused an StackOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackOverflowException", false);
		}
	}
	
	@Test
	public void testToString() {
		try {
			assertEquals("abc", stringS.toString());
			stringS.push(d);
			assertEquals("abcd", stringS.toString());
			stringS.push(e);
			assertEquals("abcde", stringS.toString());
		
		} catch (StackOverflowException e) {
			System.out.println(e.getMessage());
			assertTrue("Test shouldn't throw exception", false);
		}
	}

	@Test
	public void testToStringStudent() {
		//Use the doubleS for student tests
		try {
			assertEquals("3.14; 2.72; 1.62", doubleS.toString("; "));
			doubleS.push(gamma);
			assertEquals("3.14; 2.72; 1.62; 0.58", doubleS.toString("; "));
			doubleS.push(sqrt);
			assertEquals("3.14; 2.72; 1.62; 0.58; 1.41", doubleS.toString("; "));
		
		} catch (StackOverflowException e) {
			System.out.println(e.getMessage());
			assertTrue("Test shouldn't throw exception", false);
		}
	}
	
	@Test
	public void testToStringDelimiter() {
		try {
			assertEquals("a%b%c", stringS.toString("%"));
			stringS.push(d);
			assertEquals("a&b&c&d", stringS.toString("&"));
			stringS.push(e);
			assertEquals("a/b/c/d/e", stringS.toString("/"));
		
		} catch (StackOverflowException e) {
			System.out.println(e.getMessage());
			assertTrue("Test shouldn't throw exception", false);
		}
	}

	@Test
	public void testFill() {
		try {
			fill.add("apple");
			fill.add("banana");
			fill.add("carrot");
			//start with an empty queue
			stringS = new MyStack<String>(5);
			//fill with an ArrayList
			stringS.fill(fill);
			assertEquals(3,stringS.size());
			assertEquals("carrot", stringS.pop());
			assertEquals("banana", stringS.pop());
			assertEquals("apple", stringS.pop());
		
		} catch (StackOverflowException | StackUnderflowException e) {
			System.out.println(e.getMessage());
			assertTrue("Test shouldn't throw exception", false);
		}
	}

}
