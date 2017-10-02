package ru.ana1oliy.kdtree.tests.range;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.points.NumberKDPoint;
import ru.ana1oliy.kdtree.range.KDRange;
import ru.ana1oliy.kdtree.range.AbstractKDRange;
import ru.ana1oliy.kdtree.range.IntKDRange;

@RunWith(Parameterized.class)
public class RangeKDContainsTest {

	public RangeKDContainsTest(KDPoint<Integer> point, boolean inRange) {
		this.point = point;
		this.inRange = inRange;
		range = new IntKDRange(new NumberKDPoint<>(-5, 3), new NumberKDPoint<>(10, -4));
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
		    {new NumberKDPoint<Integer>(-5, 3),		true},
		    {new NumberKDPoint<Integer>(10, 3),		true},
		    {new NumberKDPoint<Integer>(-5, -4),	true},
		    {new NumberKDPoint<Integer>(10, -4),	true},
		    
		    {new NumberKDPoint<Integer>(-5, -1),	true},
		    {new NumberKDPoint<Integer>(2, 3),		true},
		    {new NumberKDPoint<Integer>(10, 0),		true},
		    {new NumberKDPoint<Integer>(5, -4),		true},
		    
		    {new NumberKDPoint<Integer>(7, 1),		true},
		    {new NumberKDPoint<Integer>(-1, -2),	true},
		    
		    {new NumberKDPoint<Integer>(-7, 6),		false},
		    {new NumberKDPoint<Integer>(2, 7),		false},
		    {new NumberKDPoint<Integer>(15, 6),		false},
		    {new NumberKDPoint<Integer>(15, -1),	false},
		    {new NumberKDPoint<Integer>(16, -8),	false},
		    {new NumberKDPoint<Integer>(3, -5),		false},
		    {new NumberKDPoint<Integer>(-6, -9),	false},
		    {new NumberKDPoint<Integer>(-10, 0),	false}
	    });
	}
	
	@Test
	public void testContains() {
		assertEquals(inRange, range.contains(point));
	}

}
