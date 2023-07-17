import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test Class for CourseDBManager
 * ** STUDENT IMPLEMENTATION **
 */
public class CourseDBManager_STUDENT_Test {
	
	private CourseDBManagerInterface manager = new CourseDBManager();

	@Before
	public void setUp() throws Exception {
		manager = new CourseDBManager();
	}


	@After
	public void tearDown() throws Exception {
		manager = null;
	}
	
	/**
	 * Test for the add method
	 */
	@Test
	public void testAddToDB() {
		try {
			manager.add("CMSC201",40000,3,"TE200","Josh Hartinger");
			manager.add("CMSC201",40001,3,"TE210","Luna Beringer");
			manager.add("CMSC201",40002,3,"TE220","Mike Rowan");
			manager.add("CMSC203",40003,4,"TE230","Elizabeth Shining");
			manager.add("CMSC203",40004,4,"TE240","Roy Keane");
			manager.add("CMSC203",40005,4,"TE250","Isabel De la Puente");
			manager.add("CMSC204",40006,4,"SW200","Jonathan Webb");
			
			ArrayList<String> myList = manager.showAll();
			assertEquals(myList.get(0),"\nCourse:CMSC201 CRN:40000 Credits:3 Instructor:Josh Hartinger Room:TE200");
			assertEquals(myList.get(6),"\nCourse:CMSC204 CRN:40006 Credits:4 Instructor:Jonathan Webb Room:SW200");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	
	/**
	 * Test for the showAll method
	 */
	@Test
	public void testShowAll() {
		manager.add("CMSC203",40005,4,"TE250","Isabel De La Puente");
		manager.add("CMSC201",40002,3,"TE220","Mike Rowan");
		manager.add("CMSC203",40004,4,"TE240","Roy Keane");
		ArrayList<String> myList = manager.showAll();
		
		// Shows in ascending order (as seen in output examples from instructions)
	 	assertEquals(myList.get(0),"\nCourse:CMSC201 CRN:40002 Credits:3 Instructor:Mike Rowan Room:TE220");
		assertEquals(myList.get(1),"\nCourse:CMSC203 CRN:40004 Credits:4 Instructor:Roy Keane Room:TE240");
		assertEquals(myList.get(2),"\nCourse:CMSC203 CRN:40005 Credits:4 Instructor:Isabel De La Puente Room:TE250");
	}
	
	/**
	 * Test for the read method
	 */
	@Test
	public void testRead() {
		try {
			File inputFile = new File("TestStudent.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("# This is a comment and should not be taken into account by the method");
			inFile.println("CMS025 30500 3 SC200 John Brown"); // Invalid CourseID
			inFile.println("CMS025 3050 3 SC200 John Brown"); // Invalid CRN
			inFile.println("CMS025 30500 30000 SC200 John Brown"); // Invalid Credits
			inFile.println("CMS025 30500 3 SC200 John2Brown"); // Invalid Name
			inFile.println("CMSC205 30500 3 SC200 John Brown");
			inFile.print("CMSC204 40002 3 TE220 Mike Rowan");
			
			inFile.close();
			manager.readFile(inputFile);
			
			ArrayList<String> myList = manager.showAll();
			assertEquals(2, myList.size());
			
			
			assertEquals("CMSC204",manager.get(40002).getID());
			assertEquals("SC200",manager.get(30500).getRoomNum());
		} catch (Exception e) {
			fail("Should not have thrown an exception");
		}
	}
}
