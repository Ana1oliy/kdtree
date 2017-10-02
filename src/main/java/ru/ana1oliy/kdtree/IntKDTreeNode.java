package ru.ana1oliy.kdtree;

import ru.ana1oliy.kdtree.points.KDPoint;
import ru.ana1oliy.kdtree.range.KDRange;

class IntKDTreeNode extends AbstractKDTreeNode<Integer> {

	public IntKDTreeNode(KDPoint<Integer> key, KDRange<Integer> range) {
        super(key, range);
    }
	
	protected IntKDTreeNode(KDPoint<Integer> key) {
		super(key);
	}
	
	@Override
	protected Integer getDistanceByAxis(Integer a, Integer b) {
		return Math.abs(a - b);
	}

	@Override
	protected AbstractKDTreeNode<Integer> createNode(KDPoint<Integer> key) {
		return new IntKDTreeNode(key);
	}

	@Override
	protected Integer[] createArray(char size) {
		return new Integer[size];
	}
}
