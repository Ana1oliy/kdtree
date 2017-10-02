package ru.ana1oliy.kdtree.points;

import java.util.Arrays;

public class NumberKDPoint<T extends Number & Comparable<T>> implements KDPoint<T> {
	
	public NumberKDPoint(@SuppressWarnings("unchecked") final T... coordinates) {
		if (coordinates == null)
			throw new IllegalArgumentException("Parameter can not be null.");
		
		if (coordinates.length == 0)
			throw new IllegalArgumentException("Count of dimensions must be greater than 0.");
		
		if (coordinates.length > Character.MAX_VALUE)
			throw new IllegalArgumentException("Dimensions count limited by Character.MAX_VALUE");
	
		this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
	}
	
	private NumberKDPoint() {
		
	}
	
	private T[] coordinates;
	
	public T get(char dimension) {
		checkDimension(dimension);
		return coordinates[dimension];
	}

    /**
     * Point dimensions count. Assumes that greater than 0.
     * @return dimensions count.
     */
    public char dimensions() {
    	return (char) coordinates.length;
    }
    
    public double distanceTo(KDPoint<T> point) {
    	double squared = squaredDistanceTo(point);
    	return Math.sqrt(squared);
    }
    
    public double squaredDistanceTo(KDPoint<T> point) {
    	if (point == null)
    		throw new IllegalArgumentException("Point can not be null.");
    	
    	if (dimensions() != point.dimensions())
    		throw new IllegalArgumentException("Dimensions of the points must be equal.");
    	
    	double sum = 0;
    	
    	for (char d = 0; d < dimensions(); d++) {
    		sum += Math.pow(get(d).doubleValue() - point.get(d).doubleValue(), 2);
    	}
    	
    	return sum;
    }
    
    public T[] asArray() {
    	return Arrays.copyOf(coordinates, coordinates.length);
    }
    
    public KDPoint<T> alignedPoint(T coordinate, char dimension) {
    	checkDimension(dimension);
    	
    	if (coordinate == null)
    		throw new IllegalArgumentException("Coordinate can not be null.");
    	
    	T[] coordinates = asArray();
    	coordinates[dimension] = coordinate;
    	NumberKDPoint<T> alignedPoint = new NumberKDPoint<>();
    	alignedPoint.coordinates = coordinates;
    	
    	return alignedPoint;
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
		NumberKDPoint<T> other = (NumberKDPoint<T>) object;
    	
    	if (dimensions() != other.dimensions())
    		return false;
    	
    	for (char i = 0; i < dimensions(); i++) {
    		if (!get(i).equals(other.get(i)))
    			return false;
    	}
    		
    	return true;
    }
    
    @Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append('(');
    	
    	for (char d = 0; d < dimensions(); d++) {
    		builder.append(coordinates[d]);
    		
    		if (d < dimensions() - 1)
    			builder.append(", ");
    	}
    	
    	builder.append(')');
    	
    	return builder.toString();
    }
    
    private void checkDimension(char dimension) {
    	if (dimension >= dimensions())
			throw new IllegalArgumentException("Dimension does not exist.");
    }
}
