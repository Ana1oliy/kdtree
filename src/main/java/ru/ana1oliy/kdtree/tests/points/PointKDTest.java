package ru.ana1oliy.kdtree.tests.points;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ru.ana1oliy.kdtree.KDPoint;

public class PointKDTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	
	@Test
	public void testCreateByEmptyArrayException() {
		thrown.expect(IllegalArgumentException.class);
		new KDPoint();
	}

	@Test
	public void testCreateByLargeArrayException() {
		thrown.expect(IllegalArgumentException.class);
		new KDPoint(new Double[1 + Character.MAX_VALUE]);
	}
	
	@Test
	public void testCreateByNullArrayException() {
		thrown.expect(IllegalArgumentException.class);
		new KDPoint((Double[]) null);
	}
	
	@Test
	public void testSize() {
		KDPoint point = new KDPoint(1.0, 2.0, 3.0, 4.0, 5.0);
		assertEquals(point.dimensions(), 5);
	}
	
	@Test
	public void testDistanceToOnDimensionMissmatch() {
		thrown.expect(IllegalArgumentException.class);
		KDPoint point1 = new KDPoint(1.0);
		KDPoint point2 = new KDPoint(1.0, 2.0, 3.0);
		point1.distanceTo(point2);
	}
	
	@Test
	public void testSquaredDistanceToOnDimensionMissmatch() {
		thrown.expect(IllegalArgumentException.class);
		KDPoint point1 = new KDPoint(1.0, 2.0);
		KDPoint point2 = new KDPoint(3.0);
		point1.squaredDistanceTo(point2);
	}
	
	@Test
	public void testDistanceToNull() {
		thrown.expect(IllegalArgumentException.class);
		new KDPoint(1.0).distanceTo(null);
	}
	
	@Test
	public void testSquaredDistanceToNull() {
		thrown.expect(IllegalArgumentException.class);
		new KDPoint(2.0).squaredDistanceTo(null);
	}
	
	@Test
	public void testGetOutOfBounds() {
		thrown.expect(IllegalArgumentException.class);
		KDPoint point = new KDPoint(1.0, 2.0, 3.0);
		point.get((char) 3);
	}
	
	@Test
	public void testGet() {
		KDPoint point = new KDPoint(1.0, 2.0, 3.0);
		assertEquals(1.0, point.get((char) 0), 0.0);
		assertEquals(2.0, point.get((char) 1), 0.0);
		assertEquals(3.0, point.get((char) 2), 0.0);
	}
}
