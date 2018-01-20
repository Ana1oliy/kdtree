package ru.ana1oliy.kdtree.tests.range;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ru.ana1oliy.kdtree.KDPoint;
import ru.ana1oliy.kdtree.KDRange;

@RunWith(Parameterized.class)
public class RangeKDContainsTest {

	public RangeKDContainsTest(KDPoint point, boolean inRange) {
		this.point = point;
		this.inRange = inRange;
		range = new KDRange(new KDPoint(-5.0, 3.0), new KDPoint(10.0, -4.0));
	}
	
	private KDPoint point;
	
	private boolean inRange;
	
	private KDRange range;
	
	/**
	 * Data for check is point in range {{-5, 3}, {10, -4}}.
	 */
	@Parameterized.Parameters
	public static List<Object[]> pointsSet() {
	    return Arrays.asList(new Object[][] {
		    {new KDPoint(-5.0, 3.0),	true},
		    {new KDPoint(10.0, 3.0),	true},
		    {new KDPoint(-5.0, -4.0),	true},
		    {new KDPoint(10.0, -4.0),	true},
		    
		    {new KDPoint(-5.0, -1.0),	true},
		    {new KDPoint(2.0, 3.0),		true},
		    {new KDPoint(10.0, 0.0),	true},
		    {new KDPoint(5.0, -4.0),	true},
		    
		    {new KDPoint(7.0, 1.0),		true},
		    {new KDPoint(-1.0, -2.0),	true},
		    
		    {new KDPoint(-7.0, 6.0),	false},
		    {new KDPoint(2.0, 7.0),		false},
		    {new KDPoint(15.0, 6.0),	false},
		    {new KDPoint(15.0, -1.0),	false},
		    {new KDPoint(16.0, -8.0),	false},
		    {new KDPoint(3.0, -5.0),	false},
		    {new KDPoint(-6.0, -9.0),	false},
		    {new KDPoint(-10.0, 0.0),	false}
	    });
	}
	
	@Test
	public void testContains() {
		assertEquals(inRange, range.contains(point));
	}

}
