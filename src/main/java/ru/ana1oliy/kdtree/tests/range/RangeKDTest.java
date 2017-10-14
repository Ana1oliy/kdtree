package ru.ana1oliy.kdtree.tests.range;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import ru.ana1oliy.kdtree.points.IntKDPoint;
import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.points.NumberKDPoint;
import ru.ana1oliy.kdtree.range.KDRange;
import ru.ana1oliy.kdtree.range.IntKDRange;

@RunWith(Theories.class)
public class RangeKDTest {

	@Test
	public void testRangeKD() {
		KDPoint<Integer> from = new IntKDPoint(1, 3);
		KDPoint<Integer> to = new IntKDPoint(10, 12);
		KDRange<Integer> range = new IntKDRange(from, to);
		assertTrue(from.equals(range.from()));
		assertTrue(to.equals(range.to()));
		assertEquals(from.dimensions(), range.dimensions());
	}
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	@DataPoints
	public static Object[][] wrongArguments = new Object[][] {
			{null,						new NumberKDPoint<Integer>(1)},
			{new NumberKDPoint<Integer>(3),	null},
			{null,						null},
			{new NumberKDPoint<Integer>(5), 	new NumberKDPoint<Integer>(10, -7)}
	};
	
	@SuppressWarnings("unchecked")
	@Theory
	public void testCreateRangeKDWithIncorrectArguments(final Object... points) {
		thrown.expect(IllegalArgumentException.class);
		new IntKDRange((KDPoint<Integer>) points[0], (KDPoint<Integer>) points[1]);
	}

}
