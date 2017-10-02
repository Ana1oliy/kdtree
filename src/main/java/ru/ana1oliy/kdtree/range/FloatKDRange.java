package ru.ana1oliy.kdtree.range;

import ru.ana1oliy.kdtree.points.KDPoint;

public class FloatKDRange extends AbstractKDRange<Float> {

	public FloatKDRange(KDPoint<Float> from, KDPoint<Float> to) {
		super(from, to);
	}
	
	@Override
	protected Float[] createCoordinatesArray() {
		return new Float[dimensions()];
	}

	@Override
	protected Float middleOf(Float a, Float b) {
		return a + (b - a) / 2;
	}

	@Override
	protected KDRange<Float> createRange(KDPoint<Float> from, KDPoint<Float> to) {
		return new FloatKDRange(from, to);
	}

	@Override
	protected Float distanceByAxis(Float a, Float b) {
		return Math.abs(a - b);
	}
}
