package ru.ana1oliy.kdtree;

import ru.ana1oliy.utils.ComparableUtils;

/**
 * Represents multidimensional range. Provides functionality for check contains
 * range any point or not and creation of new ranges by splitting this range.<b>
 * <b>NOTE</b>: One range is equal to other range if the <i>from</i> point 
 * of the first range is equal to the <i>from</i> point of the second range and
 * the <i>to</i> point of the first range is equal to the <i>to</i> point of
 * the second range, or if the <i>from</i> point of the first range is equal
 * to the <i>to</i> point of the second range and the <i>to</i> point of the
 * first range is equal to the <i>from</i> point of the second range.
 * @author Ana1oliy
 */
public class KDRange {

	/**
	 * Creates range by two points. Dimensions of this points must be equals. 
	 * Both points can not be null.
	 * @param from first point.
	 * @param to second point.
	 */
	public KDRange(KDPoint from, ru.ana1oliy.kdtree.KDPoint to) {
		if (from == null || to == null)
			throw new IllegalArgumentException("Initial points can not be null.");
		
		if (from.dimensions() != to.dimensions())
			throw new IllegalArgumentException("Dimensions of points must be equals.");
		
		this.from = from;
		this.to = to;
		center = calculateCenter();
	}
	
	private KDPoint from;
	
	private KDPoint to;
	
	private KDPoint center;
	
	/**
	 * The point represents beginning of the range.
	 * @return Point which can not be null.
	 */
	public KDPoint from() {
		return from;
	}

	/**
	 * The point represents end of the range.
	 * @return Point which can not be null.
	 */
	public KDPoint to() {
		return to;
	}

	/**
	 * Checks that specified point is included into the range inclusive.
	 * Dimensions of the point and the range must be equals.
	 * @param point point for check. Can not be null.
	 * @return <code>true</code> if point is included into the range, otherwise <code>false</code>.
	 */
	public boolean contains(KDPoint point) {
		if (point == null)
			throw new IllegalArgumentException("Point can not be null.");
		
		if (point.dimensions() != dimensions())
			throw new IllegalArgumentException("Range and point dimensions must be equal.");
		
		for (char dimension = 0; dimension < dimensions(); dimension++) {
			if (!ComparableUtils.isBeetween(point.get(dimension), from.get(dimension), to.get(dimension)))
				return false;
		}
		
		return true;
	}

	/**
	 * @return Dimensions count of the range.
	 */
	public char dimensions() {
		return from.dimensions();
	}

	/**
	 * Splits range by specified coordinate and dimension and returns half which
	 * have lower values of coordinates for specified dimension. Line represented by this
	 * dimension and coordinate must intersect range, otherwise you will get
	 * <code>IllegalArgumentException</code> exception. 
	 * @param coordinate can not be null.
	 * @param dimension can not out of this range dimensions.
	 * @return New range of half which have lower values of coordinates for specified dimension.
	 */
	public KDRange lowerHalf(Double coordinate, char dimension) {
		checkDimension(dimension);
		checkSplitCoordinate(coordinate);
		
		KDPoint min;
		KDPoint max;
		
		if (from.get(dimension).compareTo(to.get(dimension)) < 0) {
			min = from;
			max = to;
		} else {
			min = to;
			max = from;
		}
		
		KDPoint splitPoint = max.alignedPoint(coordinate, dimension);
		checkSplitPoint(splitPoint);
		
		return new KDRange(min, splitPoint);
	}
	
	/**
	 * Splits range by specified coordinate and dimension and returns half which
	 * have higher values of coordinates for specified dimension. Line represented by this
	 * dimension and coordinate must intersect range, otherwise you will get
	 * <code>IllegalArgumentException</code> exception. 
	 * @param coordinate can not be null.
	 * @param dimension can not out of this range dimensions.
	 * @return New range of half which have higher values of coordinates for specified dimension.
	 */
	public KDRange higherHalf(Double coordinate, char dimension) {
		checkDimension(dimension);
		checkSplitCoordinate(coordinate);
		
		KDPoint min;
		KDPoint max;
		
		if (from.get(dimension).compareTo(to.get(dimension)) < 0) {
			min = from;
			max = to;
		} else {
			min = to;
			max = from;
		}
		
		KDPoint splitPoint = min.alignedPoint(coordinate, dimension);
		checkSplitPoint(splitPoint);
		
		return new KDRange(splitPoint, max);
	}
	
	/**
	 * Center of the range.
	 */
	public KDPoint center() {
		return center;
	}
	
