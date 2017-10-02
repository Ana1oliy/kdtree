package ru.ana1oliy.kdtree;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.range.KDRange;

/**
 * K-dimensional tree. Provides functionality for searching nearest
 * point for any k-dimentional point or points in any range.
 * @author Ana1oliy
 *
 * @param <T> must extends <code>Number</code> class and implement
 * <code>Comparable</code> interface.
 */
public interface KDTree<T extends Number & Comparable<T>> {
	/**
	 * Adds specified point to the tree. If the tree contains the same point, point
	 * will not be added.
	 * @param point point to add. Can not be null.
	 */
	void add(KDPoint<T> point);
	
	/**
	 * Finds the point in tree nearest to specified point.
	 * @param point point to find the nearest point.
	 * @return Point nearest to specified point.
	 */
	KDPoint<T> nearest(KDPoint<T> point);
	
	/**
	 * Finds the points which are in range.
	 * @param searchRange range for searching.
	 * @return Points in specified range.
	 */
	Iterable<KDPoint<T>> find(KDRange<T> searchRange);
	
	/**
	 * Size of tree. 
	 * @return Count of points in the tree.
	 */
	int size();
	
	/**
	 * @return <code>true</code> if tree does not contains any point,
	 * otherwise <code>false</code>.
	 */
	boolean isEmpty();
	
	/**
	 * @return k - number of dimensions
	 */
	char dimensions();
}
