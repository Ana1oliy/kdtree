package ru.ana1oliy.kdtree;

/**
 * Created by Ana1oliy on 14.09.2017.
 */

public class KDTreeUtils {

    private KDTreeUtils() {

    }

    public static char nextDimension(char dimension, char dimCount) {
        char next = (char) (dimension + 1);

        if (next >= dimCount)
            next = 0;

        return next;
    }
    
    public static <T extends Comparable<T>> boolean isBeetween(T value, T boundA, T boundB) {
    	T hi;
    	T lo;
    	
    	if (boundA.compareTo(boundB) > 0) {
    		hi = boundA;
    		lo = boundB;
    	} else {
    		hi = boundB;
    		lo = boundA;
    	}
    	
    	return value.compareTo(lo) >= 0 && value.compareTo(hi) <= 0;
    }
}
