package ru.ana1oliy.kdtree.tests.points;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.points.PointKD;

public class PointKDTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void testCreateByEmptyArrayException() {
		thrown.expect(IllegalArgumentException.class);
		new PointKD<Integer>();
	}

	@Test
	public void testCreateByLargeArrayException() {
		thrown.expect(IllegalArgumentException.class);
		new PointKD<>(new Integer[1 + Character.MAX_VALUE]);
	}
	
	@Test
	public void testCreateByNullArrayException() {
		thrown.expect(IllegalArgumentException.class);
		new PointKD<Integer>(null);
	}
	
	@Test
	public void testSize() {
		PointKD<Integer> point = new PointKD<>(1, 2, 3, 4, 5);
		assertEquals(point.size(), 5);
	}
	
	@Test
	public void testDistanceToOnDimensionMissmatch() {
		thrown.expect(IllegalArgumentException.class);
		PointKD<Integer> point1 = new PointKD<>(1);
		PointKD<Integer> point2 = new PointKD<>(1, 2, 3);
		point1.distanceTo(point2);
	}
	
	@Test
	public void testSquaredDistanceToOnDimensionMissmatch() {
		thrown.expect(IllegalArgumentException.class);
		PointKD<Integer> point1 = new PointKD<>(1, 2);
		PointKD<Integer> point2 = new PointKD<>(3);
		point1.squaredDistanceTo(point2);
	}
	
	@Test
	public void testDistanceToNull() {
		thrown.expect(IllegalArgumentException.class);
		new PointKD<>(1).distanceTo(null);
	}
	
	@Test
	public void testSquaredDistanceToNull() {
		thrown.expect(IllegalArgumentException.class);
		new PointKD<>(2).squaredDistanceTo(null);
	}
	
	@Test
	public void testGetOutOfBounds() {
		thrown.expect(IllegalArgumentException.class);
		KDPoint<Integer> point = new PointKD<>(1, 2, 3);
		point.get((char) 3);
	}
	
	@Test
	public void testGet() {
		KDPoint<Integer> point = new PointKD<>(1, 2, 3);
		assertEquals(1, point.get((char) 0).intValue());
		assertEquals(2, point.get((char) 1).intValue());
		assertEquals(3, point.get((char) 2).intValue());
	}
}
