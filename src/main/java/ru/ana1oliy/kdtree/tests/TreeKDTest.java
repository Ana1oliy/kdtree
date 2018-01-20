package ru.ana1oliy.kdtree.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ru.ana1oliy.kdtree.KDPoint;
import ru.ana1oliy.kdtree.KDRange;
import ru.ana1oliy.kdtree.KDTree;

public class TreeKDTest {

	@Test
	public void testSize() {
		KDRange range = new KDRange(new KDPoint(0.0), new KDPoint(10.0));
		KDTree<Double> tree = new KDTree<>(range);
		
		assertEquals(0, tree.size());
		
		tree.set(new KDPoint(1.0), 1.0);
		tree.set(new KDPoint(1.0), 2.0);
		
		assertEquals(1, tree.size());
		
		tree.set(new KDPoint(2.0), 3.0);
		tree.set(new KDPoint(3.0), 4.0);
		tree.set(new KDPoint(4.0), 5.0);
		tree.set(new KDPoint(5.0), 6.0);
		
		assertEquals(5, tree.size());
	}
	
	@Test
	public void testSetGet() {
		KDRange range = new KDRange(new KDPoint(0.0), new KDPoint(10.0));
		KDTree<Double> tree = new KDTree<>(range);
		
		tree.set(new KDPoint(1.0), 1.0);
		tree.set(new KDPoint(2.0), 2.0);
		tree.set(new KDPoint(3.0), 3.0);
		tree.set(new KDPoint(4.0), 4.0);
		tree.set(new KDPoint(5.0), 5.0);
		
		assertEquals(new Double(1.0), tree.get(new KDPoint(1.0)));
		assertEquals(new Double(2.0), tree.get(new KDPoint(2.0)));
		assertEquals(new Double(3.0), tree.get(new KDPoint(3.0)));
		assertEquals(new Double(4.0), tree.get(new KDPoint(4.0)));
		assertEquals(new Double(5.0), tree.get(new KDPoint(5.0)));
	}

	@Test
	public void testDoubleSetGet() {
		KDRange range = new KDRange(new KDPoint(0.0), new KDPoint(10.0));
		KDTree<Double> tree = new KDTree<>(range);
		
		tree.set(new KDPoint(1.0), 1.0);
		assertEquals(new Double(1.0), tree.get(new KDPoint(1.0)));
		tree.set(new KDPoint(1.0), 2.0);
		assertEquals(new Double(2.0), tree.get(new KDPoint(1.0)));
		tree.set(new KDPoint(3.0), 3.0);
		assertEquals(new Double(3.0), tree.get(new KDPoint(3.0)));
		tree.set(new KDPoint(3.0), 4.0);
		assertEquals(new Double(4.0), tree.get(new KDPoint(3.0)));
		tree.set(new KDPoint(1.0), 5.0);
		assertEquals(new Double(5.0), tree.get(new KDPoint(1.0)));
	}
	
	@Test
	public void testIsEmpty() {
		KDRange range = new KDRange(new KDPoint(0.0), new KDPoint(10.0));
		KDTree<Double> tree = new KDTree<>(range);
		
		assertTrue(tree.isEmpty());
		
		tree.set(new KDPoint(2.0), 1.0);
		
		assertFalse(tree.isEmpty());
	}

	@Test
	public void testDimensions() {
		KDRange range = new KDRange(new KDPoint(1.0, 2.0, 3.0, 4.0, 5.0), new KDPoint(6.0, 7.0, 8.0, 9.0, 10.0));
		KDTree<Double> tree = new KDTree<>(range);
		
		assertEquals(5, tree.dimensions());
	}
	
	private KDTree<Double> createTree() {
		KDRange range = new KDRange(new KDPoint(-10.0, -10.0), new KDPoint(10.0, 10.0));
		KDTree<Double> tree = new KDTree<>(range);
		
		return tree;
	}
	
