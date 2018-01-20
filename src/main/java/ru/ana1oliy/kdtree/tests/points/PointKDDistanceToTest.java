package ru.ana1oliy.kdtree.tests.points;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ru.ana1oliy.kdtree.KDPoint;

@RunWith(Parameterized.class)
public class PointKDDistanceToTest {

	public PointKDDistanceToTest(double x1, double y1, double z1, double x2, double y2, double z2, double squared, double distance) {
		this.squared = squared;
		this.distance = distance;
		
		point1 = new KDPoint(x1, y1, z1);
		point2 = new KDPoint(x2, y2, z2);
	}
	
	private KDPoint point1;
	
	private KDPoint point2;
	
	private double squared;
	
	private double distance;
	
	@Parameterized.Parameters
	public static List<Object[]> isEmptyData() {
	    return Arrays.asList(new Object[][] {
		    {0.0, 0.0, 0.0,			1.0, 1.0, 1.0,					3.0,				1.7320508075688772},
		    {1.0, 1.0, 1.0,			1.0, 1.0, 2.0,					1.0,				1.0},
		    {2.0, 2.0, 2.0,			3.0, 2.0, 2.0,					1.0,				1.0},
		    {3.0, 3.0, 3.0,			3.0, 4.0, 3.0,					1.0,				1.0},
		    {1.0, 2.0, 3.0,			4.0, 5.0, 6.0,					27.0,				5.196152422706632},
		    {200.0, 0.0, 10556.0,	12311.0, 123231.0, 999999.0,	994330005931.0,		997160.9729281426}
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
