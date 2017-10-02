package ru.ana1oliy.kdtree.range;

import ru.ana1oliy.kdtree.points.KDPoint;

public class IntKDRange extends AbstractKDRange<Integer> {

	public IntKDRange(KDPoint<Integer> from, KDPoint<Integer> to) {
		super(from, to);
	}
	
	@Override
	protected Integer[] createCoordinatesArray() {
		return new Integer[dimensions()];
	}

	@Override
	protected Integer middleOf(Integer a, Integer b) {
		return a + (b - a) / 2;
	}

	@Override
	protected KDRange<Integer> createRange(KDPoint<Integer> from, KDPoint<Integer> to) {
		return new IntKDRange(from, to);
	}

	@Override
	protected Integer distanceByAxis(Integer a, Integer b) {
		return Math.abs(a - b);
	}
}