	/**
	 * Size of the range by dimension.
	 */
	public Double size(char dimension) {
		return distanceByAxis(from.get(dimension), to.get(dimension));
	}
	
	/**
     * Calculates minimal squared distance between the specified point and the range border.
     * @param point must have the same dimension and can not be null.
     * @return Distance to the range border.
     */
	public double squaredDistanceTo(KDPoint point) {
		checkPoint(point);
		
		double squaredDistance = 0;
		
		for (char d = 0; d < dimensions(); d++) {
			double delta = 0;
			Double pointCoordinate = point.get(d);
			Double maxCoordinate = maxCoordinate(this, d);
			Double minCoordinate = minCoordinate(this, d);
			
			if (pointCoordinate.compareTo(minCoordinate) < 0)
				delta = minCoordinate.doubleValue() - pointCoordinate.doubleValue();
			else if (pointCoordinate.compareTo(maxCoordinate) > 0)
				delta = pointCoordinate.doubleValue() - maxCoordinate.doubleValue();
			
			squaredDistance += delta * delta;
		}
		
		return squaredDistance;
	}
	
	/**
     * Calculates minimal distance between the specified point and the range border.
     * @param point must have the same dimension and can not be null.
     * @return Distance to the range border.
     */
	public double distanceTo(KDPoint point) {
		return Math.sqrt(squaredDistanceTo(point));
	}
	
	public boolean intersect(KDRange range) {
		checkRange(range);
		
		for (char d = 0; d < dimensions(); d++) {
			if (minCoordinate(this, d).compareTo(maxCoordinate(range, d)) > 0 ||
					maxCoordinate(this, d).compareTo(minCoordinate(range, d)) < 0)
				return false;
		}
		
		return true;
	}
	
	@Override
    public boolean equals(Object object) {
    	if (object == null)
    		return false;
    	
    	if (object == this)
    		return true;
    	
    	if (!getClass().isAssignableFrom(object.getClass()))
    		return false;
    	
		KDRange other = (KDRange) object;
    	
    	if (dimensions() != other.dimensions())
    		return false;
    		
    	return (from.equals(other.from()) && to.equals(other.to())) ||
    			(from.equals(other.to()) && to.equals(other.from()));
    }
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append('[');
		builder.append(from);
		builder.append(" - ");
		builder.append(to);
		builder.append(']');
		
		return builder.toString();
	}
	
	private KDPoint calculateCenter() {
		Double[] coordinates = new Double[dimensions()];
		
		for (char d = 0; d < dimensions(); d++)
			coordinates[d] = middleOf(from.get(d), to.get(d));
		
		return new KDPoint(coordinates);
	}
	
	private static Double minCoordinate(KDRange range, char dimension) {
		if (range.from().get(dimension).compareTo(range.to().get(dimension)) < 0) {
			return range.from().get(dimension);
		} else {
			return range.to().get(dimension);
		}
	}
	
	private static Double maxCoordinate(KDRange range, char dimension) {
		if (range.from().get(dimension).compareTo(range.to().get(dimension)) > 0) {
			return range.from().get(dimension);
		} else {
			return range.to().get(dimension);
		}
	}
	
	private Double middleOf(Double a, Double b) {
		return a + (b - a) / 2;
	}
//	
//	protected abstract KDRange createRange(KDPoint from, KDPoint to);
//	
	protected Double distanceByAxis(Double a, Double b) {
		return Math.abs(a - b);
	}
	
	private void checkDimension(char dimension) {
		if (dimension >= dimensions())
			throw new IllegalArgumentException("Dimension does not exist.");
	}
	
	private void checkSplitCoordinate(Double coordinate) {
		if (coordinate == null)
			throw new IllegalArgumentException("Coordinate can not be null.");
	}
	
	private void checkSplitPoint(KDPoint point) {
		if (!contains(point))
			throw new IllegalArgumentException("Split line represented by dimension and coordinate must intersect the range.");
	}
	
	private void checkPoint(KDPoint point) {
    	if (point == null)
    		throw new IllegalArgumentException("Point can not be null.");
    	
    	if (point.dimensions() != dimensions())
    		throw new IllegalArgumentException("Incorrect point dimension.");
    }
	
	
	private void checkRange(KDRange range) {
    	if (range == null)
    		throw new IllegalArgumentException("Range can not be null.");
    	
    	if (range.dimensions() != dimensions())
    		throw new IllegalArgumentException("Incorrect range dimension.");
    }
}
