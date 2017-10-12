package ru.ana1oliy.kdtree;


import java.util.ArrayList;
import java.util.List;
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
public abstract class AbstractKDTree<T extends Number & Comparable<T>, G> implements KDTree<T, G> {
	
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
    
    private AbstractKDTreeNode<T, G> root;
    
    private int size;
    
    private char dimensions;

    @Override
    public void set(KDPoint<T> point, G value) {
    	checkPoint(point);
    	
    	if (!range.contains(point))
    		throw new IllegalArgumentException("Point out of accessible range.");
    	
    	if (value == null)
    		throw new IllegalArgumentException("Value can not be null.");
    	
        if (root == null) {
            root = createNode(point, range);
            root.setValue(value);
            size++;
        } else if (root.add(point, value))
        	size++;
    }
    
    @Override
    public G get(KDPoint<T> point) {
    	checkPoint(point);
    	
        if (root == null)
            throw new NoSuchElementException("Nothing to get.");
            
        return root.get(point);
    }
    
    @Override
    public boolean hasValue(KDPoint<T> point) {
    	checkPoint(point);
    	
    	if (root == null)
    		return false;
    	
    	return root.hasValue(point);
    }
    
    @Override
    public KDPoint<T> nearest(KDPoint<T> point) {
    	checkPoint(point);
    	
    	if (isEmpty())
    		throw new NoSuchElementException("Nothing to find.");
    	
    	return root.nearest(point.squaredDistanceTo(root.key()), point);
    }
    
    @Override
    public List<KDPoint<T>> find(KDRange<T> searchRange) {
    	checkRange(searchRange);
    	
    	if (isEmpty())
    		throw new NoSuchElementException("Nothing to find.");
    	
    	List<KDPoint<T>> result = new ArrayList<>();
    	root.find(result, searchRange);
    	return result;
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
    }
    
    private void checkRange(KDRange<T> range) {
    	if (range == null)
    		throw new IllegalArgumentException("Range can not be null.");
    	
    	if (range.dimensions() != dimensions)
    		throw new IllegalArgumentException("Incorrect range dimension.");
    }
    
    protected abstract AbstractKDTreeNode<T, G> createNode(KDPoint<T> point, KDRange<T> range);
}
