import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GradeBookTester {

	private GradeBook g1, g2;
	
	@BeforeEach
	void setUp() throws Exception {
		g1 = new GradeBook(5);
		g2 = new GradeBook(5);
		
		g1.addScore(2.5);
		g1.addScore(13);
		g2.addScore(3.5);
		g2.addScore(9);
	}

	@AfterEach
	void tearDown() throws Exception {
		g1 = null;
		g2 = null;
	}

	@Test
	void testAddScore() {
		assertTrue(g1.toString().equals("2.5 13.0"));
		assertTrue(g2.toString().equals("3.5 9.0"));
		assertEquals(2, g1.getScoreSize());
		assertEquals(2, g2.getScoreSize());
		
		/*
		 * The following is only applicable for this test method.
		 * For other methods, GradeBook objects reset to their
		 * default values set in the setup method.
		 */
		
		g1.addScore(1);
		g2.addScore(1);
		
		assertTrue(g1.toString().equals("2.5 13.0 1.0"));
		assertTrue(g2.toString().equals("3.5 9.0 1.0"));
		assertEquals(3, g1.getScoreSize());
		assertEquals(3, g2.getScoreSize());
	}

	@Test
	void testSum() {
		assertEquals(15.5, g1.sum());
		assertEquals(12.5, g2.sum());
	}

	@Test
	void testMinimum() {
		assertEquals(2.5, g1.minimum());
		assertEquals(3.5, g2.minimum());
	}

	@Test
	void testFinalScore() {
		
		g1.addScore(4);
		g2.addScore(3);
		
		assertEquals(17, g1.finalScore());
		assertEquals(12.5, g2.finalScore());
	}

	@Test
	void testGetScoreSize() {
		assertEquals(2, g1.getScoreSize());
		assertEquals(2, g2.getScoreSize());
	}

	@Test
	void testToString() {
		assertTrue(g1.toString().equals("2.5 13.0"));
		assertTrue(g2.toString().equals("3.5 9.0"));
	}

}
