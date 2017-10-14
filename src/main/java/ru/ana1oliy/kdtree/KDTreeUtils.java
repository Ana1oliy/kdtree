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
    
    
}
