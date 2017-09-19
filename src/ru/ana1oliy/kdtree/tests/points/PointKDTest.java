package ru.ana1oliy.kdtree.tests.points;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ru.ana1oliy.kdtree.points.PointKD;

public class PointKDTest {

	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void testCreateException() {
		thrown.expect(IllegalArgumentException.class);
		new PointKD<>((char) 0);
	}
	
	@Test
	public void testCreateByEmptyArrayException() {
		thrown.expect(IllegalArgumentException.class);
		new PointKD<>(new Integer[0]);
	}

	@Test
	public void testCreateByLargeArrayException() {
		thrown.expect(IllegalArgumentException.class);
		new PointKD<>(new Integer[1 + Character.MAX_VALUE]);
	}
	
	
	@Test
	public void testCreateByNullArrayException() {
		thrown.expect(IllegalArgumentException.class);
		new PointKD<>(null);
	}
	
	@Test
	public void testSize() {
		PointKD<Integer> point = new PointKD<>((char) 5);
		assertEquals(point.size(), 5);
	}
	
	@Test
	public void testDistanceToOnDimensionMissmatch() {
		thrown.expect(IllegalArgumentException.class);
		PointKD<Integer> point1 = new PointKD<>((char) 1);
		PointKD<Integer> point2 = new PointKD<>((char) 10);
		point1.distanceTo(point2);
	}
	
	@Test
	public void testSquaredDistanceToOnDimensionMissmatch() {
		thrown.expect(IllegalArgumentException.class);
		PointKD<Integer> point1 = new PointKD<>((char) 1);
		PointKD<Integer> point2 = new PointKD<>((char) 10);
		point1.squaredDistanceTo(point2);
	}
	
	@Test
	public void testDistanceToNull() {
		thrown.expect(IllegalArgumentException.class);
		new PointKD<>((char) 1).distanceTo(null);
	}
	
	@Test
	public void testSquaredDistanceToNull() {
		thrown.expect(IllegalArgumentException.class);
		new PointKD<>((char) 1).squaredDistanceTo(null);
	}
}