	private void fillTree(KDTree<Double> tree) {
		tree.set(new KDPoint(1.0, 5.0), 1.0);
		tree.set(new KDPoint(5.0, 5.0), 2.0);
		tree.set(new KDPoint(-2.0, -2.0), 3.0);
		tree.set(new KDPoint(4.0, 8.0), 4.0);
		tree.set(new KDPoint(-2.0, 4.0), 5.0);
		tree.set(new KDPoint(2.0, -8.0), 6.0);
		tree.set(new KDPoint(7.0, 1.0), 7.0);
		tree.set(new KDPoint(-2.0, -6.0), 8.0);
		tree.set(new KDPoint(-6.0, -4.0), 9.0);
		tree.set(new KDPoint(-6.0, 4.0), 0.0);
	}
	
	@Test
	public void testNearest() {
		KDTree<Double> tree = createTree();
		fillTree(tree);
		
		KDPoint nearest = tree.nearest(new KDPoint(-4.0, 6.0));
		assertEquals(new KDPoint(-2.0, 4.0), nearest);
	}
	
	@Test
	public void testNearestValue() {
		KDTree<Double> tree = createTree();
		fillTree(tree);
		
		double nearest = tree.nearestValue(new KDPoint(-4.0, 6.0));
		assertEquals(5.0, nearest, 0.0);
	}
	
	@Test
	public void testFind() {
		KDTree<Double> tree = createTree();
		fillTree(tree);
		
		List<KDPoint> found = tree.find(new KDRange(new KDPoint(-8.0, -9.0), new KDPoint(-1.0, -1.0)));
		assertTrue(found.indexOf(new KDPoint(-2.0, -2.0)) >= 0);
		assertTrue(found.indexOf(new KDPoint(-2.0, -6.0)) >= 0);
		assertTrue(found.indexOf(new KDPoint(-6.0, -4.0)) >= 0);
	}
	
	@Test
	public void testFindValue() {
		KDTree<Double> tree = createTree();
		fillTree(tree);
		
		List<Double> found = tree.findValues(new KDRange(new KDPoint(-8.0, -9.0), new KDPoint(-1.0, -1.0)));
		assertTrue(found.indexOf(3.0) >= 0);
		assertTrue(found.indexOf(8.0) >= 0);
		assertTrue(found.indexOf(9.0) >= 0);
	}
	
	@Test
	public void testHasValue() {
		KDTree<Double> tree = createTree();
		
		assertFalse(tree.hasValue(new KDPoint(0.0, 0.0)));
		
		fillTree(tree);
		
		assertTrue(tree.hasValue(new KDPoint(1.0, 5.0)));
		assertTrue(tree.hasValue(new KDPoint(5.0, 5.0)));
		assertTrue(tree.hasValue(new KDPoint(-2.0, -2.0)));
		assertTrue(tree.hasValue(new KDPoint(4.0, 8.0)));
		assertTrue(tree.hasValue(new KDPoint(-2.0, 4.0)));
		assertTrue(tree.hasValue(new KDPoint(2.0, -8.0)));
		assertTrue(tree.hasValue(new KDPoint(7.0, 1.0)));
		assertTrue(tree.hasValue(new KDPoint(-2.0, -6.0)));
		assertTrue(tree.hasValue(new KDPoint(-6.0, -4.0)));
		assertTrue(tree.hasValue(new KDPoint(-6.0, 4.0)));
		
		assertFalse(tree.hasValue(new KDPoint(2.0, 8.0)));
		assertFalse(tree.hasValue(new KDPoint(6.0, 7.0)));
		assertFalse(tree.hasValue(new KDPoint(7.0, -1.0)));
		assertFalse(tree.hasValue(new KDPoint(4.0, 2.0)));
		assertFalse(tree.hasValue(new KDPoint(-1.0, 2.0)));
		assertFalse(tree.hasValue(new KDPoint(-1.0, -5.0)));
	}
}
