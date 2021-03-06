package ru.ana1oliy.kdtree;

import java.util.NoSuchElementException;

import ru.ana1oliy.utils.Visitor;

/**
 * Internal tree node class. Provides functionality for add child nodes, search points
 * in range and nearest point.
 */
class KDTreeNode<G> {

    public KDTreeNode(KDPoint key, KDRange range) {
        this.key = key;
        this.range = range;
        dimension = 0;
    }
    
    protected KDTreeNode(KDPoint key) {
        this.key = key;
    }

    private KDRange range;
    
    private char dimension;

    private KDPoint key;
    
    private G value;

    private KDTreeNode<G> left;

    private KDTreeNode<G> right;

    public KDPoint key() {
    	return key;
    }
    
    public KDRange range() {
    	return range;
    }
    
    public void setValue(G value) {
    	this.value = value;
    }
    
    public G getValue() {
    	return value;
    }
    
    /**
     * Adds new node into subtree using KDPoint as key.
     * @param key must implement KDPoint interface.
     */
    public boolean add(KDPoint key, G value) {
    	if (this.key.equals(key)) {
    		this.value = value;
    		return false;
    	}
    	
        if (key.get(dimension).compareTo(this.key.get(dimension)) < 0)
            addLeft(key, value);
        else
            addRight(key, value);
        
        return true;
    }

    private void addLeft(KDPoint leftKey, G value) {
        if (left == null) {
            left = createChildNode(leftKey);
            left.range = range.lowerHalf(key.get(dimension), dimension);
            left.value = value;
        } else
            left.add(leftKey, value);
    }

    private void addRight(KDPoint rightKkey, G value) {
        if (right == null) {
            right = createChildNode(rightKkey);
            right.range = range.higherHalf(key.get(dimension), dimension);
            right.value = value;
        } else
            right.add(rightKkey, value);
    }

    private KDTreeNode<G> createChildNode(KDPoint key) {
        KDTreeNode<G> child = new KDTreeNode<>(key);
        child.dimension = KDTreeUtils.nextDimension(dimension, key.dimensions());

        return child;
    }
    
    
    public G get(KDPoint point) {
    	if (point.equals(key))
    		return value;
    	
    	if (point.get(dimension).compareTo(key.get(dimension)) < 0) {
    		if (left == null)
    			throw new NoSuchElementException("Nothing to get");
    		
    		return left.get(point);
    	} else {
    		if (right == null)
    			throw new NoSuchElementException("Nothing to get");
    		
    		return right.get(point);
    	}
    }
    
    public boolean hasValue(KDPoint point) {
    	if (point.equals(key))
    		return true;
    	
    	if (point.get(dimension).compareTo(key.get(dimension)) < 0) {
    		if (left == null)
    			return false;
    		
    		return left.hasValue(point);
    	} else {
    		if (right == null)
    			return false;
    		
    		return right.hasValue(point);
    	}
    }
    
    public KDTreeNode<G> nearest(double min, KDPoint point) {
    	//System.out.println(key);
    	
    	KDTreeNode<G> candidate = null;
    	KDTreeNode<G> leftCandidate = null;
    	KDTreeNode<G> rightCandidate = null;
    	double newMin = min;
    	double dist = key.squaredDistanceTo(point);
    	
    	if (dist < newMin) {
    		newMin = dist;
    		candidate = this;
    	}
    	
    	if (left != null && left.mayContainNearest(point, newMin))
    		leftCandidate = left.nearest(newMin, point);
    	
    	if (right != null && right.mayContainNearest(point, newMin))
    		rightCandidate = right.nearest(newMin, point);
    	
    	if (leftCandidate != null && rightCandidate != null) {
    		double leftDist = leftCandidate.key.squaredDistanceTo(point);
    		double rightDist = rightCandidate.key.squaredDistanceTo(point);
    		
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
    
    public void find(Visitor<KDTreeNode<G>> visitor, KDRange range) {
    	//System.out.println(key);
    	
    	if (range.contains(key))
    		visitor.visit(this);
    	
    	if (left != null && left.isSenseToSearch(range))
    		left.find(visitor, range);
    	
    	if (right != null && right.isSenseToSearch(range))
    		right.find(visitor, range);
    }
    
    private boolean mayContainNearest(KDPoint point, double radiusSquared) {
    	return range.squaredDistanceTo(point) <= radiusSquared;
    }
    
    private boolean isSenseToSearch(KDRange range) {
    	return this.range.intersect(range);
    }

	private Double getDistanceByAxis(Double a, Double b) {
		return Math.abs(a - b);
	}
}
