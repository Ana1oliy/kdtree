package ru.ana1oliy.kdtree.range;

import ru.ana1oliy.kdtree.points.KDPoint;

public class DoubleKDRange extends AbstractKDRange<Double> {

	public DoubleKDRange(KDPoint<Double> from, KDPoint<Double> to) {
		super(from, to);
	}
	
	@Override
	protected Double[] createCoordinatesArray() {
		return new Double[dimensions()];
	}

	@Override
	protected Double middleOf(Double a, Double b) {
		return a + (b - a) / 2;
	}

	@Override
	protected KDRange<Double> createRange(KDPoint<Double> from, KDPoint<Double> to) {
		return new DoubleKDRange(from, to);
	}
	
	@Override
	protected Double distanceByAxis(Double a, Double b) {
		return Math.abs(a - b);
	}

}
