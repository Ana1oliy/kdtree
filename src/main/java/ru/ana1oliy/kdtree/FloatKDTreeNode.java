package ru.ana1oliy.kdtree;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.range.KDRange;

class FloatKDTreeNode extends AbstractKDTreeNode<Float> {

	public FloatKDTreeNode(KDPoint<Float> key, KDRange<Float> range) {
        super(key, range);
    }
	
	protected FloatKDTreeNode(KDPoint<Float> key) {
		super(key);
	}
	
	@Override
	protected Float getDistanceByAxis(Float a, Float b) {
		return Math.abs(a - b);
	}

	@Override
	protected AbstractKDTreeNode<Float> createNode(KDPoint<Float> key) {
		return new FloatKDTreeNode(key);
	}

	@Override
	protected Float[] createArray(char size) {
		return new Float[size];
	}
}
