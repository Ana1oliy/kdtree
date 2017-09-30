package ru.ana1oliy.kdtree.tests.range;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.points.PointKD;
import ru.ana1oliy.kdtree.range.KDRange;
import ru.ana1oliy.kdtree.range.RangeKD;

@RunWith(Theories.class)
public class RangeKDTest {

	@Test
	public void testRangeKD() {
		KDPoint<Integer> from = new PointKD<>(1, 3);
		KDPoint<Integer> to = new PointKD<>(10, 12);
		KDRange<Integer> range = new RangeKD<>(from, to);
		assertTrue(from.equals(range.from()));
		assertTrue(to.equals(range.to()));
		assertEquals(from.size(), range.size());
	}
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	@DataPoints
	public static Object[][] wrongArguments = new Object[][] {
			{null,						new PointKD<Integer>(1)},
			{new PointKD<Integer>(3),	null},
			{null,						null},
			{new PointKD<Integer>(5), 	new PointKD<Integer>(10, -7)}
	};
	
	@SuppressWarnings("unchecked")
	@Theory
	public void testCreateRangeKDWithIncorrectArguments(final Object... points) {
		thrown.expect(IllegalArgumentException.class);
		new RangeKD<>((KDPoint<Integer>) points[0], (KDPoint<Integer>) points[1]);
	}

}
