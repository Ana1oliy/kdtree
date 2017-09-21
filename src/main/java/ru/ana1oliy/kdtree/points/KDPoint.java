package ru.ana1oliy.kdtree.points;

/**
 * Created by Ana1oliy on 13.09.2017.
 */

public interface KDPoint<T extends Comparable<T>> {
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
    
    double distanceTo(KDPoint<T> point);
    
    double squaredDistanceTo(KDPoint<T> point);
}
