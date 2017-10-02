package ru.ana1oliy.kdtree;


import java.util.NoSuchElementException;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.range.KDRange;

/**
 * 
 * @author Ana1oliy
 *
 * @param <T> must extends <code>Number</code> class and implement
 * <code>Comparable</code> interface.
 */
public abstract class AbstractKDTree<T extends Number & Comparable<T>> implements KDTree<T> {
	
    public AbstractKDTree(KDRange<T> range) {
    	if (range == null)
    		throw new IllegalArgumentException("Acceptable range required");
    	
//    	if (dimensions == 0)
//    		throw new IllegalArgumentException("Dimensions count must be greater than 0.");
    	
    	this.range = range;
    	this.dimensions = range.dimensions();
    	size = 0;
    }
    
    private KDRange<T> range;
    
    private AbstractKDTreeNode<T> root;
    
    private int size;
    
    private char dimensions;

    @Override
    public void add(KDPoint<T> point) {
    	checkPoint(point);
    	
        if (root == null) {
            root = createNode(point, range);
            size++;
        } else if (root.add(point))
        	size++;
    }
    
    @Override
    public KDPoint<T> nearest(KDPoint<T> point) {
    	checkPoint(point);
    	
    	if (isEmpty())
    		throw new NoSuchElementException("Nothing to find.");
    	
    	return root.nearest(point.squaredDistanceTo(root.key()), point);
    }
    
    @Override
    public Iterable <KDPoint<T>> find(KDRange<T> searchRange) {
    	return null;
    }
    
    @Override
    public int size() {
    	return size;
    }
    
    @Override
    public boolean isEmpty() {
    	return size == 0;
    }
    
    @Override
    public char dimensions() {
    	return dimensions;
    }
    
    private void checkPoint(KDPoint<T> point) {
    	if (point == null)
    		throw new IllegalArgumentException("Point can not be null.");
    	
    	if (point.dimensions() != dimensions)
    		throw new IllegalArgumentException("Incorrect point dimension.");
    	
    	if (!range.contains(point))
    		throw new IllegalArgumentException("Point out of accessible range.");
    }
    
    protected abstract AbstractKDTreeNode<T> createNode(KDPoint<T> point, KDRange<T> range);
}
