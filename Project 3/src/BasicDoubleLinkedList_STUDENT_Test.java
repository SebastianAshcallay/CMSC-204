

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BasicDoubleLinkedList_STUDENT_Test {

	BasicDoubleLinkedList<Movie> linkedMovie;
	MovieComparator comparatorMovie;
	
	public Movie a = new Movie("The Shape of Water", 2017);
	public Movie b = new Movie("Parasite", 2019);
	public Movie c = new Movie("Nomadland", 2020);
	public Movie d = new Movie("Moonlight", 2016);
	public Movie e = new Movie("Birdman", 2014);
	public Movie f = new Movie("Green Book", 2018);
	

	public ArrayList<Movie> fill = new ArrayList<Movie>();
	

	@Before
	public void setUp() throws Exception {
		linkedMovie= new BasicDoubleLinkedList<Movie>();
		linkedMovie.addToEnd(b);
		linkedMovie.addToEnd(f);
		comparatorMovie = new MovieComparator();
	}

	@After
	public void tearDown() throws Exception {
		linkedMovie = null;
		comparatorMovie = null; 
	}

	@Test
	public void testGetSize() {
		assertEquals(2,linkedMovie.getSize());
		linkedMovie.addToFront(a);
		assertEquals(3,linkedMovie.getSize());
	}
	
	@Test
	public void testAddToEnd() {
		assertEquals(f,linkedMovie.getLast());
		linkedMovie.addToEnd(d);
		assertEquals(d,linkedMovie.getLast());
	}
	
	@Test
	public void testAddToFront() {
		assertEquals(b,linkedMovie.getFirst());
		linkedMovie.addToFront(a);
		assertEquals(a,linkedMovie.getFirst());
	}
	
	@Test
	public void testGetFirst() {
		assertEquals(b,linkedMovie.getFirst());
		linkedMovie.addToFront(e);
		assertEquals(e,linkedMovie.getFirst());
	}

	@Test
	public void testGetLast() {
		assertEquals(f,linkedMovie.getLast());
		linkedMovie.addToEnd(d);
		assertEquals(d,linkedMovie.getLast());
	}
	
	@Test
	public void testToArrayList()
	{
		ArrayList<Movie> list;
		linkedMovie.addToFront(a);
		linkedMovie.addToFront(c);
		linkedMovie.addToEnd(d);
		list = linkedMovie.toArrayList();
		assertEquals(c,list.get(0));
		assertEquals(a,list.get(1));
		assertEquals(b,list.get(2));
		assertEquals(f,list.get(3));
		assertEquals(d,list.get(4));
	}
	
	@Test
	public void testIteratorSuccessfulNext() {
		linkedMovie.addToFront(a);
		linkedMovie.addToEnd(d);
		ListIterator<Movie> iteratorMovie = linkedMovie.iterator();
		assertEquals(true, iteratorMovie.hasNext());
		assertEquals(a, iteratorMovie.next());
		assertEquals(b, iteratorMovie.next());
		assertEquals(f, iteratorMovie.next());
		assertEquals(true, iteratorMovie.hasNext());
		assertEquals(d, iteratorMovie.next());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious() {
		linkedMovie.addToFront(a);
		linkedMovie.addToEnd(d);
		ListIterator<Movie> iteratorMovie = linkedMovie.iterator();
		assertEquals(true, iteratorMovie.hasNext());
		assertEquals(a, iteratorMovie.next());
		assertEquals(b, iteratorMovie.next());
		assertEquals(f, iteratorMovie.next());
		assertEquals(d, iteratorMovie.next());
		assertEquals(true, iteratorMovie.hasPrevious());
		assertEquals(d, iteratorMovie.previous());
		assertEquals(f, iteratorMovie.previous());
		assertEquals(b, iteratorMovie.previous());
		assertEquals(a, iteratorMovie.previous());
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext() {
		linkedMovie.addToFront(a);
		linkedMovie.addToEnd(d);
		ListIterator<Movie> iteratorMovie = linkedMovie.iterator();		
		assertEquals(true, iteratorMovie.hasNext());
		assertEquals(a, iteratorMovie.next());
		assertEquals(b, iteratorMovie.next());
		assertEquals(f, iteratorMovie.next());
		assertEquals(true, iteratorMovie.hasNext());
		assertEquals(d, iteratorMovie.next());
		
		try{
			//no more elements in list
			iteratorMovie.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionPrevious() {
		linkedMovie.addToFront(a);
		linkedMovie.addToEnd(d);
		ListIterator<Movie> iteratorMovie = linkedMovie.iterator();		
		assertEquals(true, iteratorMovie.hasNext());
		assertEquals(a, iteratorMovie.next());
		assertEquals(b, iteratorMovie.next());
		assertEquals(f, iteratorMovie.next());
		assertEquals(d, iteratorMovie.next());
		assertEquals(true, iteratorMovie.hasPrevious());
		assertEquals(d, iteratorMovie.previous());
		assertEquals(f, iteratorMovie.previous());
		assertEquals(b, iteratorMovie.previous());
		assertEquals(a, iteratorMovie.previous());
		
		try{
			//no more elements in list
			iteratorMovie.previous();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorUnsupportedOperationException() {
		linkedMovie.addToFront(a);
		linkedMovie.addToEnd(d);
		ListIterator<Movie> iteratorMovie = linkedMovie.iterator();		
		assertEquals(true, iteratorMovie.hasNext());
		assertEquals(a, iteratorMovie.next());
		assertEquals(b, iteratorMovie.next());
		assertEquals(f, iteratorMovie.next());
		assertEquals(true, iteratorMovie.hasNext());
		assertEquals(d, iteratorMovie.next());
		
		try{
			//remove is not supported for the iterator
			iteratorMovie.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
	}
	
	@Test
	public void testRemove() {
		// remove the first
		assertEquals(b, linkedMovie.getFirst());
		assertEquals(f, linkedMovie.getLast());
		linkedMovie.addToFront(a);
		assertEquals(a, linkedMovie.getFirst());
		linkedMovie.remove(a, comparatorMovie);
		assertEquals(b, linkedMovie.getFirst());
		//remove from the end of the list
		linkedMovie.addToEnd(d);
		assertEquals(d, linkedMovie.getLast());
		linkedMovie.remove(d, comparatorMovie);
		assertEquals(f, linkedMovie.getLast());
		//remove from middle of list
		linkedMovie.addToFront(a);
		assertEquals(a, linkedMovie.getFirst());
		assertEquals(f, linkedMovie.getLast());
		linkedMovie.remove(b, comparatorMovie);
		assertEquals(a, linkedMovie.getFirst());
		assertEquals(f, linkedMovie.getLast());
		
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals(b, linkedMovie.getFirst());
		linkedMovie.addToFront(a);
		assertEquals(a, linkedMovie.getFirst());
		assertEquals(a, linkedMovie.retrieveFirstElement());
		assertEquals(b,linkedMovie.getFirst());
		assertEquals(b, linkedMovie.retrieveFirstElement());
		assertEquals(f,linkedMovie.getFirst());
		
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals(f, linkedMovie.getLast());
		linkedMovie.addToEnd(d);
		assertEquals(d, linkedMovie.getLast());
		assertEquals(d, linkedMovie.retrieveLastElement());
		assertEquals(f,linkedMovie.getLast());
	}

	
	private class MovieComparator implements Comparator<Movie>
	{

		@Override
		public int compare(Movie arg0, Movie arg1) {
			// Just put cars in alphabetic order by make
			return arg0.toString().compareTo(arg1.toString());
		}
		
	}
	
	private class Movie {
		String title;
		int year;
		
		public Movie(String title, int year){
			this.title = title;
			this.year = year;
		}
		
		public String getTitle(){
			return title;
		}

		public int getYear(){
			return year;
		}
		
		public String toString() {
			return (getTitle() + " " + getYear());
		}
	}
}
