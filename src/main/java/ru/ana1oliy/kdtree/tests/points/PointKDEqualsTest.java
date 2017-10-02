package ru.ana1oliy.kdtree.tests.points;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ru.ana1oliy.kdtree.points.NumberKDPoint;

@RunWith(Parameterized.class)
public class PointKDEqualsTest {
	
	public PointKDEqualsTest(Object point1, Object point2, boolean areEquals) {
		this.point1 = point1;
		this.point2 = point2;
		this.areEquals = areEquals;
	}
	
	private Object point1;
	
	private Object point2;
	
	private boolean areEquals;
	
	@Parameterized.Parameters
	public static List<Object[]> pointsSet() {
	    return Arrays.asList(new Object[][] {
		    {new NumberKDPoint<Integer>(1, 2),		new NumberKDPoint<Integer>(1, 2),		true},
		    {new NumberKDPoint<Integer>(1, 2),		new NumberKDPoint<Double>(1.0, 2.0),	false},
		    {new NumberKDPoint<Integer>(1, 2),		new NumberKDPoint<Integer>(2, 1),		false},
		    {new NumberKDPoint<Integer>(1, 2),		null,									false},
		    {new NumberKDPoint<Integer>(1, 2, 3),	new NumberKDPoint<Integer>(1, 2),		false},
		    {new NumberKDPoint<Integer>(1),			new Object(),							false}
	    });
	}
	
	@Test
	public void testEqualsObject() {
		assertEquals(areEquals, point1.equals(point2));
	}

}
