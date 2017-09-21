package ru.ana1oliy.kdtree.points;

import java.util.Arrays;

public class PointKD<T extends Number & Comparable<T>> implements KDPoint<T> {
	
	public PointKD(@SuppressWarnings("unchecked") final T... coordinates) {
		if (coordinates == null)
			throw new IllegalArgumentException("Parameter can not be null.");
		
		if (coordinates.length == 0)
			throw new IllegalArgumentException("Count of dimensions must be greater than 0.");
		
		if (coordinates.length > Character.MAX_VALUE)
			throw new IllegalArgumentException("Dimensions count limited by Character.MAX_VALUE");
	
		this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
	}
	
	private T[] coordinates;
	
	public T get(char dimension) {
		if (dimension >= size())
			throw new IllegalArgumentException("Dimension does not exist.");
		
		return coordinates[dimension];
	}

    /**
     * Point dimensions count. Assumes that greater than 0.
     * @return dimensions count.
     */
    public char size() {
    	return (char) coordinates.length;
    }
    
    public double distanceTo(KDPoint<T> point) {
    	double squared = squaredDistanceTo(point);
    	return Math.sqrt(squared);
    }
    
    public double squaredDistanceTo(KDPoint<T> point) {
    	if (point == null)
    		throw new IllegalArgumentException("Point can not be null.");
    	
    	if (size() != point.size())
    		throw new IllegalArgumentException("Dimensions of the points must be equal.");
    	
    	double sum = 0;
    	
    	for (char d = 0; d < size(); d++) {
    		sum += Math.pow(get(d).doubleValue() - point.get(d).doubleValue(), 2);
    	}
    	
    	return sum;
    }
    
    @Override
    public boolean equals(Object object) {
    	if (object == null)
    		return false;
    	
    	if (object == this)
    		return true;
    	
    	if (!getClass().isAssignableFrom(object.getClass()))
    		return false;
    	
    	@SuppressWarnings("unchecked")
		PointKD<T> other = (PointKD<T>) object;
    	
    	if (size() != other.size())
    		return false;
    	
    	for (char i = 0; i < size(); i++) {
    		if (!get(i).equals(other.get(i)))
    			return false;
    	}
    		
    	return true;
    }
}
