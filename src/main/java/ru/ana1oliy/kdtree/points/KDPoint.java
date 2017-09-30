package ru.ana1oliy.kdtree.points;

/**
 * Represents immutable multidimensional point. Provides functionality for
 * get any coordinate for dimension, calculate distance to other point and
 * get new point aligned to the current by any dimension.
 * 
 * @author Ana1oliy
 *
 * @param <T> must extends <code>Number</code> class and implement
 * <code>Comparable</code> interface.
 */
public interface KDPoint<T extends Number & Comparable<T>> {
    /**
     * Get value for dimension.
     * @param dimension must be greater than or equal to 0 and less than size.
     * @return coordinate for passed dimension.
     */
    T get(char dimension);

    /**
     * Point dimensions count. Assumes that greater than 0.
     * @return dimensions count.
     */
    char size();
    
    /**
     * Calculates distance to other point.
     * @param point must have the same dimension and can not be null.
     * @return Distance to otherPoint.
     */
    double distanceTo(KDPoint<T> point);
    
    /**
     * Calculates squared distance to other point.
     * @param point must have the same dimension and can not be null.
     * @return Squared distance to otherPoint.
     */
    double squaredDistanceTo(KDPoint<T> point);
    
    /**
     * Represents point as array.
     * @return New array of <code>size()</code> length which contains
     * point coordinates.
     */
    T[] asArray();
    
    /**
     * Creates new point which has <i>coordinate</i> for specified dimension,
     * but coordinates of other dimensions are equals to coordinates of this point. 
     * @param coordinate coordinate for specified dimension of the new point.
     * @param dimension dimension of the new point which will have specified
     * coordinate.
     * @return New aligned by dimension point.
     */
    KDPoint<T> alignedPoint(T coordinate, char dimension);
}
