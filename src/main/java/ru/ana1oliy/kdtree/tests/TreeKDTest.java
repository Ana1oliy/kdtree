package ru.ana1oliy.kdtree.tests;

import static org.junit.Assert.*;

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
		KDTree<Integer> tree = new IntKDTree(range);
		
		assertEquals(0, tree.size());
		
		tree.add(new NumberKDPoint<>(1));
		tree.add(new NumberKDPoint<>(1));
		
		assertEquals(1, tree.size());
		
		tree.add(new NumberKDPoint<>(2));
		tree.add(new NumberKDPoint<>(3));
		tree.add(new NumberKDPoint<>(4));
		tree.add(new NumberKDPoint<>(5));
		
		assertEquals(5, tree.size());
	}

	@Test
	public void testIsEmpty() {
		KDRange<Integer> range = new IntKDRange(new NumberKDPoint<>(0), new NumberKDPoint<>(10));
		KDTree<Integer> tree = new IntKDTree(range);
		
		assertTrue(tree.isEmpty());
		
		tree.add(new NumberKDPoint<>(2));
		
		assertFalse(tree.isEmpty());
	}

	@Test
	public void testDimensions() {
		KDRange<Integer> range = new IntKDRange(new NumberKDPoint<>(1, 2, 3, 4, 5), new NumberKDPoint<>(6, 7, 8, 9, 10));
		KDTree<Integer> tree = new IntKDTree(range);
		
		assertEquals(5, tree.dimensions());
	}
	
	@Test
	public void testNearest() {
		KDRange<Integer> range = new IntKDRange(new NumberKDPoint<>(-10, -10), new NumberKDPoint<>(10, 10));
		KDTree<Integer> tree = new IntKDTree(range);
		
		tree.add(new NumberKDPoint<>(1, 5));
		tree.add(new NumberKDPoint<>(5, 5));
		tree.add(new NumberKDPoint<>(-2, -2));
		tree.add(new NumberKDPoint<>(4, 8));
		tree.add(new NumberKDPoint<>(-2, 4));
		tree.add(new NumberKDPoint<>(2, -8));
		tree.add(new NumberKDPoint<>(7, 1));
		tree.add(new NumberKDPoint<>(-2, -6));
		tree.add(new NumberKDPoint<>(-6, -4));
		tree.add(new NumberKDPoint<>(-6, 4));
		
		KDPoint<Integer> nearest = tree.nearest(new NumberKDPoint<>(-4, 6));
		assertEquals(new NumberKDPoint<>(-2, 4), nearest);
	}
	
	@Test
	public void testFind() {
//		KDRange<Integer> range = new IntKDRange(new NumberKDPoint<>(-10, -10), new NumberKDPoint<>(10, 10));
//		KDTree<Integer> tree = new IntKDTree(range);
//		
//		tree.add(new NumberKDPoint<>(1, 5));
//		tree.add(new NumberKDPoint<>(5, 5));
//		tree.add(new NumberKDPoint<>(-2, -2));
//		tree.add(new NumberKDPoint<>(4, 8));
//		tree.add(new NumberKDPoint<>(-2, 4));
//		tree.add(new NumberKDPoint<>(2, -8));
//		tree.add(new NumberKDPoint<>(7, 1));
//		tree.add(new NumberKDPoint<>(-2, -6));
//		tree.add(new NumberKDPoint<>(-6, -4));
//		tree.add(new NumberKDPoint<>(-6, 4));
//		
//		Iterable<KDPoint<Integer>> found = tree.find(new IntKDRange<>(new IntKDPoint(-8, -9), new IntKDPoint(-1, -1)));
//		found.
//		assertEquals(new NumberKDPoint<>(-2, 4), nearest);
	}
}
