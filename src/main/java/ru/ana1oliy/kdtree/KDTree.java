package ru.ana1oliy.kdtree;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import ru.ana1oliy.utils.Visitor;

/**
 * K-dimensional tree. Provides functionality for searching nearest
 * point for any k-dimentional point or points in any range.
 * @author Ana1oliy
 *
 * @param G type of data associated with point 
 */
public class KDTree<G> {
	
    public KDTree(KDRange range) {
    	if (range == null)
    		throw new IllegalArgumentException("Acceptable range required");
    	
    	this.range = range;
    	this.dimensions = range.dimensions();
    	size = 0;
    }
    
    private KDRange range;
    
    private KDTreeNode<G> root;
    
    private int size;
    
    private char dimensions;

    /**
	 * Adds specified point to the tree and assigns value to this point. If the tree contains the same point,
	 * value assigned to this point will be replaced.
	 * @param point key point to set value. Can not be null. Must fall within the common tree range.
	 * @param value value thats will be assigned to the point. Can not be null.
	 */
    public void set(KDPoint point, G value) {
    	checkPoint(point);
    	
    	if (!range.contains(point))
    		throw new IllegalArgumentException("Point out of accessible range.");
    	
    	if (value == null)
    		throw new IllegalArgumentException("Value can not be null.");
    	
        if (root == null) {
            root = new KDTreeNode<>(point, range);
            root.setValue(value);
            size++;
        } else if (root.add(point, value))
        	size++;
    }
    
    /**
	 * Returns the value assigned to the point. If value assigned to the point
	 * does not exists throws IllegalArgumentException. 
	 * @param point key for existing value in tree.
	 * @return Value assigned to the point.
	 */
    public G get(KDPoint point) {
    	checkPoint(point);
    	
        if (root == null)
            throw new NoSuchElementException("Nothing to get.");
            
        return root.get(point);
    }
    
    /**
	 * Checks that tree contains value for specified key point.
	 * @param point key for check. Can not be null.
	 * @return True if into the tree was added value for specified key point, otherwise false.
	 */
    public boolean hasValue(KDPoint point) {
    	checkPoint(point);
    	
    	if (root == null)
    		return false;
    	
    	return root.hasValue(point);
    }
    
    /**
	 * Finds the point in tree nearest to specified point.
	 * @param point point to find the nearest point.
	 * @return Point nearest to specified point. Null if nothing is found.
	 */
    public KDPoint nearest(KDPoint point) {
    	checkPoint(point);
    	
    	if (isEmpty())
    		throw new NoSuchElementException("Nothing to find.");
    	
    	return root.nearest(point.squaredDistanceTo(root.key()), point).key();
    }
    
    /**
	 * Finds the point in tree nearest to specified point and returns assigned value.
	 * @param point point to find the nearest point.
	 * @return Value assigned to the point nearest to specified point. Null if nothing is found.
	 */
    public G nearestValue(KDPoint point) {
    	checkPoint(point);
    	
    	if (isEmpty())
    		throw new NoSuchElementException("Nothing to find.");
    	
    	return root.nearest(point.squaredDistanceTo(root.key()), point).getValue();
    }
    
    /**
	 * Finds the points which are in range.
	 * @param searchRange range for searching.
	 * @return Points in specified range. Null if nothing is found.
	 */
    public List<KDPoint> find(KDRange searchRange) {
    	checkRange(searchRange);
    	
    	if (isEmpty())
    		throw new NoSuchElementException("Nothing to find.");
    	
    	List<KDPoint> result = new ArrayList<>();
    	Visitor<KDTreeNode<G>> visitor = new Visitor<KDTreeNode<G>>() {
    		public void visit(KDTreeNode<G> node) {
    			result.add(node.key());
    		}
    	};
    	
    	root.find(visitor, searchRange);
    	
    	return result;
    }
    
    /**
	 * Finds values assigned to the points which are in range.
	 * @param searchRange range for searching.
	 * @return values assigned to the points in specified range. Null if nothing is found.
	 */
    public List<G> findValues(KDRange searchRange)
    {
    	checkRange(searchRange);
    	
    	if (isEmpty())
    		throw new NoSuchElementException("Nothing to find.");
    	
    	List<G> result = new ArrayList<>();
    	Visitor<KDTreeNode<G>> visitor = new Visitor<KDTreeNode<G>>() {
    		public void visit(KDTreeNode<G> node) {
    			result.add(node.getValue());
    		}
    	};
    	
    	root.find(visitor, searchRange);
    	
    	return result;
    }
    
    /**
	 * Size of tree. 
	 * @return Count of points in the tree.
	 */
    public int size() {
    	return size;
    }
    
    /**
	 * @return <code>true</code> if tree does not contains any point,
	 * otherwise <code>false</code>.
	 */
    public boolean isEmpty() {
    	return size == 0;
    }
    
    /**
	 * @return k - number of dimensions
	 */
    public char dimensions() {
    	return dimensions;
    }
    
    private void checkPoint(KDPoint point) {
    	if (point == null)
    		throw new IllegalArgumentException("Point can not be null.");
    	
    	if (point.dimensions() != dimensions)
    		throw new IllegalArgumentException("Incorrect point dimension.");
    }
    
    private void checkRange(KDRange range) {
    	if (range == null)
    		throw new IllegalArgumentException("Range can not be null.");
    	
    	if (range.dimensions() != dimensions)
    		throw new IllegalArgumentException("Incorrect range dimension.");
    }
}
