package ru.ana1oliy.kdtree.range;

import ru.ana1oliy.kdtree.points.KDPoint;

public class RangeKD<T extends Number & Comparable<T>> implements KDRange<T> {

	/**
	 * Creates range by two points. Dimensions of this points must be equals. 
	 * Both points can not be null.
	 * @param from first point.
	 * @param to second point.
	 */
	public RangeKD(KDPoint<T> from, KDPoint<T> to) {
		if (from == null || to == null)
			throw new IllegalArgumentException("Initial points can not be null.");
		
		if (from.size() != to.size())
			throw new IllegalArgumentException("Dimensions of points must be equals.");
		
		this.from = from;
		this.to = to;
	}
	
	private KDPoint<T> from;
	
	private KDPoint<T> to;
	
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public KDRange<T> union(KDRange<T> range) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KDRange<T> intarsection(KDRange<T> range) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KDRange<T> subtraction(KDRange<T> range) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public KDRange<T>[] split(T coordinate, char dimension) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double distanceTo(KDPoint<T> point) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public char size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean intersect(KDRange<T> range) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double squaredDistanceTo(KDPoint<T> point) {
		// TODO Auto-generated method stub
		return 0;
	}

}
