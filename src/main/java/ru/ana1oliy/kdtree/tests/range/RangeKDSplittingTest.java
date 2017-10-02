package ru.ana1oliy.kdtree.tests.range;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ru.ana1oliy.kdtree.points.NumberKDPoint;
import ru.ana1oliy.kdtree.range.KDRange;
import ru.ana1oliy.kdtree.range.IntKDRange;

@RunWith(Parameterized.class)
public class RangeKDSplittingTest {

	public RangeKDSplittingTest(Integer coordinate, int dimension, KDRange<Integer> lowerHalf, KDRange<Integer> higherHalf) {
		this.coordinate = coordinate;
		this.dimension = (char) dimension;
		this.lowerHalf = lowerHalf;
		this.higherHalf = higherHalf;
		range = new IntKDRange(new NumberKDPoint<>(1, 2, 3), new NumberKDPoint<>(11, 12, 13));
	}
	
	private Integer coordinate;
	
	private char dimension;
	
	private KDRange<Integer> lowerHalf;
	
	private KDRange<Integer> higherHalf;
	
	private KDRange<Integer> range;
	
	/**
	 * Data for check splitting range range {{1, 2, 3}, {11, 12, 13}}.
	 */
	@Parameterized.Parameters
	public static List<Object[]> pointsSet() {
	    return Arrays.asList(new Object[][] {
		    {5,		0,		new IntKDRange(new NumberKDPoint<>(1, 2, 3), new NumberKDPoint<>(5, 12, 13)),		new IntKDRange(new NumberKDPoint<>(5, 2, 3), new NumberKDPoint<>(11, 12, 13))},
		    {1,		0,		new IntKDRange(new NumberKDPoint<>(1, 2, 3), new NumberKDPoint<>(1, 12, 13)),		new IntKDRange(new NumberKDPoint<>(1, 2, 3), new NumberKDPoint<>(11, 12, 13))},
		    {11,	0,		new IntKDRange(new NumberKDPoint<>(1, 2, 3), new NumberKDPoint<>(11, 12, 13)),		new IntKDRange(new NumberKDPoint<>(11, 2, 3), new NumberKDPoint<>(11, 12, 13))},
		    
		    {6,		1,		new IntKDRange(new NumberKDPoint<>(1, 2, 3), new NumberKDPoint<>(11, 6, 13)),		new IntKDRange(new NumberKDPoint<>(1, 6, 3), new NumberKDPoint<>(11, 12, 13))},
		    {2,		1,		new IntKDRange(new NumberKDPoint<>(1, 2, 3), new NumberKDPoint<>(11, 2, 13)),		new IntKDRange(new NumberKDPoint<>(1, 2, 3), new NumberKDPoint<>(11, 12, 13))},
		    {12,	1,		new IntKDRange(new NumberKDPoint<>(1, 2, 3), new NumberKDPoint<>(11, 12, 13)),		new IntKDRange(new NumberKDPoint<>(1, 12, 3), new NumberKDPoint<>(11, 12, 13))},
		    
		    {5,		2,		new IntKDRange(new NumberKDPoint<>(1, 2, 3), new NumberKDPoint<>(11, 12, 5)),		new IntKDRange(new NumberKDPoint<>(1, 2, 5), new NumberKDPoint<>(11, 12, 13))},
		    {3,		2,		new IntKDRange(new NumberKDPoint<>(1, 2, 3), new NumberKDPoint<>(11, 12, 3)),		new IntKDRange(new NumberKDPoint<>(1, 2, 3), new NumberKDPoint<>(11, 12, 13))},
		    {13,	2,		new IntKDRange(new NumberKDPoint<>(1, 2, 3), new NumberKDPoint<>(11, 12, 13)),		new IntKDRange(new NumberKDPoint<>(1, 2, 13), new NumberKDPoint<>(11, 12, 13))},
	    });
	}
	
	@Test
	public void testSplitting() {
		assertTrue(lowerHalf.equals(range.lowerHalf(coordinate, dimension)));
		assertTrue(higherHalf.equals(range.higherHalf(coordinate, dimension)));
	}

}
