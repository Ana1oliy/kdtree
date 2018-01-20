package ru.ana1oliy.kdtree;

import java.util.Arrays;

/**
 * Represents immutable multidimensional point. Provides functionality for
 * get any coordinate for dimension, calculate distance to other point and
 * get new point aligned to the current by any dimension.
 * 
 * @author Ana1oliy
 */
public class KDPoint {
	
	public KDPoint(final Double... coordinates) {
		if (coordinates == null)
			throw new IllegalArgumentException("Parameter can not be null.");
		
		if (coordinates.length == 0)
			throw new IllegalArgumentException("Count of dimensions must be greater than 0.");
		
		if (coordinates.length > Character.MAX_VALUE)
			throw new IllegalArgumentException("Dimensions count limited by Character.MAX_VALUE");
	
		this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
	}
	
	private KDPoint() {
		
	}
	
	private Double[] coordinates;
	
	/**
     * Get value for dimension.
     * @param dimension must be greater than or equal to 0 and less than size.
     * @return coordinate for passed dimension.
     */
	public Double get(char dimension) {
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
    
    /**
     * Calculates distance to other point.
     * @param point must have the same dimension and can not be null.
     * @return Distance to otherPoint.
     */
    public double distanceTo(KDPoint point) {
    	double squared = squaredDistanceTo(point);
    	return Math.sqrt(squared);
    }
    
    /**
     * Calculates squared distance to other point.
     * @param point must have the same dimension and can not be null.
     * @return Squared distance to otherPoint.
     */
    public double squaredDistanceTo(KDPoint point) {
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
    
    /**
     * Represents point as array.
     * @return New array of <code>size()</code> length which contains
     * point coordinates.
     */
    public Double[] asArray() {
    	return Arrays.copyOf(coordinates, coordinates.length);
    }
    
    /**
     * Creates new point which has <i>coordinate</i> for specified dimension,
     * but coordinates of other dimensions are equals to coordinates of this point. 
     * @param coordinate coordinate for specified dimension of the new point.
     * @param dimension dimension of the new point which will have specified
     * coordinate.
     * @return New aligned by dimension point.
     */
    public KDPoint alignedPoint(Double coordinate, char dimension) {
    	checkDimension(dimension);
    	
    	if (coordinate == null)
    		throw new IllegalArgumentException("Coordinate can not be null.");
    	
    	Double[] coordinates = asArray();
    	coordinates[dimension] = coordinate;
    	KDPoint alignedPoint = new KDPoint();
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
    	
		KDPoint other = (KDPoint) object;
    	
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
