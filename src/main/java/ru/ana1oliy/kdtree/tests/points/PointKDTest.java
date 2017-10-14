package ru.ana1oliy.kdtree.tests.points;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.points.NumberKDPoint;

public class PointKDTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	
	@Test
	public void testCreateByEmptyArrayException() {
		thrown.expect(IllegalArgumentException.class);
		new NumberKDPoint<Integer>();
	}

	@Test
	public void testCreateByLargeArrayException() {
		thrown.expect(IllegalArgumentException.class);
		new NumberKDPoint<>(new Integer[1 + Character.MAX_VALUE]);
	}
	
	@Test
	public void testCreateByNullArrayException() {
		thrown.expect(IllegalArgumentException.class);
		new NumberKDPoint<Integer>((Integer[]) null);
	}
	
	@Test
	public void testSize() {
		NumberKDPoint<Integer> point = new NumberKDPoint<>(1, 2, 3, 4, 5);
		assertEquals(point.dimensions(), 5);
	}
	
	@Test
	public void testDistanceToOnDimensionMissmatch() {
		thrown.expect(IllegalArgumentException.class);
		NumberKDPoint<Integer> point1 = new NumberKDPoint<>(1);
		NumberKDPoint<Integer> point2 = new NumberKDPoint<>(1, 2, 3);
		point1.distanceTo(point2);
	}
	
	@Test
	public void testSquaredDistanceToOnDimensionMissmatch() {
		thrown.expect(IllegalArgumentException.class);
		NumberKDPoint<Integer> point1 = new NumberKDPoint<>(1, 2);
		NumberKDPoint<Integer> point2 = new NumberKDPoint<>(3);
		point1.squaredDistanceTo(point2);
	}
	
	@Test
	public void testDistanceToNull() {
		thrown.expect(IllegalArgumentException.class);
		new NumberKDPoint<>(1).distanceTo(null);
	}
	
	@Test
	public void testSquaredDistanceToNull() {
		thrown.expect(IllegalArgumentException.class);
		new NumberKDPoint<>(2).squaredDistanceTo(null);
	}
	
	@Test
	public void testGetOutOfBounds() {
		thrown.expect(IllegalArgumentException.class);
		KDPoint<Integer> point = new NumberKDPoint<>(1, 2, 3);
		point.get((char) 3);
	}
	
	@Test
	public void testGet() {
		KDPoint<Integer> point = new NumberKDPoint<>(1, 2, 3);
		assertEquals(1, point.get((char) 0).intValue());
		assertEquals(2, point.get((char) 1).intValue());
		assertEquals(3, point.get((char) 2).intValue());
	}
}
