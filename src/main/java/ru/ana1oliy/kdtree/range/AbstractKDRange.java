package ru.ana1oliy.kdtree.range;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.points.NumberKDPoint;
import ru.ana1oliy.utils.ComparableUtils;

/**
 * Implements <code>KDRange</code> interface. 
 * <b>NOTE</b>: One range is equal to other range if the <i>from</i> point 
 * of the first range is equal to the <i>from</i> point of the second range and
 * the <i>to</i> point of the first range is equal to the <i>to</i> point of
 * the second range, or if the <i>from</i> point of the first range is equal
 * to the <i>to</i> point of the second range and the <i>to</i> point of the
 * first range is equal to the <i>from</i> point of the second range.
 * @author Ana1oliy
 *
 * @param <T> must extends <code>Number</code> class and implement
 * <code>Comparable</code> interface.
 */
public abstract class AbstractKDRange<T extends Number & Comparable<T>> implements KDRange<T> {

	/**
	 * Creates range by two points. Dimensions of this points must be equals. 
	 * Both points can not be null.
	 * @param from first point.
	 * @param to second point.
	 */
	public AbstractKDRange(KDPoint<T> from, KDPoint<T> to) {
		if (from == null || to == null)
			throw new IllegalArgumentException("Initial points can not be null.");
		
		if (from.dimensions() != to.dimensions())
			throw new IllegalArgumentException("Dimensions of points must be equals.");
		
		this.from = from;
		this.to = to;
		center = calculateCenter();
	}
	
	private KDPoint<T> from;
	
	private KDPoint<T> to;
	
	private KDPoint<T> center;
	
	@Override
	public KDPoint<T> from() {
		return from;
	}

	@Override
	public KDPoint<T> to() {
		return to;
	}

	@Override
	public boolean contains(KDPoint<T> point) {
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

	@Override
	public char dimensions() {
		return from.dimensions();
	}

	@Override
	public KDRange<T> lowerHalf(T coordinate, char dimension) {
		checkDimension(dimension);
		checkSplitCoordinate(coordinate);
		
		KDPoint<T> min;
		KDPoint<T> max;
		
		if (from.get(dimension).compareTo(to.get(dimension)) < 0) {
			min = from;
			max = to;
		} else {
			min = to;
			max = from;
		}
		
		KDPoint<T> splitPoint = max.alignedPoint(coordinate, dimension);
		checkSplitPoint(splitPoint);
		
		return createRange(min, splitPoint);
	}
	
	@Override
	public KDRange<T> higherHalf(T coordinate, char dimension) {
		checkDimension(dimension);
		checkSplitCoordinate(coordinate);
		
		KDPoint<T> min;
		KDPoint<T> max;
		
		if (from.get(dimension).compareTo(to.get(dimension)) < 0) {
			min = from;
			max = to;
		} else {
			min = to;
			max = from;
		}
		
		KDPoint<T> splitPoint = min.alignedPoint(coordinate, dimension);
		checkSplitPoint(splitPoint);
		
		return createRange(splitPoint, max);
	}
	
	@Override
	public KDPoint<T> center() {
		return center;
	}
	
	@Override
	public T size(char dimension) {
		return distanceByAxis(from.get(dimension), to.get(dimension));
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
		KDRange<T> other = (KDRange<T>) object;
    	
    	if (dimensions() != other.dimensions())
    		return false;
    		
    	return (from.equals(other.from()) && to.equals(other.to())) ||
    			(from.equals(other.to()) && to.equals(other.from()));
    }
	
	private KDPoint<T> calculateCenter() {
		T[] coordinates = createCoordinatesArray();
		
		for (char d = 0; d < dimensions(); d++)
			coordinates[d] = middleOf(from.get(d), to.get(d));
		
		return new NumberKDPoint<T>(coordinates);
	}
	
	protected abstract T[] createCoordinatesArray();
	
	protected abstract T middleOf(T a, T b);
	
	protected abstract KDRange<T> createRange(KDPoint<T> from, KDPoint<T> to);
	
	protected abstract T distanceByAxis(T a, T b);
	
	private void checkDimension(char dimension) {
		if (dimension >= dimensions())
			throw new IllegalArgumentException("Dimension does not exist.");
	}
	
	private void checkSplitCoordinate(T coordinate) {
		if (coordinate == null)
			throw new IllegalArgumentException("Coordinate can not be null.");
	}
	
	private void checkSplitPoint(KDPoint<T> point) {
		if (!contains(point))
			throw new IllegalArgumentException("Split line represented by dimension and coordinate must intersect the range.");
	}
}
