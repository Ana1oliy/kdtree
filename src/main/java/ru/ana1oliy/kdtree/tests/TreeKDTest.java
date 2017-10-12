package ru.ana1oliy.kdtree.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ru.ana1oliy.kdtree.KDTree;
import ru.ana1oliy.kdtree.IntKDTree;
import ru.ana1oliy.kdtree.points.IntKDPoint;
import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.range.KDRange;
import ru.ana1oliy.kdtree.range.IntKDRange;

public class TreeKDTest {

	@Test
	public void testSize() {
		KDRange<Integer> range = new IntKDRange(new IntKDPoint(0), new IntKDPoint(10));
		KDTree<Integer, Integer> tree = new IntKDTree<>(range);
		
		assertEquals(0, tree.size());
		
		tree.set(new IntKDPoint(1), 1);
		tree.set(new IntKDPoint(1), 2);
		
		assertEquals(1, tree.size());
		
		tree.set(new IntKDPoint(2), 3);
		tree.set(new IntKDPoint(3), 4);
		tree.set(new IntKDPoint(4), 5);
		tree.set(new IntKDPoint(5), 6);
		
		assertEquals(5, tree.size());
	}
	
	@Test
	public void testSetGet() {
		KDRange<Integer> range = new IntKDRange(new IntKDPoint(0), new IntKDPoint(10));
		KDTree<Integer, Integer> tree = new IntKDTree<>(range);
		
		tree.set(new IntKDPoint(1), 1);
		tree.set(new IntKDPoint(2), 2);
		tree.set(new IntKDPoint(3), 3);
		tree.set(new IntKDPoint(4), 4);
		tree.set(new IntKDPoint(5), 5);
		
		assertEquals(new Integer(1), tree.get(new IntKDPoint(1)));
		assertEquals(new Integer(2), tree.get(new IntKDPoint(2)));
		assertEquals(new Integer(3), tree.get(new IntKDPoint(3)));
		assertEquals(new Integer(4), tree.get(new IntKDPoint(4)));
		assertEquals(new Integer(5), tree.get(new IntKDPoint(5)));
	}

	@Test
	public void testDoubleSetGet() {
		KDRange<Integer> range = new IntKDRange(new IntKDPoint(0), new IntKDPoint(10));
		KDTree<Integer, Integer> tree = new IntKDTree<>(range);
		
		tree.set(new IntKDPoint(1), 1);
		assertEquals(new Integer(1), tree.get(new IntKDPoint(1)));
		tree.set(new IntKDPoint(1), 2);
		assertEquals(new Integer(2), tree.get(new IntKDPoint(1)));
		tree.set(new IntKDPoint(3), 3);
		assertEquals(new Integer(3), tree.get(new IntKDPoint(3)));
		tree.set(new IntKDPoint(3), 4);
		assertEquals(new Integer(4), tree.get(new IntKDPoint(3)));
		tree.set(new IntKDPoint(1), 5);
		assertEquals(new Integer(5), tree.get(new IntKDPoint(1)));
	}
	
	@Test
	public void testIsEmpty() {
		KDRange<Integer> range = new IntKDRange(new IntKDPoint(0), new IntKDPoint(10));
		KDTree<Integer, Integer> tree = new IntKDTree<>(range);
		
		assertTrue(tree.isEmpty());
		
		tree.set(new IntKDPoint(2), 1);
		
		assertFalse(tree.isEmpty());
	}

	@Test
	public void testDimensions() {
		KDRange<Integer> range = new IntKDRange(new IntKDPoint(1, 2, 3, 4, 5), new IntKDPoint(6, 7, 8, 9, 10));
		KDTree<Integer, Integer> tree = new IntKDTree<>(range);
		
		assertEquals(5, tree.dimensions());
	}
	
	private KDTree<Integer, Integer> createTree() {
		KDRange<Integer> range = new IntKDRange(new IntKDPoint(-10, -10), new IntKDPoint(10, 10));
		KDTree<Integer, Integer> tree = new IntKDTree<>(range);
		
		return tree;
	}
	
	private void fillTree(KDTree<Integer, Integer> tree) {
		tree.set(new IntKDPoint(1, 5), 1);
		tree.set(new IntKDPoint(5, 5), 2);
		tree.set(new IntKDPoint(-2, -2), 3);
		tree.set(new IntKDPoint(4, 8), 4);
		tree.set(new IntKDPoint(-2, 4), 5);
		tree.set(new IntKDPoint(2, -8), 6);
		tree.set(new IntKDPoint(7, 1), 7);
		tree.set(new IntKDPoint(-2, -6), 8);
		tree.set(new IntKDPoint(-6, -4), 9);
		tree.set(new IntKDPoint(-6, 4), 0);
	}
	
	@Test
	public void testNearest() {
		KDTree<Integer, Integer> tree = createTree();
		fillTree(tree);
		
		KDPoint<Integer> nearest = tree.nearest(new IntKDPoint(-4, 6));
		assertEquals(new IntKDPoint(-2, 4), nearest);
	}
	
	@Test
	public void testNearestValue() {
		KDTree<Integer, Integer> tree = createTree();
		fillTree(tree);
		
		Integer nearest = tree.nearestValue(new IntKDPoint(-4, 6));
		assertEquals(new Integer(5), nearest);
	}
	
	@Test
	public void testFind() {
		KDTree<Integer, Integer> tree = createTree();
		fillTree(tree);
		
		List<KDPoint<Integer>> found = tree.find(new IntKDRange(new IntKDPoint(-8, -9), new IntKDPoint(-1, -1)));
		assertTrue(found.indexOf(new IntKDPoint(-2, -2)) >= 0);
		assertTrue(found.indexOf(new IntKDPoint(-2, -6)) >= 0);
		assertTrue(found.indexOf(new IntKDPoint(-6, -4)) >= 0);
	}
	
	@Test
	public void testHasValue() {
		KDTree<Integer, Integer> tree = createTree();
		
		assertFalse(tree.hasValue(new IntKDPoint(0, 0)));
		
		fillTree(tree);
		
		assertTrue(tree.hasValue(new IntKDPoint(1, 5)));
		assertTrue(tree.hasValue(new IntKDPoint(5, 5)));
		assertTrue(tree.hasValue(new IntKDPoint(-2, -2)));
		assertTrue(tree.hasValue(new IntKDPoint(4, 8)));
		assertTrue(tree.hasValue(new IntKDPoint(-2, 4)));
		assertTrue(tree.hasValue(new IntKDPoint(2, -8)));
		assertTrue(tree.hasValue(new IntKDPoint(7, 1)));
		assertTrue(tree.hasValue(new IntKDPoint(-2, -6)));
		assertTrue(tree.hasValue(new IntKDPoint(-6, -4)));
		assertTrue(tree.hasValue(new IntKDPoint(-6, 4)));
		
		assertFalse(tree.hasValue(new IntKDPoint(2, 8)));
		assertFalse(tree.hasValue(new IntKDPoint(6, 7)));
		assertFalse(tree.hasValue(new IntKDPoint(7, -1)));
		assertFalse(tree.hasValue(new IntKDPoint(4, 2)));
		assertFalse(tree.hasValue(new IntKDPoint(-1, 2)));
		assertFalse(tree.hasValue(new IntKDPoint(-1, -5)));
	}
}
