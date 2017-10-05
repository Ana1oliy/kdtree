package ru.ana1oliy.kdtree.range;

import ru.ana1oliy.kdtree.points.KDPoint;

/**
 * Represents multidimensional range. Provides functionality for check contains
 * range any point or not and creation of new ranges by splitting this range.
 * 
 * @author A. Miller
 *
 * @param <T> must extends <code>Number</code> class and implement
 * <code>Comparable</code> interface.
 */
public interface KDRange<T extends Number & Comparable<T>> {

	/**
	 * The point represents beginning of the range.
	 * @return Point which can not be null.
	 */
	KDPoint<T> from();
	
	/**
	 * The point represents end of the range.
	 * @return Point which can not be null.
	 */
	KDPoint<T> to();
	
	/**
	 * Checks that specified point is included into the range inclusive.
	 * Dimensions of the point and the range must be equals.
	 * @param point point for check. Can not be null.
	 * @return <code>true</code> if point is included into the range, otherwise <code>false</code>.
	 */
	boolean contains(KDPoint<T> point);
	
	/**
	 * Splits range by specified coordinate and dimension and returns half which
	 * have lower values of coordinates for specified dimension. Line represented by this
	 * dimension and coordinate must intersect range, otherwise you will get
	 * <code>IllegalArgumentException</code> exception. 
	 * @param coordinate can not be null.
	 * @param dimension can not out of this range dimensions.
	 * @return New range of half which have lower values of coordinates for specified dimension.
	 */
	KDRange<T> lowerHalf(T coordinate, char dimension);
	
	/**
	 * Splits range by specified coordinate and dimension and returns half which
	 * have higher values of coordinates for specified dimension. Line represented by this
	 * dimension and coordinate must intersect range, otherwise you will get
	 * <code>IllegalArgumentException</code> exception. 
	 * @param coordinate can not be null.
	 * @param dimension can not out of this range dimensions.
	 * @return New range of half which have higher values of coordinates for specified dimension.
	 */
	KDRange<T> higherHalf(T coordinate, char dimension);
	
	/**
     * Calculates minimal distance between the specified point and the range border.
     * @param point must have the same dimension and can not be null.
     * @return Distance to the range border.
     */
    double distanceTo(KDPoint<T> point);
    
    /**
     * Calculates minimal squared distance between the specified point and the range border.
     * @param point must have the same dimension and can not be null.
     * @return Distance to the range border.
     */
    double squaredDistanceTo(KDPoint<T> point);
    
	/**
	 * @return Dimensions count of the range.
	 */
	char dimensions();
	
	KDPoint<T> center();
	
	T size(char dimension);
	
	boolean intersect(KDRange<T> range);
}
