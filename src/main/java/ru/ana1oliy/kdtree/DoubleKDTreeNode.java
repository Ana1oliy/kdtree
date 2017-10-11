package ru.ana1oliy.kdtree;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.range.KDRange;

class DoubleKDTreeNode<G> extends AbstractKDTreeNode<Double, G> {

	public DoubleKDTreeNode(KDPoint<Double> key, KDRange<Double> range) {
        super(key, range);
    }
	
	protected DoubleKDTreeNode(KDPoint<Double> key) {
		super(key);
	}
	
	@Override
	protected Double getDistanceByAxis(Double a, Double b) {
		return Math.abs(a - b);
	}

	@Override
	protected AbstractKDTreeNode<Double, G> createNode(KDPoint<Double> key) {
		return new DoubleKDTreeNode<>(key);
	}
	
	@Override
	protected Double[] createArray(char size) {
		return new Double[size];
	}
}
