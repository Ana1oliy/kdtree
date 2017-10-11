package ru.ana1oliy.kdtree.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ru.ana1oliy.kdtree.KDTree;
import ru.ana1oliy.kdtree.AbstractKDTree;
import ru.ana1oliy.kdtree.IntKDTree;
import ru.ana1oliy.kdtree.points.IntKDPoint;
import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.points.NumberKDPoint;
import ru.ana1oliy.kdtree.range.KDRange;
import ru.ana1oliy.kdtree.range.AbstractKDRange;
import ru.ana1oliy.kdtree.range.IntKDRange;

public class TreeKDTest {

	@Test
	public void testSize() {
		KDRange<Integer> range = new IntKDRange(new NumberKDPoint<>(0), new NumberKDPoint<>(10));
		KDTree<Integer, Integer> tree = new IntKDTree<>(range);
		
		assertEquals(0, tree.size());
		
		tree.set(new NumberKDPoint<>(1), 1);
		tree.set(new NumberKDPoint<>(1), 2);
		
		assertEquals(1, tree.size());
		
		tree.set(new NumberKDPoint<>(2), 3);
		tree.set(new NumberKDPoint<>(3), 4);
		tree.set(new NumberKDPoint<>(4), 5);
		tree.set(new NumberKDPoint<>(5), 6);
		
		assertEquals(5, tree.size());
	}
	
	@Test
	public void testSetGet() {
		KDRange<Integer> range = new IntKDRange(new NumberKDPoint<>(0), new NumberKDPoint<>(10));
		KDTree<Integer, Integer> tree = new IntKDTree<>(range);
		
		tree.set(new NumberKDPoint<>(1), 1);
		tree.set(new NumberKDPoint<>(2), 2);
		tree.set(new NumberKDPoint<>(3), 3);
		tree.set(new NumberKDPoint<>(4), 4);
		tree.set(new NumberKDPoint<>(5), 5);
		
		assertEquals(new Integer(1), tree.get(new NumberKDPoint<>(1)));
		assertEquals(new Integer(2), tree.get(new NumberKDPoint<>(2)));
		assertEquals(new Integer(3), tree.get(new NumberKDPoint<>(3)));
		assertEquals(new Integer(4), tree.get(new NumberKDPoint<>(4)));
		assertEquals(new Integer(5), tree.get(new NumberKDPoint<>(5)));
	}

	@Test
	public void testDoubleSetGet() {
		KDRange<Integer> range = new IntKDRange(new NumberKDPoint<>(0), new NumberKDPoint<>(10));
		KDTree<Integer, Integer> tree = new IntKDTree<>(range);
		
		tree.set(new NumberKDPoint<>(1), 1);
		assertEquals(new Integer(1), tree.get(new NumberKDPoint<>(1)));
		tree.set(new NumberKDPoint<>(1), 2);
		assertEquals(new Integer(2), tree.get(new NumberKDPoint<>(1)));
		tree.set(new NumberKDPoint<>(3), 3);
		assertEquals(new Integer(3), tree.get(new NumberKDPoint<>(3)));
		tree.set(new NumberKDPoint<>(3), 4);
		assertEquals(new Integer(4), tree.get(new NumberKDPoint<>(3)));
		tree.set(new NumberKDPoint<>(1), 5);
		assertEquals(new Integer(5), tree.get(new NumberKDPoint<>(1)));
	}
	
	@Test
	public void testIsEmpty() {
		KDRange<Integer> range = new IntKDRange(new NumberKDPoint<>(0), new NumberKDPoint<>(10));
		KDTree<Integer, Integer> tree = new IntKDTree<>(range);
		
		assertTrue(tree.isEmpty());
		
		tree.set(new NumberKDPoint<>(2), 1);
		
		assertFalse(tree.isEmpty());
	}

	@Test
	public void testDimensions() {
		KDRange<Integer> range = new IntKDRange(new NumberKDPoint<>(1, 2, 3, 4, 5), new NumberKDPoint<>(6, 7, 8, 9, 10));
		KDTree<Integer, Integer> tree = new IntKDTree<>(range);
		
		assertEquals(5, tree.dimensions());
	}
	
	private KDTree<Integer, Integer> createTree() {
		KDRange<Integer> range = new IntKDRange(new NumberKDPoint<>(-10, -10), new NumberKDPoint<>(10, 10));
		KDTree<Integer, Integer> tree = new IntKDTree<>(range);
		
		tree.set(new NumberKDPoint<>(1, 5), 0);
		tree.set(new NumberKDPoint<>(5, 5), 0);
		tree.set(new NumberKDPoint<>(-2, -2), 0);
		tree.set(new NumberKDPoint<>(4, 8), 0);
		tree.set(new NumberKDPoint<>(-2, 4), 0);
		tree.set(new NumberKDPoint<>(2, -8), 0);
		tree.set(new NumberKDPoint<>(7, 1), 0);
		tree.set(new NumberKDPoint<>(-2, -6), 0);
		tree.set(new NumberKDPoint<>(-6, -4), 0);
		tree.set(new NumberKDPoint<>(-6, 4), 0);
		
		return tree;
	}
	
	@Test
	public void testNearest() {
		KDTree<Integer, Integer> tree = createTree();
		
		KDPoint<Integer> nearest = tree.nearest(new NumberKDPoint<>(-4, 6));
		assertEquals(new NumberKDPoint<>(-2, 4), nearest);
	}
	
	@Test
	public void testFind() {
		KDTree<Integer, Integer> tree = createTree();
		
		List<KDPoint<Integer>> found = tree.find(new IntKDRange(new IntKDPoint(-8, -9), new IntKDPoint(-1, -1)));
		assertTrue(found.indexOf(new NumberKDPoint<>(-2, -2)) >= 0);
		assertTrue(found.indexOf(new NumberKDPoint<>(-2, -6)) >= 0);
		assertTrue(found.indexOf(new NumberKDPoint<>(-6, -4)) >= 0);
	}
}
