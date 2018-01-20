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
public class RangeKDSplittingTest {

	public RangeKDSplittingTest(Double coordinate, int dimension, KDRange lowerHalf, KDRange higherHalf) {
		this.coordinate = coordinate;
		this.dimension = (char) dimension;
		this.lowerHalf = lowerHalf;
		this.higherHalf = higherHalf;
		range = new KDRange(new KDPoint(1.0, 2.0, 3.0), new KDPoint(11.0, 12.0, 13.0));
	}
	
	private Double coordinate;
	
	private char dimension;
	
	private KDRange lowerHalf;
	
	private KDRange higherHalf;
	
	private KDRange range;
	
	/**
	 * Data for check splitting range range {{1, 2, 3}, {11, 12, 13}}.
	 */
	@Parameterized.Parameters
	public static List<Object[]> pointsSet() {
	    return Arrays.asList(new Object[][] {
		    {5.0,	0,		new KDRange(new KDPoint(1.0, 2.0, 3.0), new KDPoint(5.0, 12.0, 13.0)),		new KDRange(new KDPoint(5.0, 2.0, 3.0), new KDPoint(11.0, 12.0, 13.0))},
		    {1.0,	0,		new KDRange(new KDPoint(1.0, 2.0, 3.0), new KDPoint(1.0, 12.0, 13.0)),		new KDRange(new KDPoint(1.0, 2.0, 3.0), new KDPoint(11.0, 12.0, 13.0))},
		    {11.0,	0,		new KDRange(new KDPoint(1.0, 2.0, 3.0), new KDPoint(11.0, 12.0, 13.0)),		new KDRange(new KDPoint(11.0, 2.0, 3.0), new KDPoint(11.0, 12.0, 13.0))},
		    
		    {6.0,	1,		new KDRange(new KDPoint(1.0, 2.0, 3.0), new KDPoint(11.0, 6.0, 13.0)),		new KDRange(new KDPoint(1.0, 6.0, 3.0), new KDPoint(11.0, 12.0, 13.0))},
		    {2.0,	1,		new KDRange(new KDPoint(1.0, 2.0, 3.0), new KDPoint(11.0, 2.0, 13.0)),		new KDRange(new KDPoint(1.0, 2.0, 3.0), new KDPoint(11.0, 12.0, 13.0))},
		    {12.0,	1,		new KDRange(new KDPoint(1.0, 2.0, 3.0), new KDPoint(11.0, 12.0, 13.0)),		new KDRange(new KDPoint(1.0, 12.0, 3.0), new KDPoint(11.0, 12.0, 13.0))},
		    
		    {5.0,	2,		new KDRange(new KDPoint(1.0, 2.0, 3.0), new KDPoint(11.0, 12.0, 5.0)),		new KDRange(new KDPoint(1.0, 2.0, 5.0), new KDPoint(11.0, 12.0, 13.0))},
		    {3.0,	2,		new KDRange(new KDPoint(1.0, 2.0, 3.0), new KDPoint(11.0, 12.0, 3.0)),		new KDRange(new KDPoint(1.0, 2.0, 3.0), new KDPoint(11.0, 12.0, 13.0))},
		    {13.0,	2,		new KDRange(new KDPoint(1.0, 2.0, 3.0), new KDPoint(11.0, 12.0, 13.0)),		new KDRange(new KDPoint(1.0, 2.0, 13.0), new KDPoint(11.0, 12.0, 13.0))},
	    });
	}
	
	@Test
	public void testSplitting() {
		assertTrue(lowerHalf.equals(range.lowerHalf(coordinate, dimension)));
		assertTrue(higherHalf.equals(range.higherHalf(coordinate, dimension)));
	}

}
