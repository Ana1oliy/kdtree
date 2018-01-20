package ru.ana1oliy.kdtree.tests.range;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import ru.ana1oliy.kdtree.KDPoint;
import ru.ana1oliy.kdtree.KDRange;

@RunWith(Theories.class)
public class RangeKDTest {

	@Test
	public void testRangeKD() {
		KDPoint from = new KDPoint(1.0, 3.0);
		KDPoint to = new KDPoint(10.0, 12.0);
		KDRange range = new KDRange(from, to);
		assertTrue(from.equals(range.from()));
		assertTrue(to.equals(range.to()));
		assertEquals(from.dimensions(), range.dimensions());
	}
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	@DataPoints
	public static Object[][] wrongArguments = new Object[][] {
			{null,				new KDPoint(1.0)},
			{new KDPoint(3.0),	null},
			{null,				null},
			{new KDPoint(5.0), 	new KDPoint(10.0, -7.0)}
	};
	
	@Theory
	public void testCreateRangeKDWithIncorrectArguments(final Object... points) {
		thrown.expect(IllegalArgumentException.class);
		new KDRange((KDPoint) points[0], (KDPoint) points[1]);
	}

}
