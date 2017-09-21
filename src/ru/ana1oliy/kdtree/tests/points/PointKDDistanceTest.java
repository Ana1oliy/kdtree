package ru.ana1oliy.kdtree.tests.points;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ru.ana1oliy.kdtree.points.PointKD;

@RunWith(Parameterized.class)
public class PointKDDistanceTest {

	public PointKDDistanceTest(int x1, int y1, int z1, int x2, int y2, int z2, double squared, double distance) {
		this.squared = squared;
		this.distance = distance;
		
		point1 = new PointKD<>(x1, y1, z1);
		point2 = new PointKD<>(x2, y2, z2);
	}
	
	private PointKD<Integer> point1;
	
	private PointKD<Integer> point2;
	
	private double squared;
	
	private double distance;
	
	@Parameterized.Parameters
	public static List<Object[]> isEmptyData() {
	    return Arrays.asList(new Object[][] {
		    {0, 0, 0,					1, 1, 1,					3.0,				1.7320508075688772},
		    {1, 1, 1,					1, 1, 2,					1.0,				1.0},
		    {2, 2, 2,					3, 2, 2,					1.0,				1.0},
		    {3, 3, 3,					3, 4, 3,					1.0,				1.0},
		    {1, 2, 3,					4, 5, 6,					27.0,				5.196152422706632},
		    {200, 0, 10556,				12311, 123231, 999999, 		994330005931.0,		997160.9729281426}
	    });
	}
	
	@Test
	public void testDistanceTo() {
		assertEquals(distance, point1.distanceTo(point2), 0.000000001);
	}

	@Test
	public void testSquaredDistanceTo() {
		assertEquals(squared, point1.squaredDistanceTo(point2), 0.0);
	}
	
	@Test
	public void testDistanceTo2() {
		assertEquals(distance, point2.distanceTo(point1), 0.000000001);
	}

	@Test
	public void testSquaredDistanceTo2() {
		assertEquals(squared, point2.squaredDistanceTo(point1), 0.0);
	}
}
