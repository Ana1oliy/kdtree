package ru.ana1oliy.kdtree.tests.range;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.points.PointKD;
import ru.ana1oliy.kdtree.range.KDRange;
import ru.ana1oliy.kdtree.range.RangeKD;

@RunWith(Parameterized.class)
public class RangeKDContainsTest {

	public RangeKDContainsTest(KDPoint<Integer> point, boolean inRange) {
		this.point = point;
		this.inRange = inRange;
		range = new RangeKD<>(new PointKD<>(-5, 3), new PointKD<>(10, -4));
	}
	
	private KDPoint<Integer> point;
	
	private boolean inRange;
	
	private KDRange<Integer> range;
	
	/**
	 * Data for check is point in range {{-5, 3}, {10, -4}}.
	 */
	@Parameterized.Parameters
	public static List<Object[]> pointsSet() {
	    return Arrays.asList(new Object[][] {
		    {new PointKD<Integer>(-5, 3),	true},
		    {new PointKD<Integer>(10, 3),	true},
		    {new PointKD<Integer>(-5, -4),	true},
		    {new PointKD<Integer>(10, -4),	true},
		    
		    {new PointKD<Integer>(-5, -1),	true},
		    {new PointKD<Integer>(2, 3),	true},
		    {new PointKD<Integer>(10, 0),	true},
		    {new PointKD<Integer>(5, -4),	true},
		    
		    {new PointKD<Integer>(7, 1),	true},
		    {new PointKD<Integer>(-1, -2),	true},
		    
		    {new PointKD<Integer>(-7, 6),	false},
		    {new PointKD<Integer>(2, 7),	false},
		    {new PointKD<Integer>(15, 6),	false},
		    {new PointKD<Integer>(15, -1),	false},
		    {new PointKD<Integer>(16, -8),	false},
		    {new PointKD<Integer>(3, -5),	false},
		    {new PointKD<Integer>(-6, -9),	false},
		    {new PointKD<Integer>(-10, 0),	false}
	    });
	}
	
	@Test
	public void testContains() {
		assertEquals(inRange, range.contains(point));
	}

}
