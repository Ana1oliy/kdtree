package ru.ana1oliy.kdtree;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.range.KDRange;

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
