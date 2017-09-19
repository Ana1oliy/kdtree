package ru.ana1oliy.kdtree.tests.points;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.points.PointKD;

@RunWith(Theories.class)
public class PointKDGetSetTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	@DataPoints
	public static Object[][] dimensions = new Object[][] {
	    {(char) 0, new Integer(1) },
	    {(char) 1, new Integer(2) },
	    {(char) 2, new Integer(3) },
	};
	  
	@Theory
	public void testSetGet(final Object... testData) {
		KDPoint<Integer> point = new PointKD<>((char) 3);
		char dimension = (char) testData[0];
		Integer coordinate = (Integer) testData[1];
		point.set(dimension, coordinate);
		assertEquals(coordinate, point.get(dimension));
	}
	
	@Test
	public void testSetOutOfBounds() {
		thrown.expect(IllegalArgumentException.class);
		KDPoint<Integer> point = new PointKD<>((char) 3);
		point.set((char) 3, new Integer(1));
	}

	@Test
	public void testGetOutOfBounds() {
		thrown.expect(IllegalArgumentException.class);
		KDPoint<Integer> point = new PointKD<>((char) 3);
		point.get((char) 3);
	}
}
