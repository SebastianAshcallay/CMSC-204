


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SortedDoubleLinkedList_STUDENT_Test {
	SortedDoubleLinkedList<Movie> sortedLinkedMovie;
	MovieComparator comparatorMovie;
	
	public Movie a = new Movie("The Shape of Water", 2017);
	public Movie b = new Movie("Parasite", 2019);
	public Movie c = new Movie("Nomadland", 2020);
	public Movie d = new Movie("Moonlight", 2016);
	public Movie e = new Movie("Birdman", 2014);
	public Movie f = new Movie("Green Book", 2018);
	//alphabetic order: e f d c b a
	
	@Before
	public void setUp() throws Exception {
		comparatorMovie = new MovieComparator();
		sortedLinkedMovie = new SortedDoubleLinkedList<Movie>(comparatorMovie);
		
	}

	@After
	public void tearDown() throws Exception {
		comparatorMovie = null;
		sortedLinkedMovie = null;
	}

	@Test
	public void testAddToEnd() {
		try {
			sortedLinkedMovie.addToEnd(a);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testAddToFront() {
		try {
			sortedLinkedMovie.addToFront(f);
			assertTrue("Did not throw an UnsupportedOperationException", false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw an UnsupportedOperationException", true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
	}

	@Test
	public void testIteratorSuccessfulMovieNext() {
		sortedLinkedMovie.add(a);
		sortedLinkedMovie.add(b);
		sortedLinkedMovie.add(c);
		sortedLinkedMovie.add(d);
		ListIterator<Movie> iterator = sortedLinkedMovie.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(d, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(true, iterator.hasNext());
	}

	@Test
	public void testIteratorSuccessfulMoviePrevious() {
		sortedLinkedMovie.add(e);
		sortedLinkedMovie.add(c);
		sortedLinkedMovie.add(b);
		sortedLinkedMovie.add(d);
		//ArrayList<Movie> movieList = sortedLinkedMovie.toArrayList();
		//alphabetic order: e f d c b a
		ListIterator<Movie> iterator = sortedLinkedMovie.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals(e, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(b, iterator.next());
		assertEquals(true, iterator.hasPrevious());
		assertEquals(b, iterator.previous());
		assertEquals(c, iterator.previous());
		assertEquals(d, iterator.previous());
	}

	@Test
	public void testIteratorNoSuchElementException() {
		sortedLinkedMovie.add(e);
		sortedLinkedMovie.add(c);
		sortedLinkedMovie.add(b);
		sortedLinkedMovie.add(d);
		//ArrayList<Movie> movieList = sortedLinkedMovie.toArrayList();
		//alphabetic order: e f d c b a
		ListIterator<Movie> iterator = sortedLinkedMovie.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(e, iterator.next());
		assertEquals(d, iterator.next());
		assertEquals(c, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(b, iterator.next());
		try{
			//no more elements in list
			iterator.next();
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
	public void testIteratorUnsupportedOperationExceptionString() {
		sortedLinkedMovie.add(e);
		sortedLinkedMovie.add(c);
		sortedLinkedMovie.add(b);
		sortedLinkedMovie.add(d);
		//ArrayList<Movie> movieList = sortedLinkedMovie.toArrayList();
		//alphabetic order: e f d c b a
		ListIterator<Movie> iterator = sortedLinkedMovie.iterator();
		
		try{
			//remove is not supported for the iterator
			iterator.remove();
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
	public void testAddCar() {
		//alphabetic order: e f d c b a
		sortedLinkedMovie.add(a);
		sortedLinkedMovie.add(b);
		sortedLinkedMovie.add(c);
		assertEquals(c, sortedLinkedMovie.getFirst());
		assertEquals(a, sortedLinkedMovie.getLast());
		sortedLinkedMovie.add(d);
		sortedLinkedMovie.add(e);
		assertEquals(e, sortedLinkedMovie.getFirst());
		assertEquals(a, sortedLinkedMovie.getLast());
		//deletes last element from linked list
		assertEquals(a, sortedLinkedMovie.retrieveLastElement());
		assertEquals(b, sortedLinkedMovie.getLast());
	}

	@Test
	public void testRemoveFirstCar() {
		//alphabetic order: e f d c b a
		sortedLinkedMovie.add(b);
		sortedLinkedMovie.add(c);
		assertEquals(c, sortedLinkedMovie.getFirst());
		assertEquals(b, sortedLinkedMovie.getLast());
		sortedLinkedMovie.add(f);
		assertEquals(f, sortedLinkedMovie.getFirst());
		// remove the first
		sortedLinkedMovie.remove(f, comparatorMovie);
		assertEquals(c, sortedLinkedMovie.getFirst());
	}
	
	@Test
	public void testRemoveEndCar() {
		//alphabetic order: e f d c b a
		sortedLinkedMovie.add(b);
		sortedLinkedMovie.add(f);
		assertEquals(f, sortedLinkedMovie.getFirst());
		assertEquals(b, sortedLinkedMovie.getLast());
		sortedLinkedMovie.add(a);
		assertEquals(a, sortedLinkedMovie.getLast());
		//remove from the end of the list
		sortedLinkedMovie.remove(a, comparatorMovie);
		assertEquals(b, sortedLinkedMovie.getLast());
	}

	@Test
	public void testRemoveMiddleCar() {
		//alphabetic order: e f d c b a
		sortedLinkedMovie.add(f);
		sortedLinkedMovie.add(a);
		assertEquals(f, sortedLinkedMovie.getFirst());
		assertEquals(a, sortedLinkedMovie.getLast());
		sortedLinkedMovie.add(e);
		assertEquals(e, sortedLinkedMovie.getFirst());
		assertEquals(a, sortedLinkedMovie.getLast());
		assertEquals(3,sortedLinkedMovie.getSize());
		//remove from middle of list
		sortedLinkedMovie.remove(f, comparatorMovie);
		assertEquals(e, sortedLinkedMovie.getFirst());
		assertEquals(a, sortedLinkedMovie.getLast());
		assertEquals(2,sortedLinkedMovie.getSize());
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
