package ru.ana1oliy.kdtree;

import java.util.List;

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
public interface KDTree<T extends Number & Comparable<T>, G> {
	/**
	 * Adds specified point to the tree and assigns value to this point. If the tree contains the same point,
	 * value assigned to this point will be replaced.
	 * @param point key point to set value. Can not be null. Must fall within the common tree range.
	 * @param value value thats will be assigned to the point. Can not be null.
	 */
	void set(KDPoint<T> point, G value);
	
	/**
	 * Returns the value assigned to the point. If value assigned to the point
	 * does not exists throws IllegalArgumentException. 
	 * @param point key for existing value in tree.
	 * @return Value assigned to the point.
	 */
	G get(KDPoint<T> point);
	
	/**
	 * Checks that tree contains value for specified key point.
	 * @param point key for check. Can not be null.
	 * @return True if into the tree was added value for specified key point, otherwise false.
	 */
	boolean hasValue(KDPoint<T> point);
	
	/**
	 * Finds the point in tree nearest to specified point.
	 * @param point point to find the nearest point.
	 * @return Point nearest to specified point. Null if nothing is found.
	 */
	KDPoint<T> nearest(KDPoint<T> point);
	
	/**
	 * Finds the point in tree nearest to specified point and returns assigned value.
	 * @param point point to find the nearest point.
	 * @return Value assigned to the point nearest to specified point. Null if nothing is found.
	 */
	G nearestValue(KDPoint<T> point);
	
	/**
	 * Finds the points which are in range.
	 * @param searchRange range for searching.
	 * @return Points in specified range. Null if nothing is found.
	 */
	List<KDPoint<T>> find(KDRange<T> searchRange);
	
	/**
	 * Finds values assigned to the points which are in range.
	 * @param searchRange range for searching.
	 * @return values assigned to the points in specified range. Null if nothing is found.
	 */
	//List<G> findValues(KDRange<T> searchRange);
	
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
