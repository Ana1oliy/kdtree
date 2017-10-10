package ru.ana1oliy.kdtree;

import java.util.Collection;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.range.KDRange;

/**
 * Internal tree node class. Provides functionality for add child nodes, search points
 * in range and nearest point.
 */
abstract class AbstractKDTreeNode<T extends Number & Comparable<T>> {

    public AbstractKDTreeNode(KDPoint<T> key, KDRange<T> range) {
        this.key = key;
        this.range = range;
        dimension = 0;
    }
    
    protected AbstractKDTreeNode(KDPoint<T> key) {
        this.key = key;
    }

    private KDRange<T> range;
    
    private char dimension;

    private KDPoint<T> key;

    private AbstractKDTreeNode<T> left;

    private AbstractKDTreeNode<T> right;

    public KDPoint<T> key() {
    	return key;
    }
    
    public KDRange<T> range() {
    	return range;
    }
    /**
     * Adds new node into subtree using KDPoint as key.
     * @param key must implement KDPoint interface.
     */
    public boolean add(KDPoint<T> key) {
    	if (this.key.equals(key))
    		return false;
    	
        if (key.get(dimension).compareTo(this.key.get(dimension)) < 0)
            addLeft(key);
        else
            addRight(key);
        
        return true;
    }

    private void addLeft(KDPoint<T> leftKey) {
        if (left == null) {
            left = createChildNode(leftKey);
            left.range = range.lowerHalf(key.get(dimension), dimension);
        } else
            left.add(leftKey);
    }

    private void addRight(KDPoint<T> rightKkey) {
        if (right == null) {
            right = createChildNode(rightKkey);
            right.range = range.higherHalf(key.get(dimension), dimension);
        } else
            right.add(rightKkey);
    }

    private AbstractKDTreeNode<T> createChildNode(KDPoint<T> key) {
        AbstractKDTreeNode<T> child = createNode(key);
        child.dimension = KDTreeUtils.nextDimension(dimension, key.dimensions());

        return child;
    }
    
    
    public KDPoint<T> nearest(double min, KDPoint<T> point) {
    	//System.out.println(key);
    	
    	KDPoint<T> candidate = null;
    	KDPoint<T> leftCandidate = null;
    	KDPoint<T> rightCandidate = null;
    	double newMin = min;
    	double dist = key.squaredDistanceTo(point);
    	
    	if (dist < newMin) {
    		newMin = dist;
    		candidate = key;
    	}
    	
    	if (left != null && left.mayContainNearest(point, newMin))
    		leftCandidate = left.nearest(newMin, point);
    	
    	if (right != null && right.mayContainNearest(point, newMin))
    		rightCandidate = right.nearest(newMin, point);
    	
    	if (leftCandidate != null && rightCandidate != null) {
    		double leftDist = leftCandidate.squaredDistanceTo(point);
    		double rightDist = rightCandidate.squaredDistanceTo(point);
    		
    		if (leftDist < rightDist)
    			return leftCandidate;
    		else
    			return rightCandidate;
    		
    	} else if (leftCandidate != null) {
    		return leftCandidate;
    	} else if (rightCandidate != null) {
    		return rightCandidate;
    	}
   
    	return candidate;
    }
    
    public void find(Collection<KDPoint<T>> found, KDRange<T> range) {
    	//System.out.println(key);
    	
    	if (range.contains(key))
    		found.add(key);
    	
    	if (left != null && left.isSenseToSearch(range))
    		left.find(found, range);
    	
    	if (right != null && right.isSenseToSearch(range))
    		right.find(found, range);
    }
    
    private boolean mayContainNearest(KDPoint<T> point, double radiusSquared) {
    	return range.squaredDistanceTo(point) <= radiusSquared;
    }
    
    private boolean isSenseToSearch(KDRange<T> range) {
    	return this.range.intersect(range);
    }
    protected abstract T getDistanceByAxis(T a, T b);
    
    protected abstract AbstractKDTreeNode<T> createNode(KDPoint<T> key);
    
    protected abstract T[] createArray(char size);
}
